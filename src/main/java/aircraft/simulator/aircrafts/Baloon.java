package aircraft.simulator.aircrafts;

/**
 * Represents a balloon in the simulation.
 * Inherits from the Aircraft class and implements own specific behavior.
 */
public class Baloon extends Aircraft {

  public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
    super(p_id, p_name, p_coordinate);
    this.tag = this.getClass().getSimpleName()
        + "#" + p_name
        + "(" + p_id + ")";
  }

  /**
   * Updates coordinates of the balloon based on the current weather:
   * - SUN: Increases longitude by 2 and height by 4.
   * - RAIN: Decreases height by 5.
   * - FOG: Decreases height by 3.
   * - SNOW: Decreases height by 15.
   * Lands if height drops to 0 or below, and unregisters from tower.
   */
  @Override
  public void updateConditions() {
    Simulation sim = Simulation.getInstance();
    String currentWeather = weatherTower.getWeather(coordinates);

    // sim.announce(currentWeather + " at "
    // + getCoordinates().getLongitude() + ","
    // + getCoordinates().getLatitude() + ","
    // + getCoordinates().getHeight()); // for debugging

    if (currentWeather.equals("SUN")) {
      sim.announce(getTag()
          + ": Let's enjoy the good weather and take some pics.");
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
