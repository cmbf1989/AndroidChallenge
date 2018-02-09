package com.example.frontkom.androidchallenge.Models;

import android.util.Log;

import com.example.frontkom.androidchallenge.Interfaces.IObserver;
import com.example.frontkom.androidchallenge.Interfaces.ListViewItem;
import com.example.frontkom.androidchallenge.Interfaces.NewsConnection;
import com.example.frontkom.androidchallenge.Network.ServiceGenerator;
import com.example.frontkom.androidchallenge.POJO.Article;
import com.example.frontkom.androidchallenge.POJO.NewsDataFeed;

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

    public void requestNews(String country)
    {
        NewsConnection news_connection = ServiceGenerator.createService(NewsConnection.class, null, null);



        Call<NewsDataFeed> call =   news_connection.getNews(country, "e9f236f020e5427aa5f6b1ff104e955e");
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
                Log.d("APIREQUEST", t.getMessage());
            }
        });
    }

    public List<ListViewItem> getNews()
    {
        return (List<ListViewItem>)(List<?>) articles;
    }
}
