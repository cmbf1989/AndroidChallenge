package com.frontkom.androidchallenge.POJO;

/**
 * Created by Cesar Ferreira on 10/02/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.google.gson.annotations.Expose;

public class ConfigSettings {

    @SerializedName("category")
    @Expose
    private List<String> category = null;
    @SerializedName("language")
    @Expose
    private List<String> language = null;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}