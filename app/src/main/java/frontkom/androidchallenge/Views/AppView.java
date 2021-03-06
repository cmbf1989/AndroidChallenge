package frontkom.androidchallenge.Views;

import android.support.v7.app.AppCompatActivity;

import frontkom.androidchallenge.Controllers.AppController;
import frontkom.androidchallenge.DataSource.DataSource;
import frontkom.androidchallenge.Factories.Factory;
import frontkom.androidchallenge.Interfaces.IObserver;

/**
 * Created by Cesar Ferreira on 07/02/2018. Abstract super class that holds some methods to be implemented and a factory variable
 * Also implements IObserver interface
 */

public abstract class AppView  extends AppCompatActivity implements IObserver {
    protected Factory factory = Factory.getInstance();
    public abstract AppController getController();
}
