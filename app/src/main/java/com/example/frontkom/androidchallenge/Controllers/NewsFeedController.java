package com.example.frontkom.androidchallenge.Controllers;

import com.example.frontkom.androidchallenge.Interfaces.IListViewItem;
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

    /**
     *  This method requests news to the model to make an http request to get the news (JSON)
     */
    public void requestNews()
    {
        ((NewsModel) this.model).requestNews();
    }

    /**
     *  This method gets the news from memory and returns it in a list
     */
    public List<IListViewItem> getNews()
    {
        return ((NewsModel) this.model).getNews();
    }

    /**
     *  Sets the country id to search the news
     */
    public void setCountry(String country) {
        ((NewsModel) this.model).setCountryId(country);
    }
}
