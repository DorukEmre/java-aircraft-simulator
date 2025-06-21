package demre.avaj.simulator.tower;

import demre.avaj.simulator.aircrafts.Coordinates;
import demre.avaj.simulator.aircrafts.Flyable;
import demre.avaj.simulator.aircrafts.Simulation;
import demre.avaj.simulator.weather.WeatherProvider;

// class WeatherTower
// {
// +string getWeather(Coordinates p_coordinates)
// +void changeWeather()
// }

// ConcreteSubject in Observer behavioral pattern

public class WeatherTower extends Tower {
  // Constructor
  public WeatherTower() {
    super();
  }

  // Getter
  public String getWeather(Coordinates p_coordinates) {
    return (WeatherProvider.getInstance().getCurrentWeather(p_coordinates));
  }

  // Setter
  public void changeWeather() {
    // Advance simulation turn and notify observers that weather state changed
    Simulation sim = Simulation.getInstance();
    if (sim.getTurn() <= sim.getTimesToRun()) {
      sim.nextTurn();
      conditionChanged();
    }
  }

  @Override
  public void register(Flyable p_flyable) {
    // Tower says: Balloon#B1(1) registered to weather tower.
    super.register(p_flyable);
    announce("Tower says: "
        + p_flyable.getClass().getSimpleName()
        + "#" + p_flyable.getName()
        + "(" + p_flyable.getId() + ") registered to weather tower.");
  }

  @Override
  public void addToUnregisterQueue(Flyable p_flyable) {
    // Tower says: Balloon#B1(1) unregistered from weather tower.
    super.addToUnregisterQueue(p_flyable);
    announce("Tower says: "
        + p_flyable.getClass().getSimpleName()
        + "#" + p_flyable.getName()
        + "(" + p_flyable.getId() + ") unregistered from weather tower.");
  }

}
