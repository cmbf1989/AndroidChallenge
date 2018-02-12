package com.frontkom.androidchallenge.Models;

import com.frontkom.androidchallenge.DataSource.DataSource;
import com.frontkom.androidchallenge.Factories.Factory;
import com.frontkom.androidchallenge.Interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public abstract class AppModel {

    protected List<IObserver> observers;
    protected Factory factory = Factory.getInstance();
    protected DataSource datasource = DataSource.getInstance();

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
