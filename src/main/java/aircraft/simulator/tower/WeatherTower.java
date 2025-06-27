package aircraft.simulator.tower;

import aircraft.simulator.aircrafts.Coordinates;
import aircraft.simulator.aircrafts.Flyable;
import aircraft.simulator.aircrafts.Simulation;
import aircraft.simulator.weather.WeatherProvider;

// ConcreteSubject in Observer behavioral pattern

/**
 * WeatherTower allows to get the current weather based on coordinates, change
 * the weather by advancing the simulation turn, and manage the registration and
 * unregistration of flyable objects.
 * It notifies all registered observers (flyable objects) when the weather
 * state changes.
 */
public class WeatherTower extends Tower {

  // Constructor

  public WeatherTower() {
    super();
  }

  // Getter

  /**
   * Returns the current weather at the specified coordinates.
   * 
   * @param p_coordinates the coordinates to get the weather for
   * @return the current weather condition as a String
   */
  public String getWeather(Coordinates p_coordinates) {
    return (WeatherProvider.getInstance().getCurrentWeather(p_coordinates));
  }

  // Setter

  /**
   * Changes the weather by advancing the simulation turn.
   * Increments the turn count in the simulation and notifies all
   * registered observers (flyable objects) that the weather state has changed.
   */
  public void changeWeather() {
    // Advance simulation turn and notify observers that weather state changed
    Simulation sim = Simulation.getInstance();
    if (sim.getTurn() <= sim.getTimesToRun()) {
      sim.nextTurn();
      conditionChanged();
    }
  }

  /**
   * Registers a flyable object to the weather tower.
   * Announce the registration of the flyable object to the simulation.
   * 
   * @param p_flyable the flyable object to register
   */
  @Override
  public void register(Flyable p_flyable) {
    // Tower says: Balloon#B1(1) registered to weather tower.
    super.register(p_flyable);
    Simulation.getInstance().announce("Tower says: "
        + p_flyable.getClass().getSimpleName()
        + "#" + p_flyable.getName()
        + "(" + p_flyable.getId() + ") registered to weather tower.");
  }

  /**
   * Unregisters a flyable object from the weather tower.
   * Announce the unregistration of the flyable object to the simulation.
   * 
   * @param p_flyable the flyable object to unregister
   */
  @Override
  public void addToUnregisterQueue(Flyable p_flyable) {
    // Tower says: Balloon#B1(1) unregistered from weather tower.
    super.addToUnregisterQueue(p_flyable);
    Simulation.getInstance().announce("Tower says: "
        + p_flyable.getClass().getSimpleName()
        + "#" + p_flyable.getName()
        + "(" + p_flyable.getId() + ") unregistered from weather tower.");
  }

}
