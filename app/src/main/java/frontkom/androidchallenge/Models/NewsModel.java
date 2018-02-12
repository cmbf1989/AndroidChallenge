package frontkom.androidchallenge.Models;

import frontkom.androidchallenge.DataSource.DataSource;
import frontkom.androidchallenge.Helpers.FileReaderString;
import frontkom.androidchallenge.Interfaces.IObserver;
import frontkom.androidchallenge.Interfaces.IListViewItem;
import frontkom.androidchallenge.Interfaces.NewsConnection;
import frontkom.androidchallenge.Network.ServiceGenerator;
import frontkom.androidchallenge.POJO.Article;
import frontkom.androidchallenge.POJO.ConfigSettings;
import frontkom.androidchallenge.POJO.NewsDataFeed;
import frontkom.androidchallenge.R;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cesar Ferreira on 09/02/2018.
 */

public class NewsModel extends AppModel{


    protected List<Article> articles;

    public NewsModel(IObserver observer)
    {
        super(observer);
    }

    public NewsModel()
    {
        super();
    }

    /**
     * Makes http request using retrofit. creates async thread to notify the view when done
     */
    public void requestNews()
    {
        String country = datasource.getCountryId();
        NewsConnection news_connection = ServiceGenerator.createService(NewsConnection.class, null, null);

        Call<NewsDataFeed> call =   news_connection.getNews(country, ServiceGenerator.getApiKey());
        call.enqueue(new Callback<NewsDataFeed>() {
            @Override
            public void onResponse(Call<NewsDataFeed> call, Response<NewsDataFeed> response) {
                if (response.isSuccessful()) {
                    NewsDataFeed data = (NewsDataFeed) response.body();
                    articles = data.getArticles();
                    notifyAllObservers();
                } else {

                }
            }

            @Override
            public void onFailure(Call<NewsDataFeed> call, Throwable t) {
                // something went completely south (like no internet connection)

            }
        });
    }

    public List<IListViewItem> getNews()
    {
        return (List<IListViewItem>)(List<?>) articles;
    }


    public void setCountryId(String id, String name)
    {
        datasource.setCountryId(id);
        datasource.setCountryName(name);
    }

    public String getCountry() {
        return  DataSource.getInstance().getCountryName();
    }

    public ConfigSettings getSettings(InputStream is) {

        String config_settings = FileReaderString.getFileString(is, R.raw.datasource);
        Gson converter =  new Gson();
        ConfigSettings settings = converter.fromJson(config_settings, ConfigSettings.class);

        return  settings;
    }
}
