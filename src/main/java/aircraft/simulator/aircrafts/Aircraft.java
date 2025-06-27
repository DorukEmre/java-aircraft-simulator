package aircraft.simulator.aircrafts;

/**
 * Represents a base class for all aircraft types in the simulation.
 * This class extends Flyable and provides common properties and methods for all
 * aircraft.
 * It includes methods to update the aircraft's coordinates based on weather
 * conditions.
 */
public class Aircraft extends Flyable {
  protected long id;
  protected String name;
  protected Coordinates coordinates;
  protected String tag; // Type#Name(Id), ie: Balloon#B1(1)

  protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
    this.id = p_id;
    this.name = p_name;
    this.coordinates = p_coordinate;
  }

  @Override
  public void updateConditions() {
    // keep empty method to allow Aircraft to be a concrete class
  }

  // Getters

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public String getTag() {
    return tag;
  }

  // Setters

  @Override
  protected void updateLongitude(int change) {
    getCoordinates().setLongitude(coordinates.getLongitude() + change);
  }

  @Override
  protected void updateLatitude(int change) {
    getCoordinates().setLatitude(coordinates.getLatitude() + change);
  }

  @Override
  protected void updateHeight(int change) {
    int newHeight = coordinates.getHeight() + change;
    if (newHeight > 100) {
      newHeight = 100;
    } else if (newHeight < 0) {
      newHeight = 0;
    }
    getCoordinates().setHeight(newHeight);
  }
}
