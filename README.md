## Aircraft and Weather Simulator in Java

Java-based aircraft simulation program that models the behavior of different aircraft types interacting with a weather system.

This project uses the object-oriented design patterns Singleton, Factory, and Observer, to ensure modularity and maintainability.

It includes a UML class diagram to illustrate the relationships between core components and visualise the use of the design patterns and the overall architecture.

### How the Simulation Works

The simulation reads a scenario file describing the aircraft and their initial positions, then simulates their responses to changing weather conditions over a series of turns.

## Features
- Aircraft Types: Each aircraft class has unique responses to weather.
- Weather System: Weather changes each turn.
- Observer Pattern: Aircraft register with a weather tower and are notified of weather changes.
- Exception Handling: Error checking for scenario file format and simulation errors.

## Project Structure
```
src/
  main/
    java/
      aircraft/
        simulator/
          Simulator.java           # Main entry point
          aircrafts/               # Aircraft classes and simulation logic
          exceptions/              # Custom exception classes
          factory/                 # AircraftFactory (singleton - factory pattern)
          tower/                   # Tower and WeatherTower (observer pattern)
          weather/                 # WeatherProvider (singleton)
    resources/
      avaj_uml.png                 # UML diagram
      global.puml                  # PlantUML source
scenario.txt                       # Example scenario file
Makefile                           # Build and run automation
README.md
```
## How to Build and Run
Compile and run using scenario.txt as input and writing results to simulation.txt:
```sh
   make
```
Alternatively, build the project and run the simulation separately:
```sh
   make compile
   make run
```

Clean build artifacts:
```sh
   make clean
```

## Scenario File Format
- The first line is the number of simulation turns (positive integer).
- Each subsequent line describes an aircraft:
    
      <TYPE> <NAME> <LONGITUDE> <LATITUDE> <HEIGHT>

## Design Patterns Used
  - Singleton: For AircraftFactory and WeatherProvider.
  - Observer: Aircraft (observers) register with the WeatherTower (subject) and are notified of weather changes.
  - Factory: AircraftFactory creates aircraft based on type.

## Error Handling
Invalid scenario files or arguments will result in a descriptive error message and program termination.
All exceptions are handled gracefully.

## UML Diagram
See avaj_uml.png and global.puml for the class diagram.
