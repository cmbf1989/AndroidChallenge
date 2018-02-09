package com.example.frontkom.androidchallenge.Controllers;

import android.content.Context;

import com.example.frontkom.androidchallenge.Interfaces.ListViewItem;
import com.example.frontkom.androidchallenge.Models.NewsModel;
import com.example.frontkom.androidchallenge.Views.AppView;

import java.util.List;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public class NewsFeedController extends AppController {

    String language = "pt";

    public NewsFeedController(AppView view)
    {
        super(view);
        this.model = factory.createNewsModel(view);
    }

    public void setLanguage(String lang)
    {
        this.language = lang;
    }

    public void requestNews(String country)
    {
        ((NewsModel) this.model).requestNews(country);
    }

    public List<ListViewItem> getNews()
    {
        return ((NewsModel) this.model).getNews();
    }
}
