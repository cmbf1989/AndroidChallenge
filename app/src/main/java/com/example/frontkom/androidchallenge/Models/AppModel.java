package com.example.frontkom.androidchallenge.Models;

import com.example.frontkom.androidchallenge.Interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public abstract class AppModel {

    List<IObserver> observers;

    public AppModel() {
        this.observers = new ArrayList<>();
    }

    public AppModel(List<IObserver> observers) {
        this.observers = observers;
    }

    public AppModel(IObserver obs) {
        this.observers = new ArrayList<>();
        this.observers.add(obs);
    }
    void attachObserver(IObserver obs)
    {
        observers.add(obs);
    }

    void dettachObserver(IObserver obs)
    {
        observers.remove(obs);
    }

    public void notifyAllObservers(){
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}
