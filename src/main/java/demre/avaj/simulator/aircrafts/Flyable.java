package demre.avaj.simulator.aircrafts;

// abstract Flyable
// {
// #WeatherTower weatherTower
// +abstract void updateConditions()
// +registerTower(WeatherTower* p_tower)
// }

public abstract class Flyable {
  // protected WeatherTower weatherTower;

  public abstract void updateConditions();

  // puml doesn't specify return type
  // public void registerTower(WeatherTower tower) {
  // }
}
