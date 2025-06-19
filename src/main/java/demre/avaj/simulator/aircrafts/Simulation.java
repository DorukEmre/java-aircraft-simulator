package demre.avaj.simulator.aircrafts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import demre.avaj.simulator.factory.AircraftFactory;
import demre.avaj.simulator.weather.WeatherProvider;

public class Simulation {
  private static int turn = 0;
  private static int simulationSeed = 0;
  ArrayList<Flyable> aircrafts;
  AircraftFactory factory;
  WeatherProvider weatherProvider;

  // Constructor
  public Simulation() {
    this.aircrafts = new ArrayList<>();
    this.factory = AircraftFactory.getInstance();
    this.weatherProvider = WeatherProvider.getInstance();

    // Each simulation gets a random seed number
    Random random = new Random();
    simulationSeed = random.nextInt();
  }

  public static int getTurn() {
    return turn;
  }

  public static int getSimulationSeed() {
    return simulationSeed;
  }

  ////////////////////////////

  public void parseScenario(String scenarioFileName) throws IOException {
    File scenarioFile = new File(scenarioFileName);
    try (FileReader fr = new FileReader(scenarioFile);
        BufferedReader br = new BufferedReader(fr)) {
      String line;
      boolean firstLine = true;

      System.out.println(getSimulationSeed());

      while ((line = br.readLine()) != null) {

        if (firstLine) {
          firstLine = false;
          turn = Integer.parseInt(line.trim());

        } else {
          String[] components = line.trim().split(" ");

          String type = components[0];
          String name = components[1];
          int longitude = Integer.parseInt(components[2]);
          int latitude = Integer.parseInt(components[3]);
          int height = Integer.parseInt(components[4]);

          Coordinates coord = new Coordinates(longitude, latitude, height);

          System.out.println(
              "Weather (coord): " + weatherProvider.getCurrentWeather(coord) + "\t(" + longitude + "," + latitude + ","
                  + height + ")");

          // Factory creates new flyable aircraft
          Flyable aircraft = factory.newAircraft(type, name, coord);
          // Add aircraft to aircrafts list
          aircrafts.add(aircraft);

        }
      }
    }
  };

  public void runSimulation() {

    System.out.println("aircrafts.size: " + aircrafts.size());

  };

}
