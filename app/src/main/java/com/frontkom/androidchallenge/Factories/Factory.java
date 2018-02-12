package com.frontkom.androidchallenge.Factories;

import com.frontkom.androidchallenge.Controllers.AppController;
import com.frontkom.androidchallenge.Controllers.NewsController;
import com.frontkom.androidchallenge.Dialogs.SettingsDialogView;
import com.frontkom.androidchallenge.Interfaces.IObserver;
import com.frontkom.androidchallenge.Models.AppModel;
import com.frontkom.androidchallenge.Models.NewsModel;
import com.frontkom.androidchallenge.POJO.Country;
import com.frontkom.androidchallenge.Views.AppView;

import java.util.List;

/**
 * Created by Cesar Ferreira on 09/02/2018.
 */

public class Factory {

    private static Factory mInstance = null;

    public Factory() {

    }

    public static Factory getInstance() {
        if (mInstance == null) {
            mInstance = new Factory();
        }
        return mInstance;
    }

    public AppModel createNewsModel(IObserver observer)
    {
        return new NewsModel(observer);
    }

    public AppController createNewsController(AppView view)
    {
        return new NewsController(view);
    }

    public SettingsDialogView createSettingsDialog(AppView activity, List<Country> countries)
    {
        return new SettingsDialogView(activity, countries);
    }
}