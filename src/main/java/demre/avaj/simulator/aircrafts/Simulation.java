package demre.avaj.simulator.aircrafts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import demre.avaj.simulator.factory.AircraftFactory;
import demre.avaj.simulator.tower.WeatherTower;
import demre.avaj.simulator.weather.WeatherProvider;
import static demre.avaj.simulator.Simulator.errorAndExit;

public class Simulation {
  private static Simulation instance; // singleton

  private int timesToRun; // number of times the simulation will run
  private int currentTurn; // number of times the simulation will run
  private int simulationSeed; // random number used as seed

  private ArrayList<Flyable> aircrafts; // array of all aircrafts
  private WeatherTower weatherTower;

  // Constructor
  private Simulation(String scenarioFileName) throws IOException {
    this.aircrafts = new ArrayList<>();
    // this.weatherProvider = WeatherProvider.getInstance();
    this.weatherTower = new WeatherTower();
    this.currentTurn = 1;

    // Each simulation gets a random seed number
    Random random = new Random();
    this.simulationSeed = random.nextInt();

    this.parseScenario(scenarioFileName);
  }

  // Getters //

  public int getTimesToRun() {
    return timesToRun;
  }

  public int getTurn() {
    return currentTurn;
  }

  public int getSimulationSeed() {
    return simulationSeed;
  }

  public static Simulation getInstance(String scenarioFileName) throws IOException {
    if (instance == null && !scenarioFileName.isEmpty()) {
      instance = new Simulation(scenarioFileName);
    } else if (instance == null) {
      errorAndExit("Simulation instantiation failed.");
    }
    return instance;
  }

  public static Simulation getInstance() {
    if (instance == null) {
      errorAndExit("Simulation not instantiated.");
    }
    return instance;
  }

  // Setters

  private void setTimesToRun(int num) {
    timesToRun = num;
  }

  public void nextTurn() {
    currentTurn = getTurn() + 1;
  }

  // Member functions //

  private void parseScenario(String scenarioFileName) throws IOException {
    File scenarioFile = new File(scenarioFileName);
    try (FileReader fr = new FileReader(scenarioFile);
        BufferedReader br = new BufferedReader(fr)) {
      AircraftFactory factory = AircraftFactory.getInstance();
      String line;
      boolean firstLine = true;

      System.out.println(getSimulationSeed());

      while ((line = br.readLine()) != null) {

        if (firstLine) {
          firstLine = false;
          setTimesToRun(Integer.parseInt(line.trim()));

        } else {
          String[] components = line.trim().split(" ");

          String type = components[0];
          String name = components[1];

          int longitude = Integer.parseInt(components[2]);
          int latitude = Integer.parseInt(components[3]);
          int height = Integer.parseInt(components[4]);
          if (height > 100) {
            height = 100;
          } else if (height < 0) {
            height = 0;
          }
          Coordinates coord = new Coordinates(longitude, latitude, height);

          // Factory creates new flyable aircraft
          Flyable aircraft = factory.newAircraft(type, name, coord);
          // Add aircraft to aircrafts list
          aircrafts.add(aircraft);

        }
      }
    }
  };

  public void runSimulation() {

    // System.out.println("Aircrafts loaded: " + aircrafts.size());
    // for (Flyable aircraft : aircrafts) {
    // System.out.println(
    // aircraft.getClass().getSimpleName() + "\t"
    // + aircraft.getName() + "(" + aircraft.getId() + ") "
    // + aircraft.getCoordinates().getLongitude() + " "
    // + aircraft.getCoordinates().getLatitude() + " "
    // + aircraft.getCoordinates().getHeight() + "\t"
    // +
    // WeatherProvider.getInstance().getCurrentWeather(aircraft.getCoordinates()));
    // }

    for (Flyable aircraft : aircrafts) {
      // Aircraft (observer) is registered with weatherTower (subject)
      // Only register aircrafts with height > 0
      if (aircraft.getCoordinates().getHeight() > 0) {
        aircraft.registerTower(weatherTower);
      }
    }

    while (getTurn() <= getTimesToRun()) {
      // System.out.println("turn: " + getTurn() + ", registered aircrafts: " +
      // weatherTower.getObservers().size());
      weatherTower.changeWeather();
    }
  };
}
