package aircraft.simulator.aircrafts;

/**
 * Represents a helicopter in the simulation.
 * Inherits from the Aircraft class and implements own specific behavior.
 */
public class Helicopter extends Aircraft {

  public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
    super(p_id, p_name, p_coordinate);
    this.tag = this.getClass().getSimpleName()
        + "#" + p_name
        + "(" + p_id + ")";
  }

  /**
   * Updates coordinates of the helicopter based on the current weather:
   * - SUN: Increases longitude by 10 and height by 2.
   * - RAIN: Increases longitude by 5.
   * - FOG: Increases longitude by 1.
   * - SNOW: Decreases height by 12.
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
      sim.announce(getTag() + ": This is hot.");
      updateLongitude(10);
      updateHeight(2);

    } else if (currentWeather.equals("RAIN")) {
      sim.announce(getTag() + ": Heavy rain! Watch out for turbulence.");
      updateLongitude(5);

    } else if (currentWeather.equals("FOG")) {
      sim.announce(getTag() + ": The fog is thick. Navigating carefully.");
      updateLongitude(1);

    } else if (currentWeather.equals("SNOW")) {
      sim.announce(getTag() + ": My rotor is going to freeze!");
      updateHeight(-12);
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
