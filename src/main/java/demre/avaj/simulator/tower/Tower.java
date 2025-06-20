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

  Tower() {
    this.observers = new ArrayList<Flyable>();
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
    }
  }

}
