package demre.avaj.simulator.customExceptions;

/**
 * InvalidScenarioException is thrown when the scenario file is invalid.
 * This exception extends IllegalArgumentException to indicate that the
 * provided scenario does not meet the expected format or criteria.
 */
public class InvalidScenarioException extends IllegalArgumentException {
  // Default constructor
  public InvalidScenarioException() {
    super("Invalid scenario file.");
  }

  // Constructor with custom message
  public InvalidScenarioException(String message) {
    super(message);
  }

  // Constructor with custom message and cause
  public InvalidScenarioException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause
  public InvalidScenarioException(Throwable cause) {
    super(cause);
  }

}
