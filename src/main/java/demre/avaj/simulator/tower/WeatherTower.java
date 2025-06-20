package demre.avaj.simulator.tower;

import demre.avaj.simulator.aircrafts.Coordinates;
import demre.avaj.simulator.weather.WeatherProvider;

// class WeatherTower
// {
// +string getWeather(Coordinates p_coordinates)
// +void changeWeather()
// }

// ConcreteSubject

public class WeatherTower extends Tower {
  // Constructor
  WeatherTower() {
    super();
  }

  // getter
  public String getWeather(Coordinates p_coordinates) {
    return (WeatherProvider.getInstance().getCurrentWeather(p_coordinates));
  }

  // setter
  public void changeWeather() {

  }
}
