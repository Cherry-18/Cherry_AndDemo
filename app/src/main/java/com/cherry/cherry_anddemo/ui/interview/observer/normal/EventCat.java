package com.cherry.cherry_anddemo.ui.interview.observer.normal;

/**
 * 相当于一个 ObservableManager（被观察者管理类）
 */

public class EventCat {

    private static EventCat eventCat;
    private ConcreteObservable concreteObservable;

    public EventCat() {
        concreteObservable = new ConcreteObservable();
    }

    public static EventCat getDefault(){
        if (eventCat == null){
            synchronized (EventCat.class){
                if (eventCat == null){
                    eventCat = new EventCat();
                }
            }
        }
        return eventCat;
    }

    public void register(Observer observer){
        concreteObservable.addObserver(observer);
    }

    public void unregister(Observer observer){
        concreteObservable.removeObserver(observer);
    }

    public void post(Object object){
        concreteObservable.notifyObserver(object);
    }
}
