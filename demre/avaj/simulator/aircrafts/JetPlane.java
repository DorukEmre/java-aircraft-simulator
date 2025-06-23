package demre.avaj.simulator.aircrafts;

/**
 * Represents a jet plane in the simulation.
 * Inherits from the Aircraft class and implements own specific behavior.
 */
public class JetPlane extends Aircraft {

  public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
    super(p_id, p_name, p_coordinate);
    this.tag = this.getClass().getSimpleName()
        + "#" + p_name
        + "(" + p_id + ")";
  }

  /**
   * Updates coordinates of the helicopter based on the current weather:
   * - SUN: Increases latitude by 10 and height by 2.
   * - RAIN: Increases latitude by 5.
   * - FOG: Increases latitude by 1.
   * - SNOW: Decreases height by 7.
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
      sim.announce(getTag() + ": Clear skies ahead. Perfect for flying.");
      updateLatitude(10);
      updateHeight(2);

    } else if (currentWeather.equals("RAIN")) {
      sim.announce(getTag()
          + ": It's raining. Better watch out for lightings.");
      updateLatitude(5);

    } else if (currentWeather.equals("FOG")) {
      sim.announce(getTag() + ": Visibility is low. Proceeding with caution.");
      updateLatitude(1);

    } else if (currentWeather.equals("SNOW")) {
      sim.announce(getTag() + ": OMG! Winter is coming!");
      updateHeight(-7);
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
