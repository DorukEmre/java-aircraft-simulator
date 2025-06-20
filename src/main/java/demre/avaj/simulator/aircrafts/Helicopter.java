package demre.avaj.simulator.aircrafts;

import demre.avaj.simulator.Simulator;

// class Helicopter
// {
// +Helicopter(long p_id, string p_name, Coordinates p_coordinate)
// +void updateConditions()
// }

public class Helicopter extends Aircraft {

  public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
    super(p_id, p_name, p_coordinate);
    this.tag = this.getClass().getSimpleName()
        + "#" + p_name
        + "(" + p_id + ")";
  }

  @Override
  public void updateConditions() {
    String currentWeather = weatherTower.getWeather(coordinates);

    // Simulator.announce(currentWeather + " at "
    // + getCoordinates().getLongitude() + ","
    // + getCoordinates().getLatitude() + ","
    // + getCoordinates().getHeight()); // for debugging

    // • Helicopter:
    // ◦ SUN - Longitude increases with 10, Height increases with 2
    // ◦ RAIN - Longitude increases with 5
    // ◦ FOG - Longitude increases with 1
    // ◦ SNOW - Height decreases with 12

    if (currentWeather.equals("SUN")) {
      Simulator.announce(getTag() + ": This is hot.");
      updateLongitude(10);
      updateHeight(2);
    } else if (currentWeather.equals("RAIN")) {
      Simulator.announce(getTag() + ": Heavy rain! Watch out for turbulence.");
      updateLongitude(5);
    } else if (currentWeather.equals("FOG")) {
      Simulator.announce(getTag() + ": The fog is thick. Navigating carefully.");
      updateLongitude(1);
    } else if (currentWeather.equals("SNOW")) {
      Simulator.announce(getTag() + ": My rotor is going to freeze!");
      updateHeight(-12);
    }

    // Simulator.announce("New coordinates: "
    // + getCoordinates().getLongitude() + ","
    // + getCoordinates().getLatitude() + ","
    // + getCoordinates().getHeight() + "\n"); // for debugging

    if (coordinates.getHeight() <= 0) {
      Simulator.announce(getTag() + " landing.");
      weatherTower.addToUnregisterQueue(this);
    }

  }
}
