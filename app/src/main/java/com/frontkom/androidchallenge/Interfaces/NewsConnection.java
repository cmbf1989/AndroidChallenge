package com.frontkom.androidchallenge.Interfaces;

import com.frontkom.androidchallenge.POJO.NewsDataFeed;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cesar Ferreira on 09/02/2018.
 */

public interface NewsConnection {

    @GET("/v2/top-headlines/")
    Call<NewsDataFeed> getNews(@Query("country") String country, @Query("apiKey") String apiKey);
}
