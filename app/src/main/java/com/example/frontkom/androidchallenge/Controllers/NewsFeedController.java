package com.example.frontkom.androidchallenge.Controllers;

import android.content.Context;

import com.example.frontkom.androidchallenge.DataSource.DataSource;
import com.example.frontkom.androidchallenge.Interfaces.ListViewItem;
import com.example.frontkom.androidchallenge.Models.NewsModel;
import com.example.frontkom.androidchallenge.Views.AppView;

import java.util.List;

/**
 * Created by Cesar Ferreira on 07/02/2018.
 */

public class NewsFeedController extends AppController {



    public NewsFeedController(AppView view)
    {
        super(view);
        this.model = factory.createNewsModel(view);
    }

    public void requestNews()
    {
        ((NewsModel) this.model).requestNews();
    }

    public List<ListViewItem> getNews()
    {
        return ((NewsModel) this.model).getNews();
    }

    public void setCountry(String country) {
        ((NewsModel) this.model).setCountryId(country);
    }
}
