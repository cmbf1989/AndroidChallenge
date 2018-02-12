package com.example.frontkom.androidchallenge.Adapters;

/**
 * Created by Cesar Ferreira on 08/02/2018.
 * Recycler view adapter that is responsible for holding every item in the list with the desired
 * proprieties
 */

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frontkom.androidchallenge.Helpers.TimeConverter;
import com.example.frontkom.androidchallenge.Interfaces.IListViewItem;
import com.example.frontkom.androidchallenge.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GRAY;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<IListViewItem> values;
    public Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView image;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            image     = v.findViewById(R.id.image_new);
        }
    }

    public void addNewList(List<IListViewItem> values) {
        this.values = values;
    }
    public void add(int position, IListViewItem item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public IListViewItem getListItemAt(int position)
    {
        return values.get(position);
    }
    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(List<IListViewItem> myDataset, Context context) {
        values = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.news_feed_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //  This method sets up the values for the recyclerview item. Uses picasso to lazy load the image from URL
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.txtHeader.setText(values.get(position).getHeader());

        String time = values.get(position).getFooter();

        String time_past = TimeConverter.getElapsedTimeMinutesSecondsString(time);
        holder.txtFooter.setText(time_past);

        if (values.get(position).getImageSrc() != null ) {
            holder.image.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(values.get(position).getImageSrc())
                    .resize(80, 80)
                    .centerCrop()
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(holder.image);
        } else {
            holder.image.setVisibility(View.GONE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
