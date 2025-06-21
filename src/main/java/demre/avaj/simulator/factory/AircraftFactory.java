package demre.avaj.simulator.factory;

import demre.avaj.simulator.aircrafts.*;

// class AircraftFactory <<Singleton>>
// {
// +Flyable* newAircraft(string p_type, string p_name, Coordinates p_coordinates)
// }

public class AircraftFactory {
  // Static instance of the singleton
  private static AircraftFactory instance;
  private long maxId; // Current max aircraft unique ID

  // Private constructor to prevent instantiation
  private AircraftFactory() {
    this.maxId = 0;
  }

  // Public method to provide access to the singleton instance
  public static AircraftFactory getInstance() {
    if (instance == null) {
      instance = new AircraftFactory();
    }
    return instance;
  }

  // Getters - Setters

  private long getMaxId() {
    return maxId;
  }

  private void setMaxId(long id) {
    maxId = maxId + 1;
  }

  // Member functions

  public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
    Flyable flyable;
    long id = getMaxId() + 1;

    if (p_type.equals("Helicopter")) {
      flyable = new Helicopter(id, p_name, p_coordinates);
    } else if (p_type.equals("JetPlane")) {
      flyable = new JetPlane(id, p_name, p_coordinates);
    } else if (p_type.equals("Baloon")) {
      flyable = new Baloon(id, p_name, p_coordinates);
    } else {
      throw new IllegalArgumentException("Unknown aircraft type: " + p_type);
    }

    setMaxId(id);

    return flyable;
  }
}
