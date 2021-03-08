package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.SampleModel;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.w3c.dom.Text;

import okhttp3.Headers;

public class activity_compose extends AppCompatActivity {
    private int maxChars = 180;
    EditText etCompose;
    Button btnTweet;
    TextView charCount;
    TwitterClient twitterClient;
    private String TAG = "ActivityCompose";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit = pref.edit();
        setContentView(R.layout.activity_compose);
        twitterClient = TwitterApp.getRestClient(this);
        etCompose = findViewById(R.id.etCompose);
        btnTweet = findViewById(R.id.btnTweet);
        charCount = findViewById(R.id.tvCharCount);
        etCompose.setText(pref.getString("tweetDraft", ""));
        etCompose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int totalChars = maxChars - etCompose.getText().length();
                if (totalChars < 0){
                    charCount.setTextColor(Color.parseColor("#8B0000"));
                }
                charCount.setText(String.valueOf(totalChars));
            }
            @Override
            public void afterTextChanged(Editable editable) {
                edit.putString("tweetDraft", String.valueOf(etCompose.getText()));
                edit.commit();
            }
        });

        // set click listener on button
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetContent = etCompose.getText().toString();
                if (tweetContent.isEmpty()) {
                    Toast.makeText(activity_compose.this, "Sorry your text cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                int tweetLength = tweetContent.length();
                if (tweetContent.length() > maxChars) {
                    Toast.makeText(activity_compose.this, "Sorry your tweet is " +  String.valueOf(tweetLength - maxChars) + " characters too long", Toast.LENGTH_SHORT ).show();
                    return;
                }
                // make an API call to Twitter to publish the tweet
                twitterClient.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        edit.putString("tweetDraft","");
                        edit.commit();

                        try {
                           Tweet tweet = Tweet.fromJson(json.jsonObject);
                            Toast.makeText(activity_compose.this,"Tweet published!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity_compose.this, TimelineActivity.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.i(TAG, "error publishing tweet:" + String.valueOf(statusCode), throwable);
                        Toast.makeText(activity_compose.this,"Tweet publish error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}