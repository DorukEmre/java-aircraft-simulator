package demre.avaj.simulator.weather;

import static demre.avaj.simulator.aircrafts.Simulation.getSimulationSeed;
import static demre.avaj.simulator.aircrafts.Simulation.getTurn;

import java.util.Random;

import demre.avaj.simulator.aircrafts.Coordinates;

public class WeatherProvider {
  // Static instance of the singleton
  private static WeatherProvider instance;
  private String[] weather;

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

  public String getCurrentWeather(Coordinates p_coordinates) {
    // Returned weather stays constant at given coordinates at a given turn
    int seed = p_coordinates.getLongitude()
        * p_coordinates.getLatitude()
        * p_coordinates.getHeight()
        * (getTurn() + 1)
        * getSimulationSeed();

    Random random = new Random(seed);

    int index = random.nextInt(weather.length);
    return weather[index];
  }
}
