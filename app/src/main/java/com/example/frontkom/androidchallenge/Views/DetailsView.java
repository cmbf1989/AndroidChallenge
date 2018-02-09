package com.example.frontkom.androidchallenge.Views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontkom.androidchallenge.Adapters.RecyclerViewAdapter;
import com.example.frontkom.androidchallenge.Controllers.NewsFeedController;
import com.example.frontkom.androidchallenge.Interfaces.ListViewItem;
import com.example.frontkom.androidchallenge.Interfaces.RecyclerViewClickListener;
import com.example.frontkom.androidchallenge.Listeners.RecyclerViewTouchListener;
import com.example.frontkom.androidchallenge.POJO.Article;
import com.example.frontkom.androidchallenge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
        Bundle data = getIntent().getExtras();
        Article article =  data.getParcelable("Article");
        String title = article.getHeader();
        String description = article.getFooter();
        ((TextView)findViewById(R.id.title_new)).setText(title);
        ((TextView)findViewById(R.id.time_new)).setText(article.getPublishedAt()+ " - " + article.getAuthor());
        ((TextView)findViewById(R.id.description_new)).setText(description);


        ImageView image_detail = findViewById(R.id.image_new);


        Picasso.with(this)
                .load(article.getImageSrc())
                .into(image_detail);
       /* LinearLayout description_container  =  findViewById(R.id.description_container);
        ViewGroup.LayoutParams params = description_container.getLayoutParams();
        params.width = image_detail.getWidth();
        description_container.setLayoutParams(params);
        */
        setSupportActionBar(top_toolbar);

        getDetails();
    }

    public void getDetails()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                item.setActionView(new ProgressBar(this));
                //controller.requestNews("us");
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
