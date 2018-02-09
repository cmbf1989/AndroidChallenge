package com.example.frontkom.androidchallenge.Views;

import android.support.v7.app.AppCompatActivity;

import com.example.frontkom.androidchallenge.DataSource.DataSource;
import com.example.frontkom.androidchallenge.Factories.Factory;
import com.example.frontkom.androidchallenge.Interfaces.IObserver;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public abstract class AppView  extends AppCompatActivity implements IObserver {
    protected Factory factory = Factory.getInstance();
    protected DataSource app_data = DataSource.getInstance();
}
