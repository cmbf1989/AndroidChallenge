package com.example.frontkom.androidchallenge.Controllers;

import com.example.frontkom.androidchallenge.DataSource.DataSource;
import com.example.frontkom.androidchallenge.Factories.Factory;
import com.example.frontkom.androidchallenge.Interfaces.IObserver;
import com.example.frontkom.androidchallenge.Models.AppModel;
import com.example.frontkom.androidchallenge.Views.AppView;

import java.util.ArrayList;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public abstract class AppController {

    protected AppModel model;
    protected AppView view;
    protected Factory  factory = Factory.getInstance();

    public AppController(AppView view) {
        this.view = view;
    }
    public  void attachModel(AppModel model)
    {
        this.model = model;
    }

    public  void attachView(AppView view)
    {
        this.view = view;
    }
}
