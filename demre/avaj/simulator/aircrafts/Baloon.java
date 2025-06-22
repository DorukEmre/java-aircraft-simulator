package demre.avaj.simulator.aircrafts;

// class Baloon
// {
// +Baloon(long p_id, string p_name, Coordinates p_coordinate)
// +void updateConditions()
// }

public class Baloon extends Aircraft {

  public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
    super(p_id, p_name, p_coordinate);
    this.tag = this.getClass().getSimpleName()
        + "#" + p_name
        + "(" + p_id + ")";
  }

  @Override
  public void updateConditions() {
    Simulation sim = Simulation.getInstance();
    String currentWeather = weatherTower.getWeather(coordinates);

    // sim.announce(currentWeather + " at "
    // + getCoordinates().getLongitude() + ","
    // + getCoordinates().getLatitude() + ","
    // + getCoordinates().getHeight()); // for debugging

    // • Balloon:
    // ◦ SUN - Longitude increases with 2, Height increases with 4
    // ◦ RAIN - Height decreases with 5
    // ◦ FOG - Height decreases with 3
    // ◦ SNOW - Height decreases with 15

    if (currentWeather.equals("SUN")) {
      sim.announce(getTag() + ": Let's enjoy the good weather and take some pics.");
      updateLongitude(2);
      updateHeight(4);
    } else if (currentWeather.equals("RAIN")) {
      sim.announce(getTag() + ": Damn you rain! You messed up my balloon.");
      updateHeight(-5);
    } else if (currentWeather.equals("FOG")) {
      sim.announce(getTag() + ": Can't see anything! We're flying blind.");
      updateHeight(-3);
    } else if (currentWeather.equals("SNOW")) {
      sim.announce(getTag() + ": It's snowing. We're gonna crash.");
      updateHeight(-15);
    }

    // sim.announce("New coordinates: "
    // + getCoordinates().getLongitude() + ","
    // + getCoordinates().getLatitude() + ","
    // + getCoordinates().getHeight() + "\n"); // for debugging

    if (coordinates.getHeight() <= 0) {
      sim.announce(getTag() + " landing.");
      weatherTower.addToUnregisterQueue(this);
    }

  }
}
