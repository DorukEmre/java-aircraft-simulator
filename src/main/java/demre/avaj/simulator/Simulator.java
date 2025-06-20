package demre.avaj.simulator;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;

import demre.avaj.simulator.aircrafts.Simulation;
import demre.avaj.simulator.customExceptions.InvalidScenarioException;

public class Simulator {
  public static void main(String[] args) {
    try {
      if (args.length != 1)
        throw new IllegalArgumentException("Wrong number of arguments.");

      String scenarioFileName = args[0];

      checkScenarioFile(scenarioFileName);
      System.out.println("File '" + scenarioFileName + "' checked successfully.\n");

      Simulation sim = Simulation.getInstance(scenarioFileName);
      sim.runSimulation();

    } catch (Exception e) {
      errorAndExit(e.getMessage());

      // } catch (Error err) {
      // errorAndExit(err.getMessage());
    }
    System.exit(0);
  }

  private static void checkScenarioFile(String scenarioFileName)
      throws InvalidScenarioException, FileNotFoundException, IOException {
    File scenarioFile = new File(scenarioFileName);

    if (!scenarioFile.exists() || !scenarioFile.isFile()) {
      throw new FileNotFoundException(
          "File '" + scenarioFileName + "' does not exist or is not a valid file.");
    }

    checkScenarioFileContent(scenarioFile);
  }

  private static void checkScenarioFileContent(File scenarioFile)
      throws InvalidScenarioException, FileNotFoundException, IOException {

    if (scenarioFile.length() == 0)
      throw new InvalidScenarioException();

    try (FileReader fr = new FileReader(scenarioFile);
        BufferedReader br = new BufferedReader(fr)) {
      String line;
      boolean firstLine = true;

      while ((line = br.readLine()) != null) {
        System.out.println(line);

        if (firstLine) {
          firstLine = false;
          try {
            int simulationCount = Integer.parseInt(line.trim());
            if (simulationCount <= 0) {
              throw new InvalidScenarioException("The first line must be a positive integer.");
            }
          } catch (NumberFormatException e) {
            throw new InvalidScenarioException("The first line must be a valid integer.");
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
            throw new InvalidScenarioException("Longitude, latitude, and height must be valid integers.");
          }

          if ((!type.equals("Baloon")
              && !type.equals("JetPlane")
              && !type.equals("Helicopter"))
              || (longitude <= 0 || latitude <= 0)
              || (height < 1)) {
            throw new InvalidScenarioException();
          }

        }
      }
    }
  }

  public static void errorAndExit(String message) {
    System.out.println("\u001B[31mError:\u001B[0m " + message);
    System.exit(1);
  }
}
