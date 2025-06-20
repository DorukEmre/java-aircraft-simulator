package demre.avaj.simulator.tower;

import demre.avaj.simulator.aircrafts.Coordinates;
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
  WeatherTower() {
    super();
  }

  // Getter
  public String getWeather(Coordinates p_coordinates) {
    return (WeatherProvider.getInstance().getCurrentWeather(p_coordinates));
  }

  // Setter
  public void changeWeather() {
    // Advance simulation turn and notify observers that weather state changed
    if (Simulation.getInstance().getTurn() > 0) {
      Simulation.getInstance().decreaseTurn();
      conditionChanged();
    }
  }
}
