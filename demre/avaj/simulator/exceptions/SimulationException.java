package demre.avaj.simulator.exceptions;

/**
 * SimulationException is thrown when an error occurs during the simulation.
 */
public class SimulationException extends RuntimeException {
  // Default constructor
  public SimulationException() {
    super("Simulation error occurred.");
  }

  // Constructor with custom message
  public SimulationException(String message) {
    super(message);
  }

  // Constructor with custom message and cause
  public SimulationException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause
  public SimulationException(Throwable cause) {
    super(cause);
  }
}
