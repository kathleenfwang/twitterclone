package com.codepath.apps.restclienttemplate.models;

import org.json.JSONObject;

public class Tweet {
    public String body;
    public String createdAt;
    public User user;

    public static Tweet fromJson(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.createdAt = User.fromJson(jsonObject.getString("user"));

                return tweet;
    }
}
