package aircraft.simulator;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import aircraft.simulator.aircrafts.Simulation;
import aircraft.simulator.exceptions.InvalidScenarioException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Simulator {
  public static void main(String[] args) {
    try {
      if (args.length != 1)
        throw new IllegalArgumentException("Wrong number of arguments.");

      // Check if the scenario file is valid
      String scenarioFileName = args[0];
      checkScenarioFile(scenarioFileName);

      // Create/check output file
      File output = new File("simulation.txt");
      if ((!output.exists() && !output.createNewFile()) || !output.canWrite())
        throw new IOException("Failed to create output file.");

      // Run the simulation
      try (
          FileWriter fileWriter = new FileWriter(output, false);
          PrintWriter writer = new PrintWriter(new BufferedWriter(fileWriter), true)) {
        Simulation sim = Simulation.getInstance(scenarioFileName, writer);
        sim.runSimulation();
      }

    } catch (Exception e) {
      errorAndExit(e, e.getMessage());

    }
    System.exit(0);
  }

  /**
   * Checks if the scenario file is valid.
   * 
   * @param scenarioFileName the name of the scenario file to check
   * @throws InvalidScenarioException
   * @throws FileNotFoundException
   * @throws IOException
   */
  private static void checkScenarioFile(String scenarioFileName)
      throws InvalidScenarioException, FileNotFoundException, IOException {
    File scenarioFile = new File(scenarioFileName);

    if (!scenarioFile.exists() || !scenarioFile.isFile()) {
      throw new FileNotFoundException(
          "File '" + scenarioFileName +
              "' does not exist or is not a valid file.");
    }

    checkScenarioFileContent(scenarioFile);
  }

  /**
   * Checks the content of the scenario file.
   * 
   * @param scenarioFile the scenario file to check
   */
  private static void checkScenarioFileContent(File scenarioFile)
      throws InvalidScenarioException, FileNotFoundException, IOException {

    if (scenarioFile.length() == 0)
      throw new InvalidScenarioException();

    try (FileReader fr = new FileReader(scenarioFile);
        BufferedReader br = new BufferedReader(fr)) {

      String line;
      boolean firstLine = true;

      while ((line = br.readLine()) != null) {

        if (firstLine) {
          firstLine = false;
          try {
            int simulationCount = Integer.parseInt(line.trim());
            if (simulationCount <= 0) {
              throw new InvalidScenarioException("The first line must be a positive integer.");
            }
          } catch (NumberFormatException e) {
            throw new InvalidScenarioException("The first line must be a valid integer.", e);
          }

        } else {
          // Split line in exactly 5 components
          String[] components = line.trim().split(" ");
          if (components.length != 5) {
            throw new InvalidScenarioException("Each line must have exactly 5 components.");
          }

          // Check component validity
          String type = components[0];
          // String name = components[1];
          int longitude, latitude, height;

          try {
            longitude = Integer.parseInt(components[2]);
            latitude = Integer.parseInt(components[3]);
            height = Integer.parseInt(components[4]);
          } catch (NumberFormatException e) {
            throw new InvalidScenarioException("Longitude, latitude, and height must be valid integers.", e);
          }

          if ((!type.equals("Baloon")
              && !type.equals("JetPlane")
              && !type.equals("Helicopter"))
              || (longitude <= 0 || latitude <= 0)
          // || (height < 1)
          ) {
            throw new InvalidScenarioException();
          }

        }
      }
    }
  }

  /**
   * Prints an error message and exits the program with a non-zero status.
   * 
   * @param message the error message to print
   */
  private static void errorAndExit(Exception e, String message) {
    // e.printStackTrace();
    System.out.println("\u001B[31mError:\u001B[0m " + message);
    System.exit(1);
  }

}
