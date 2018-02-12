package frontkom.androidchallenge.Factories;

import frontkom.androidchallenge.Controllers.AppController;
import frontkom.androidchallenge.Controllers.NewsController;
import frontkom.androidchallenge.Dialogs.SettingsDialogView;
import frontkom.androidchallenge.Interfaces.IObserver;
import frontkom.androidchallenge.Models.AppModel;
import frontkom.androidchallenge.Models.NewsModel;
import frontkom.androidchallenge.POJO.Country;
import frontkom.androidchallenge.Views.AppView;

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