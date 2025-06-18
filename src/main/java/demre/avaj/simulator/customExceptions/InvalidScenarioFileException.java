package main.java.demre.avaj.simulator.customExceptions;

public class InvalidScenarioFileException extends IllegalArgumentException {
  // Default constructor
  public InvalidScenarioFileException() {
    super("Invalid scenario file.");
  }

  // Constructor with custom message
  public InvalidScenarioFileException(String message) {
    super(message);
  }

  // Constructor with custom message and cause
  public InvalidScenarioFileException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause
  public InvalidScenarioFileException(Throwable cause) {
    super(cause);
  }

}
