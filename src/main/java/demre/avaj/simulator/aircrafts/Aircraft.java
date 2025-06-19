package demre.avaj.simulator.aircrafts;

// class Aircraft
// {
// #long id
// #string name
// #Coordinate coordinates
// #Aircraft(long p_id, string p_name, Coordinates p_coordinate)
// }

public class Aircraft extends Flyable {
  protected long id;
  protected String name;
  protected Coordinates coordinates; // Coordinate(s)???

  protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
    this.id = p_id;
    this.name = p_name;
    this.coordinates = p_coordinate;
  }

  @Override
  public void updateConditions() {
    // keep empty method to allow Aircraft to be a concrete class
  }
}
