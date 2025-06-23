package demre.avaj.simulator.weather;

import java.util.Random;

import demre.avaj.simulator.aircrafts.Coordinates;
import demre.avaj.simulator.aircrafts.Simulation;

// Singleton

/**
 * WeatherProvider is a singleton class that provides the current weather
 * conditions based on the coordinates and the current turn of the simulation.
 * The weather conditions are determined using a random seed based on the
 * coordinates and the simulation state, ensuring that the weather remains
 * constant for a given turn.
 */
public class WeatherProvider {
  // Static instance of the singleton
  private static WeatherProvider instance;

  private String[] weather; // Array of possible weather conditions

  // Private constructor to prevent instantiation
  private WeatherProvider() {
    this.weather = new String[] { "RAIN", "FOG", "SUN", "SNOW" };
  }

  // Public method to provide access to the singleton instance
  public static WeatherProvider getInstance() {
    if (instance == null) {
      instance = new WeatherProvider();
    }
    return instance;
  }

  /**
   * Returns the current weather at the given coordinates.
   * The weather is determined based on the coordinates and the current turn.
   * Weather remains constant at given coordinates for the duration of a turn.
   * 
   * @param p_coordinates
   * @return the current weather condition as a String
   */
  public String getCurrentWeather(Coordinates p_coordinates) {
    int seed = p_coordinates.getLongitude()
        * p_coordinates.getLatitude()
        * p_coordinates.getHeight()
        * (Simulation.getInstance().getTurn() + 1)
        * Simulation.getInstance().getSimulationSeed();

    Random random = new Random(seed);

    int index = random.nextInt(weather.length);
    return weather[index];
  }
}
