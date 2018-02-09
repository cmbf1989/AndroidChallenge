package com.example.frontkom.androidchallenge.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.frontkom.androidchallenge.Adapters.RecyclerViewAdapter;
import com.example.frontkom.androidchallenge.Controllers.NewsFeedController;
import com.example.frontkom.androidchallenge.Interfaces.RecyclerViewClickListener;
import com.example.frontkom.androidchallenge.Interfaces.ListViewItem;
import com.example.frontkom.androidchallenge.Listeners.RecyclerViewTouchListener;
import com.example.frontkom.androidchallenge.POJO.Article;
import com.example.frontkom.androidchallenge.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

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
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        requestNews();
    }

    public void requestNews()
    {
      //  top_toolbar.getMenu().findItem(R.id.action_refresh).setActionView(new ProgressBar(this));
        controller = (NewsFeedController) factory.createNewsController(this);
        controller.requestNews("pt");
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
                controller.requestNews("us");
                return true;

            case R.id.action_settings:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
