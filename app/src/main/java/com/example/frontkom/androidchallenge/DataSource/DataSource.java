package com.example.frontkom.androidchallenge.DataSource;

import com.example.frontkom.androidchallenge.Factories.Factory;

/**
 * Created by Cesar Ferreira on 09/02/2018.
 */

public class DataSource {

    private static DataSource mInstance = null;
    private String country_id = "pt";
    public DataSource() {

    }

    public static DataSource getInstance() {
        if (mInstance == null) {
            mInstance = new DataSource();
        }
        return mInstance;
    }

    public String getCountryId() {
        return this.country_id;
    }

    public void setCountryId(String country_id) {
        this.country_id = country_id;
    }
}
