package demre.avaj.simulator.factory;

import demre.avaj.simulator.aircrafts.*;

// class AircraftFactory <<Singleton>>
// {
// +Flyable* newAircraft(string p_type, string p_name, Coordinates p_coordinates)
// }

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
    Flyable flyable;
    long id = 1; // Set unique ID

    if (p_type.equals("Helicopter")) {
      flyable = new Helicopter(id, p_name, p_coordinates);
    } else if (p_type.equals("JetPlane")) {
      flyable = new JetPlane(id, p_name, p_coordinates);
    } else if (p_type.equals("Baloon")) {
      flyable = new Baloon(id, p_name, p_coordinates);
    } else {
      throw new IllegalArgumentException("Unknown aircraft type: " + p_type);
    }

    return flyable;
  }
}
