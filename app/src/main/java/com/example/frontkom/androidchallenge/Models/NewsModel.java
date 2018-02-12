package com.example.frontkom.androidchallenge.Models;

import android.util.Log;

import com.example.frontkom.androidchallenge.DataSource.DataSource;
import com.example.frontkom.androidchallenge.Dialogs.SettingsDialogView;
import com.example.frontkom.androidchallenge.Helpers.FileReaderJSON;
import com.example.frontkom.androidchallenge.Interfaces.IObserver;
import com.example.frontkom.androidchallenge.Interfaces.IListViewItem;
import com.example.frontkom.androidchallenge.Interfaces.NewsConnection;
import com.example.frontkom.androidchallenge.Network.ServiceGenerator;
import com.example.frontkom.androidchallenge.POJO.Article;
import com.example.frontkom.androidchallenge.POJO.ConfigSettings;
import com.example.frontkom.androidchallenge.POJO.NewsDataFeed;
import com.example.frontkom.androidchallenge.R;
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

        String config_settings = FileReaderJSON.getJSON(is, R.raw.datasource);
        Gson converter =  new Gson();
        ConfigSettings settings = converter.fromJson(config_settings, ConfigSettings.class);

        return  settings;
    }
}
