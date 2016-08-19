package com.project.app.loginapp.Config;

/**
 * Created by User on 11/08/2016.
 */
public class AppConfig {
    //URL to our login.php file
    public static final String LOGIN_URL = "http://10.0.2.2/advance/login.php";
    //URL to our register.php file
    public static final String REGISTER_URL = "http://10.0.2.2/advance/register.php";

    //Keys for name,email and password as defined
    public static final String KEY_NAME = "nama";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "session";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

//    belum kepake
//    public static final String TAG_ID = "id";
//
//    public static final String TAG_NAMA = "nama";
//
//    public static final String TAG_EMAIL = "email";
//
//    public static final String TAG_TOKEN = "token";
}
