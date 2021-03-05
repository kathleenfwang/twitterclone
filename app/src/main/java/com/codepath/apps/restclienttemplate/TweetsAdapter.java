package com.codepath.apps.restclienttemplate;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    // pass in context and list of tweets

    // for each row, inflate the layout

    //bind values based on the position of the tweet
    // define view holder

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.textView2);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
        }
    }
}
