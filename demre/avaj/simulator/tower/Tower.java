package demre.avaj.simulator.tower;

import java.util.ArrayList;
import java.util.List;

import demre.avaj.simulator.aircrafts.Flyable;

// Subject in Observer behavioral pattern
// Tower acts as a subject/publisher, notifying Flyable objects (observers/subscribers) of changes

/**
 * Tower allows Flyable objects to register and unregister themselves, and
 * notifies them when the weather conditions change.
 */
public class Tower {
  // List of registered Flyable observers
  private List<Flyable> observers;
  // Queue for Flyable observers to be unregistered after notification cycle
  private List<Flyable> observersToUnregister;

  Tower() {
    this.observers = new ArrayList<Flyable>();
    this.observersToUnregister = new ArrayList<Flyable>();
  }

  public List<Flyable> getObservers() {
    return observers;
  }

  public void register(Flyable p_flyable) {
    observers.add(p_flyable);
  }

  public void unregister(Flyable p_flyable) {
    observers.remove(p_flyable);
  }

  /**
   * Notify all registered observers (Flyable objects) about a change in weather
   * conditions.
   * Unregistered observers are processed after all notifications to avoid
   * concurrent modification issues.
   */
  protected void conditionChanged() {
    // Notify all observers about a change in state
    if (observers != null) {
      for (Flyable observer : observers) {
        observer.updateConditions();
      }
      processUnregisterQueue();
    }
  }

  /**
   * Process the queue of Flyable observers to be unregistered.
   * This method is called after all observers have been notified to ensure that
   * unregistration does not interfere with the notification process.
   */
  private void processUnregisterQueue() {
    for (Flyable observer : observersToUnregister) {
      unregister(observer);
    }
    observersToUnregister.clear();
  }

  /**
   * Add a Flyable object to the queue for unregistration.
   * This is used to defer unregistration until after all observers have been
   * notified.
   * 
   * @param p_flyable the Flyable object to unregister
   */
  public void addToUnregisterQueue(Flyable p_flyable) {
    observersToUnregister.add(p_flyable);
  }

}
