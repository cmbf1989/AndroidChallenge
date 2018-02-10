package com.example.frontkom.androidchallenge.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frontkom.androidchallenge.Controllers.AppController;
import com.example.frontkom.androidchallenge.Controllers.NewsFeedController;
import com.example.frontkom.androidchallenge.DataSource.DataSource;
import com.example.frontkom.androidchallenge.Interfaces.IObserver;
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

    public void showDialog() {

        Button save_btn = findViewById(R.id.save_settings);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String country = ((Country)((Spinner)findViewById(R.id.list_countries)).getSelectedItem()).getId();
                ((NewsFeedController) view.getController()).setCountry(country);
                ((NewsFeedController) view.getController()).requestNews();

                dismiss();
            }
        });

        Button cancel_btn = findViewById(R.id.cancel_settings);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        show();
    }
}
