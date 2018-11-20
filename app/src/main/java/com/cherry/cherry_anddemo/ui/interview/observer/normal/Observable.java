package com.cherry.cherry_anddemo.ui.interview.observer.normal;

public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver(Object obj);

}
