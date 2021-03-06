package frontkom.androidchallenge.Controllers;

import frontkom.androidchallenge.Dialogs.SettingsDialogView;
import frontkom.androidchallenge.Interfaces.IListViewItem;
import frontkom.androidchallenge.Models.NewsModel;
import frontkom.androidchallenge.POJO.ConfigSettings;
import frontkom.androidchallenge.Views.AppView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public class NewsController extends AppController {


    NewsModel model = null;

    public NewsController(AppView view)
    {
        super(view);
        this.model =  (NewsModel) factory.createNewsModel(view);
    }

    /**
     *  This method requests news to the model to make an http request to get the news (JSON)
     */
    public void requestNews()
    {
        model.requestNews();
    }

    /**
     *  This method gets the news from memory and returns it in a list
     */
    public List<IListViewItem> getNews()
    {
        return model.getNews();
    }

    /**
     *  Sets the country id to search the news
     */
    public void setCountry(String id, String name) {
        this.model.setCountryId(id, name);
    }

    public String getCountry() {
        return this.model.getCountry();
    }

    public ConfigSettings getSettings(InputStream is) {
        return model.getSettings(is);
    }
}
