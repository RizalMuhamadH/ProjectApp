package com.project.app.loginapp.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.project.app.loginapp.Config.AppConfig;
import com.project.app.loginapp.LoginActivity;

import java.util.HashMap;

/**
 * Created by User on 10/08/2016.
 */
public class SessionPreferences {
    //shared preferences
    SharedPreferences pref;
    //editor for shared preferences
    SharedPreferences.Editor editor;
    //context
    Context _context;
    //shared pref mode
    int PRIVATE_MODE = 0;

    //name sharedpreferences


    public SessionPreferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(AppConfig.SHARED_PREF_NAME,PRIVATE_MODE);
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String email){
        // Storing login value as TRUE
        editor.putBoolean(AppConfig.LOGGEDIN_SHARED_PREF, true);

//        editor.putString(KEY_NAME, name);
        editor.putString(AppConfig.KEY_EMAIL, email);
        editor.commit();
    }
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            //((Activity)_context).finish();
        }
    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

//        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(AppConfig.KEY_EMAIL, pref.getString(AppConfig.KEY_EMAIL, null));

        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(AppConfig.LOGGEDIN_SHARED_PREF, false);
    }
}
