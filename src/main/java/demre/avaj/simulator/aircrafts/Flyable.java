package demre.avaj.simulator.aircrafts;

import demre.avaj.simulator.tower.WeatherTower;

// abstract Flyable
// {
// #WeatherTower weatherTower
// +abstract void updateConditions()
// +registerTower(WeatherTower* p_tower)
// }

// If Flyable was an 'interface', every method would be implicitely public abstract.
// As we have both abstract and concrete methods, it must be an abstract class and the relationship in the uml '<|..' is incorrect. 
// '<|..' is for interface implementation

public abstract class Flyable {
  protected WeatherTower weatherTower;

  // Observer defines an update() method that will be called by the Subject
  // whenever there is a change.
  public abstract void updateConditions();

  // puml doesn't specify return type
  public void registerTower(WeatherTower p_tower) {
    this.weatherTower = p_tower;
    p_tower.register(this);
  }

  // Additional functions
  public abstract String getName();

  public abstract long getId();

  public abstract Coordinates getCoordinates();

  protected abstract void updateLongitude(int change);

  protected abstract void updateLatitude(int change);

  protected abstract void updateHeight(int change);
}
