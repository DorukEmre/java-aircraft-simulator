package demre.avaj.simulator.tower;

import java.util.ArrayList;
import java.util.List;

import demre.avaj.simulator.aircrafts.Flyable;

// class Tower
// {
// - List<Flyable*> observers
// +void register(Flyable* p_flyable)
// +void unregister(Flyable* p_flyable)
// #void conditionChanged()
// }

// Subject in Observer behavioral pattern
// Tower acts as a subject/publisher, notifying Flyable objects (observers/subscribers) of changes

public class Tower {
  private List<Flyable> observers;
  private List<Flyable> observersToUnregister;

  Tower() {
    this.observers = new ArrayList<Flyable>();
    this.observersToUnregister = new ArrayList<Flyable>();
  }

  // == addObserver()
  public void register(Flyable p_flyable) {
    observers.add(p_flyable);
  }

  // == removeObserver()
  public void unregister(Flyable p_flyable) {
    observers.remove(p_flyable);
  }

  // == notifyObservers()
  protected void conditionChanged() {
    // Notify all observers about a change in state
    if (observers != null) {
      for (Flyable observer : observers) {
        observer.updateConditions();
      }
      processUnregisterQueue();
    }
  }

  public void addToUnregisterQueue(Flyable p_flyable) {
    observersToUnregister.add(p_flyable);
  }

  private void processUnregisterQueue() {
    for (Flyable observer : observersToUnregister) {
      unregister(observer);
    }
    observersToUnregister.clear();
  }

  public List<Flyable> getObservers() {
    return observers;
  }

  protected void announce(String message) {
    System.out.println(message);
  }

  public void publishInitialConditions() {
    conditionChanged();
  }

}
