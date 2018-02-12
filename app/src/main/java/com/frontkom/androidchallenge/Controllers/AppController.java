package com.frontkom.androidchallenge.Controllers;

import com.frontkom.androidchallenge.DataSource.DataSource;
import com.frontkom.androidchallenge.Factories.Factory;
import com.frontkom.androidchallenge.Interfaces.IObserver;
import com.frontkom.androidchallenge.Models.AppModel;
import com.frontkom.androidchallenge.Views.AppView;

import java.util.ArrayList;

/**
 * Created by Cesar Ferreira on 07/02/2018. Main abstract controller class that all controllers extend.
 * Holds some generic methods
 */

public abstract class AppController {

    protected AppView view;
    protected Factory  factory = Factory.getInstance();

    public AppController(AppView view) {
        this.view = view;
    }


    public  void attachView(AppView view)
    {
        this.view = view;
    }
}
