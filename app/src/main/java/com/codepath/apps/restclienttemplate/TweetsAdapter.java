package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.w3c.dom.Text;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;
    // generate constructor to populate member variables

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        // this refers to "this", otherwise if we set context = context we get error
        this.context = context;
        this.tweets = tweets;
    }

    // for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create new view
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);

    }
    //bind values based on the position of the tweet
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the tweet at position
    Tweet tweet = tweets.get(position);
    holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }
    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    // define view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        ImageView ivMediaImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.textView2);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            ivMediaImg = itemView.findViewById(R.id.mediaImg);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            Glide.with(context).load(tweet.user.imageUrl).into(ivProfileImage);
            if (tweet.mediaUrl != null) {
                Glide.with(context).load(tweet.mediaUrl).into(ivMediaImg);
            }
        }
    }
}
