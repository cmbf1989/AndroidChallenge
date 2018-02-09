package com.example.frontkom.androidchallenge.Factories;

import android.content.Context;

import com.example.frontkom.androidchallenge.Controllers.AppController;
import com.example.frontkom.androidchallenge.Controllers.NewsFeedController;
import com.example.frontkom.androidchallenge.Interfaces.IObserver;
import com.example.frontkom.androidchallenge.Models.AppModel;
import com.example.frontkom.androidchallenge.Models.NewsModel;
import com.example.frontkom.androidchallenge.Views.AppView;

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
        return new NewsFeedController(view);
    }
}