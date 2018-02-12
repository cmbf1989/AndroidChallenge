package com.example.frontkom.androidchallenge.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frontkom.androidchallenge.Controllers.AppController;
import com.example.frontkom.androidchallenge.Controllers.NewsFeedController;
import com.example.frontkom.androidchallenge.Helpers.TimeConverter;
import com.example.frontkom.androidchallenge.POJO.Article;
import com.example.frontkom.androidchallenge.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by Cesar Ferreira on 09/02/2018.
 */

public class DetailsView extends AppView {


    NewsFeedController controller = null;
    Toolbar top_toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        top_toolbar = findViewById(R.id.toolbar);
        controller = (NewsFeedController) factory.createNewsController(this);

        setSupportActionBar(top_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getDetails();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(getApplicationContext(), NewsFeedView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();


                break;
        }
        return true;
    }

    /**
     * Sets up all the view data based on Article object selected on previous activity
     */
    public void getDetails()
    {
        Bundle data = getIntent().getExtras();
        final Article article =  data.getParcelable("Article");
        String title = article.getHeader();
        String description = article.getDescription();


        TextView title_textview = ((TextView)findViewById(R.id.title_new));
        ImageView image_detail = findViewById(R.id.image_new);

        String time = "";
        if (article.getPublishedAt() != null)
            time = TimeConverter.convertTimeDate(article.getPublishedAt());
        ((TextView)findViewById(R.id.time_new)).setText(time);
        ((TextView)findViewById(R.id.description_new)).setText(description);

        title_textview.setText(title);
        title_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (article.getUrl() != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(article.getUrl()));
                    startActivity(i);
                }
            }
        });

        image_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (article.getUrl() != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(article.getUrl()));
                    startActivity(i);
                }
            }
        });

        if (article.getImageSrc() != null) {
            Picasso.with(this)
                    .load(article.getImageSrc())
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(image_detail);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_misc, menu);
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());

        super.onDestroy();
        //Picasso.with(this).shutdown();

    }


    @Override
    public AppController getController() {
        return controller;
    }
}
