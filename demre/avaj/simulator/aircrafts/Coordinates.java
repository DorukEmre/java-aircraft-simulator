package demre.avaj.simulator.aircrafts;

/**
 * Represents the coordinates of a three dimensional point in the simulation.
 * This class encapsulates the longitude, latitude, and height.
 */
public class Coordinates {

  private int longitude;
  private int latitute;
  private int height;

  // Constructor

  Coordinates(int p_longitude, int p_latitude, int p_height) {
    this.longitude = p_longitude;
    this.latitute = p_latitude;
    this.height = p_height;
  }

  // Getters

  public int getLongitude() {
    return longitude;
  }

  public int getLatitude() {
    return latitute;
  }

  public int getHeight() {
    return height;
  }

  // Setters

  public void setLongitude(int p_longitude) {
    this.longitude = p_longitude;
  }

  public void setLatitude(int p_latitude) {
    this.latitute = p_latitude;
  }

  public void setHeight(int p_height) {
    this.height = p_height;
  }

}
