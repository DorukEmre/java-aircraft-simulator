package main.java.demre.avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;

import main.java.demre.avaj.simulator.customExceptions.InvalidScenarioFileException;

public class Simulator {
  public static void main(String[] args) {
    try {
      if (args.length == 1) {
        String scenarioFileName = args[0];

        checkScenarioFile(scenarioFileName);
        System.out.println("File '" + scenarioFileName + "' exists and is a valid file.");

      } else {
        throw new IllegalArgumentException("Wrong number of arguments.");
      }
    } catch (Exception e) {
      System.out.println("Caught");
      errorAndExit(e.getMessage());

      // } catch (Error err) {
      // errorAndExit(err.getMessage());
    }
    System.exit(0);
  }

  private static void checkScenarioFile(String scenarioFileName)
      throws FileNotFoundException, InvalidScenarioFileException {
    File scenarioFile = new File(scenarioFileName);

    if (!scenarioFile.exists() || !scenarioFile.isFile()) {
      throw new FileNotFoundException(
          "File '" + scenarioFileName + "' does not exist or is not a valid file.");
    }

    checkScenarioFileContent(scenarioFile);
  }

  private static void checkScenarioFileContent(File scenarioFile) throws InvalidScenarioFileException {

    // The first line of the file contains a positive integer number. This number
    // represents the number of times the simulation is run. In our case, this will
    // be the number of times a weather change is triggered.
    // Each following line describes an aircraft that will be part of the
    // simulation, with this format: TYPE NAME LONGITUDE LATITUDE HEIGHT.
    //
    // 25
    // Baloon B1 2 3 20
    // Baloon B2 1 8 66
    // JetPlane J1 23 44 32
    // Helicopter H1 654 33 20

    if (true) {
      throw new InvalidScenarioFileException();
    }
  }

  public static void errorAndExit(String message) {
    System.out.println("\u001B[31mError:\u001B[0m " + message);
    System.exit(1);
  }
}
