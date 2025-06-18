package main.java.demre.avaj.simulator;

import java.io.File;

public class Simulator {
  public static void main(String[] args) {
    if (args.length == 1) {
      String scenarioFileName = args[0];

      if (isValidScenarioFile(scenarioFileName)) {
        System.out.println("File '" + scenarioFileName + "' exists and is a valid file.");

      } else {
        System.out.println(
            "\u001B[31mError:\u001B[0m file '" + scenarioFileName + "' does not exist or is not a valid file.");
      }
    } else {
      System.out.println("\u001B[31mError:\u001B[0m wrong number of arguments");
    }
    return;
  }

  private static boolean isValidScenarioFile(String scenarioFileName) {
    File scenarioFile = new File(scenarioFileName);
    return (scenarioFile.exists() && scenarioFile.isFile() && isValidScenarioFileContent(scenarioFile));
  }

  private static boolean isValidScenarioFileContent(File scenarioFile) {

    return (true);
  }
}
