package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    public String body;
    public String createdAt;
    public JSONObject entities;
    public String mediaUrl;
    public User user;

    //creating individual tweet
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.entities = jsonObject.getJSONObject("entities");
        if (tweet.entities.has("media")) {
            Log.i("json tweet", (String) tweet.entities.getJSONArray("media").getJSONObject(0).get("media_url"));
        tweet.mediaUrl = (String) tweet.entities.getJSONArray("media").getJSONObject(0).get("media_url_https");
    }
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
                return tweet;
    }
    // create list of tweets with function fromJsonArray
    // takes in a jsonArray
    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0;i<jsonArray.length();i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
