package com.example.frontkom.androidchallenge.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frontkom.androidchallenge.Adapters.RecyclerViewAdapter;
import com.example.frontkom.androidchallenge.Controllers.AppController;
import com.example.frontkom.androidchallenge.Controllers.NewsController;
import com.example.frontkom.androidchallenge.Dialogs.SettingsDialogView;
import com.example.frontkom.androidchallenge.Interfaces.RecyclerViewClickListener;
import com.example.frontkom.androidchallenge.Interfaces.IListViewItem;
import com.example.frontkom.androidchallenge.Listeners.RecyclerViewTouchListener;
import com.example.frontkom.androidchallenge.POJO.Article;
import com.example.frontkom.androidchallenge.POJO.ConfigSettings;
import com.example.frontkom.androidchallenge.POJO.Country;
import com.example.frontkom.androidchallenge.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedView extends AppView {

    NewsController controller = null;
    RecyclerViewAdapter news_recycledview = null;
    Toolbar top_toolbar = null;
    SettingsDialogView settings_dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = (NewsController) factory.createNewsController(this);
        setContentView(R.layout.activity_news_feed_view);
        top_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(top_toolbar);
        ((TextView)findViewById(R.id.country_label)).setText( controller.getCountry());

        RecyclerView recyclerView =  findViewById(R.id.news_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<IListViewItem> input = new ArrayList<>();
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

    /**
     * Requests to the controller the news. Sets progress search visible
     */
    public void requestNews()
    {
        setProgessLoading(R.id.progress_search, View.VISIBLE);
        controller.requestNews();
    }

    public void setProgessLoading(int item, int id)
    {
        (findViewById(item)).setVisibility(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    /**
     * IObserver composition method that updates the content.
     */
    public void update() {
        List<IListViewItem> items = this.controller.getNews();
        news_recycledview.addNewList(items);
        setProgessLoading(R.id.progress_search, View.GONE);
        setProgessLoading(R.id.news_list, View.VISIBLE);
        news_recycledview.notifyDataSetChanged();
        (top_toolbar.getMenu().findItem(R.id.action_refresh)).setActionView(null);
    }

    @Override
    /**
     * Actions for tab bar:
     *  -  refresh : tries to request the news again.
     *  - settings : opens custom dialog for the selection of the language codes for the news
     *
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                item.setActionView(new ProgressBar(this));
                controller.requestNews();
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




    /**
     * Method that creates the custom popup. Retrieves data from Raw/datasource.json and loads to a class object
     * After loading shows in the popup the languages available pointing to their code
     */
    public void createSettingsPopup() {

        InputStream is = getResources().openRawResource(R.raw.datasource);
        ConfigSettings settings = controller.getSettings(is);
        settings_dialog = factory.createSettingsDialog(this, settings.getCountries() );
        handleSettingsPopup();
    }

    /**
     * Handles button clicks on popup settings.
     */
    public void handleSettingsPopup()
    {
        //final View view  = settings_dialog.f;
        Button save_btn = settings_dialog.findViewById(R.id.save_settings);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = ((Country)((Spinner) settings_dialog.findViewById(R.id.list_countries)).getSelectedItem()).getId();
                String name = ((Country)((Spinner) settings_dialog.findViewById(R.id.list_countries)).getSelectedItem()).getName();
                setProgessLoading(R.id.news_list, View.GONE);
                setProgessLoading(R.id.progress_search, View.VISIBLE);
                controller.setCountry(id, name);
                controller.requestNews();
                ((TextView)findViewById(R.id.country_label)).setText(name);
                settings_dialog.dismiss();
                settings_dialog = null;
            }
        });

        Button cancel_btn = settings_dialog.findViewById(R.id.cancel_settings);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings_dialog.dismiss();
                settings_dialog = null;
            }
        });
        settings_dialog.show();
    }
    @Override
    public AppController getController() {
        return controller;
    }
}
