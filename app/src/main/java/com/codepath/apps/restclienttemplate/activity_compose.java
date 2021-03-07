package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class activity_compose extends AppCompatActivity {
    private int maxChars = 180;
    EditText etCompose;
    Button btnTweet;
    TextView charCount;
    private String TAG = "ActivityCompose";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose = findViewById(R.id.etCompose);
        btnTweet = findViewById(R.id.btnTweet);
        charCount = findViewById(R.id.tvCharCount);
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
            public void afterTextChanged(Editable editable) {}
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
            }
        });
        // make an API call to Twitter to publish the tweet

    }
}