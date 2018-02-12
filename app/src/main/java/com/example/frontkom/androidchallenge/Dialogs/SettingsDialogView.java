package com.example.frontkom.androidchallenge.Dialogs;

import android.app.Dialog;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.frontkom.androidchallenge.POJO.Country;
import com.example.frontkom.androidchallenge.R;
import com.example.frontkom.androidchallenge.Views.AppView;

import java.util.List;

/**
 * Created by Cesar Ferreira on 10/02/2018.
 */

public class SettingsDialogView extends Dialog {

    AppView view;
    public SettingsDialogView(AppView activity)
    {
        super(activity);
        view = activity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.settings_dialog);
    }

    public SettingsDialogView(AppView activity, List<Country> countries)
    {
        super(activity);
        view = activity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.settings_dialog);
        fillSettingsData(countries);
    }

    public void fillSettingsData(List<Country> countries)
    {
        ArrayAdapter<Country> dataAdapter = new ArrayAdapter<>(view, android.R.layout.simple_spinner_item, countries);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner _EmpSpinner = findViewById(R.id.list_countries);
        _EmpSpinner.setAdapter(dataAdapter);

    }

}
