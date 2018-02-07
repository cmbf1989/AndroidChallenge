package com.example.frontkom.androidchallenge.Controllers;

import com.example.frontkom.androidchallenge.Models.AppModel;
import com.example.frontkom.androidchallenge.Views.AppView;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public abstract class AppController {

    protected AppModel model;
    protected AppView view;
    public  void attachModel(AppModel model)
    {
        this.model = model;
    }

    public  void attachView(AppView view)
    {
        this.view = view;
    }
}
