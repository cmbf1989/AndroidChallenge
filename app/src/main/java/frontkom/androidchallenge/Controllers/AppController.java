package frontkom.androidchallenge.Controllers;

import frontkom.androidchallenge.DataSource.DataSource;
import frontkom.androidchallenge.Factories.Factory;
import frontkom.androidchallenge.Interfaces.IObserver;
import frontkom.androidchallenge.Models.AppModel;
import frontkom.androidchallenge.Views.AppView;

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
