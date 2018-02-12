package frontkom.androidchallenge.DataSource;

import frontkom.androidchallenge.Factories.Factory;

/**
 * Created by Cesar Ferreira on 09/02/2018.
 * DataSource holds application information relative to some values that neeed presistance.
 */

public class DataSource {

    private static DataSource mInstance = null;
    private String country_id = "pt";
    private String country_name = "Portugal";

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

    public void setCountryName(String name)
    {
        country_name = name;
    }

    public String getCountryName() {
        return country_name;
    }
}
