package com.cherry.cherry_anddemo.ui.interview.observer.normal;

import java.util.ArrayList;

public class ConcreteObservable implements Observable{

    public ArrayList<Observer> mObserverList;

    @Override
    public void addObserver(Observer observer) {
        if (mObserverList == null){
            mObserverList = new ArrayList<>();
        }
        mObserverList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer == null && mObserverList.size() == 0){
            return;
        }
        mObserverList.remove(observer);
    }

    @Override
    public void notifyObserver(Object obj) {
        if (obj == null && mObserverList.size() == 0){
            return;
        }
        for (Observer observer : mObserverList){
            observer.update(obj);
        }
    }
}
