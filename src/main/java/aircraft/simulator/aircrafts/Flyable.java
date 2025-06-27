package aircraft.simulator.aircrafts;

import aircraft.simulator.tower.WeatherTower;

// If Flyable was an 'interface', every method would be implicitely public abstract.
// As we have both abstract and concrete methods, it must be an abstract class and the relationship in the uml '<|..' is incorrect. 
// '<|..' is for interface implementation

/**
 * Represents a flyable object in the simulation.
 * This class serves as a base for all aircraft types that can interact with the
 * weather tower.
 * Each subclass must implement the updateConditions method to define its
 * specific behavior.
 */
public abstract class Flyable {
  protected WeatherTower weatherTower;

  // Observer defines an update() method that will be called by the Subject
  // whenever there is a change.
  public abstract void updateConditions();

  public void registerTower(WeatherTower p_tower) {
    this.weatherTower = p_tower;
    p_tower.register(this);
  }

  // Getters - Setters

  public abstract String getName();

  public abstract long getId();

  public abstract Coordinates getCoordinates();

  protected abstract void updateLongitude(int change);

  protected abstract void updateLatitude(int change);

  protected abstract void updateHeight(int change);
}
