package com.example.frontkom.androidchallenge.Views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.frontkom.androidchallenge.Adapters.RecyclerViewAdapter;
import com.example.frontkom.androidchallenge.Controllers.NewsFeedController;
import com.example.frontkom.androidchallenge.Helpers.FileReaderJSON;
import com.example.frontkom.androidchallenge.Interfaces.RecyclerViewClickListener;
import com.example.frontkom.androidchallenge.Interfaces.ListViewItem;
import com.example.frontkom.androidchallenge.Listeners.RecyclerViewTouchListener;
import com.example.frontkom.androidchallenge.POJO.Article;
import com.example.frontkom.androidchallenge.POJO.ConfigSettings;
import com.example.frontkom.androidchallenge.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedView extends AppView {

    NewsFeedController controller = null;
    RecyclerViewAdapter news_recycledview = null;
    Toolbar top_toolbar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_view);
        top_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(top_toolbar);

        RecyclerView recyclerView =  findViewById(R.id.news_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<ListViewItem> input = new ArrayList<>();
        news_recycledview = new RecyclerViewAdapter(input,this);
        recyclerView.setAdapter(news_recycledview);

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getApplicationContext(), DetailsView.class);
                Article article =  (Article) news_recycledview.getListItemAt(position);
                intent.putExtra("Article", article);
               // Intent i = new Intent("com.example.frontkom.androidchallenge.Views.DetailsView");
                //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        requestNews();
    }
    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());

        super.onDestroy();
        //Picasso.with(this).shutdown();

    }
    public void requestNews()
    {
      //  top_toolbar.getMenu().findItem(R.id.action_refresh).setActionView(new ProgressBar(this));
        controller = (NewsFeedController) factory.createNewsController(this);
        controller.requestNews(app_data.getCountryId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void update() {
        List<ListViewItem> items = this.controller.getNews();
        news_recycledview.addNewList(items);
        news_recycledview.notifyDataSetChanged();
        (top_toolbar.getMenu().findItem(R.id.action_refresh)).setActionView(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                item.setActionView(new ProgressBar(this));
                controller.requestNews(app_data.getCountryId());
                return true;

            case R.id.action_settings:
                createSettingsPopup();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void createSettingsPopup() {
        /*String config_settings = FileReaderJSON.getJSON(this, R.raw.datasource);
        Gson converter =  new Gson();
        ConfigSettings settings = converter.fromJson(config_settings, ConfigSettings.class);

        String tteste = " true";

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, settings.getCountries());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner _EmpSpinner =  null;
        _EmpSpinner = findViewById(R.id.);
        _EmpSpinner.setAdapter(dataAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());


        builder.setMessage("Choose settings").setTitle("Settings");

        AlertDialog dialog = builder.create();
        */


    }
}
