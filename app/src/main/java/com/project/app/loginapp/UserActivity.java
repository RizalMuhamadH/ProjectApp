package com.project.app.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.project.app.loginapp.Config.AppConfig;

public class UserActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        textView = (TextView)findViewById(R.id.textView);
        // Fetching nama username user dari shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(AppConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(AppConfig.EMAIL_SHARED_PREF,"Not Available");

        textView.setText("Welcome User " + email);
    }
}
