package aircraft.simulator.aircrafts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import aircraft.simulator.factory.AircraftFactory;
import aircraft.simulator.tower.WeatherTower;

import aircraft.simulator.exceptions.SimulationException;

/**
 * Manages the simulation of aircraft and weather interactions.
 * Handles scenario parsing, aircraft registration, and simulation turns.
 */
public class Simulation {
  private static Simulation instance; // singleton

  private PrintWriter writer; // to print to output file

  private int timesToRun; // number of times the simulation will run
  private int currentTurn; // current turn
  private int simulationSeed; // random number used as seed

  private ArrayList<Flyable> aircrafts; // array of all aircrafts
  private WeatherTower weatherTower;

  // Constructor //

  private Simulation(String scenarioFileName, PrintWriter p_writer) throws IOException {
    this.aircrafts = new ArrayList<>();
    // this.weatherProvider = WeatherProvider.getInstance();
    this.weatherTower = new WeatherTower();
    this.currentTurn = 1;
    this.writer = p_writer;

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

  public static Simulation getInstance(String scenarioFileName, PrintWriter p_writer) throws IOException {
    try {
      if (instance == null && !scenarioFileName.isEmpty() && p_writer != null) {
        instance = new Simulation(scenarioFileName, p_writer);
      } else if (instance == null) {
        throw new IllegalArgumentException("Simulation instantiation failed.");
      }

    } catch (Exception e) {
      throw new SimulationException(e.getMessage(), e);
    }
    return instance;
  }

  public static Simulation getInstance() {
    try {
      if (instance == null) {
        throw new IllegalStateException("Simulation not yet instantiated.");
      }
    } catch (Exception e) {
      throw new SimulationException(e.getMessage(), e);
    }
    return instance;
  }

  // Setters //

  private void setTimesToRun(int num) {
    timesToRun = num;
  }

  public void nextTurn() {
    currentTurn = getTurn() + 1;
  }

  // Member functions //

  /**
   * Parses the scenario file, and initialises times to run the simulation
   * and all flyable aircrafts.
   * 
   * @param scenarioFileName the path to the scenario file
   * @throws IOException if the file cannot be read
   */
  private void parseScenario(String scenarioFileName) throws IOException {
    File scenarioFile = new File(scenarioFileName);

    try (FileReader fr = new FileReader(scenarioFile);
        BufferedReader br = new BufferedReader(fr)) {

      AircraftFactory factory = AircraftFactory.getInstance();
      String line;
      boolean firstLine = true;

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

  /**
   * Runs the simulation for the specified number of turns.
   * Each turn, the weather tower changes the weather,
   * and each registered aircraft reacts to the weather.
   */
  public void runSimulation() {
    // Register Aircrafts (observer) with weatherTower (subject)
    for (Flyable aircraft : aircrafts) {
      if (aircraft.getCoordinates().getHeight() > 0) {
        aircraft.registerTower(weatherTower);
      }
    }

    // Loop and change the weather for each turn
    while (getTurn() <= getTimesToRun()) {
      // System.out.println(
      // "\u001B[31mTurn:\u001B[0m " + getTurn() + ", registered aircrafts: " +
      // weatherTower.getObservers().size());
      weatherTower.changeWeather();
    }
  };

  /**
   * Prints a message to the simulation output file.
   * 
   * @param message
   */
  public void announce(String message) {
    if (writer == null) {
      throw new IllegalStateException("Simulation output writer is not set.");
    }
    writer.println(message);
    // System.out.println(message);
  }
}
