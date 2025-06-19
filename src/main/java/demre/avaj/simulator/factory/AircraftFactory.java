package demre.avaj.simulator.factory;

import demre.avaj.simulator.aircrafts.*;

public class AircraftFactory {
  // Static instance of the singleton
  private static AircraftFactory instance;

  // Private constructor to prevent instantiation
  private AircraftFactory() {
  }

  // Public method to provide access to the singleton instance
  public static AircraftFactory getInstance() {
    if (instance == null) {
      instance = new AircraftFactory();
    }
    return instance;
  }

  public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
    Flyable flyable = new Flyable() {

    };

    return flyable;
  }
}
