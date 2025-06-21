package demre.avaj.simulator.aircrafts;

import demre.avaj.simulator.Simulator;

// class JetPlane
// {
// +JetPlane(long p_id, string p_name, Coordinates p_coordinate)
// +void updateConditions()
// }

public class JetPlane extends Aircraft {

  public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
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

    // • JetPlane:
    // ◦ SUN - Latitude increases with 10, Height increases with 2
    // ◦ RAIN - Latitude increases with 5
    // ◦ FOG - Latitude increases with 1
    // ◦ SNOW - Height decreases with 7

    if (currentWeather.equals("SUN")) {
      Simulator.announce(getTag() + ": Clear skies ahead. Perfect for flying.");
      updateLatitude(10);
      updateHeight(2);
    } else if (currentWeather.equals("RAIN")) {
      Simulator.announce(getTag() + ": It's raining. Better watch out for lightings.");
      updateLatitude(5);
    } else if (currentWeather.equals("FOG")) {
      Simulator.announce(getTag() + ": Visibility is low. Proceeding with caution.");
      updateLatitude(1);
    } else if (currentWeather.equals("SNOW")) {
      Simulator.announce(getTag() + ": OMG! Winter is coming!");
      updateHeight(-7);
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
