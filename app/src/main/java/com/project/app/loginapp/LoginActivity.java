package com.project.app.loginapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.app.loginapp.Config.AppConfig;
import com.project.app.loginapp.Session.SessionPreferences;
import com.rengwuxian.materialedittext.MaterialEditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    MaterialEditText email, password;
    FancyButton login, register;
    String mUrl=null;
    String success;
    private boolean loggedIn = false;
    private String mEmail,mPassword = null;
    SessionPreferences session;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (MaterialEditText)findViewById(R.id.email);
        password = (MaterialEditText)findViewById(R.id.password);
        login = (FancyButton)findViewById(R.id.login);
        register = (FancyButton)findViewById(R.id.register);

        session = new SessionPreferences(getApplicationContext());
        Toast.makeText(getApplicationContext(),"User Login Status : "+session.isLoggedIn(), Toast.LENGTH_LONG).show();
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == login){
            login();
        } else if (view == register){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(AppConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(AppConfig.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            startActivity(intent);
        }
    }
    public void openFile(){
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(AppConfig.KEY_EMAIL, mEmail);
        startActivity(intent);
    }
    private void login(){
        //Getting values from edit texts
        mEmail = email.getText().toString().trim();
        mPassword = password.getText().toString().trim();


        //Creating a string request
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            // Getting out
                            SharedPreferences preferences = getSharedPreferences(AppConfig.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                            //Buatkan sebuah shared preference
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(AppConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Buatkan Sebuah variabel Editor Untuk penyimpanan Nilai shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Tambahkan Nilai ke Editor
                            editor.putBoolean(AppConfig.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(AppConfig.EMAIL_SHARED_PREF, mEmail);

                            //Simpan Nilai ke Variabel editor
                            editor.commit();
                            openFile();
                        } else {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> maps = new HashMap<>();
                maps.put(AppConfig.KEY_EMAIL,mEmail);
                maps.put(AppConfig.KEY_PASSWORD,mPassword);
                return maps;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        /*
        mengambl data dari database  dengan request JSONObject
         */
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, AppConfig.LOGIN_URL, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("user");
//                            Map<String, String> params = new HashMap<>();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObj = jsonArray.getJSONObject(i);
//                                String nama
//                            }
//                        } catch (JSONException e) {
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //If we are getting success from server
//                        Log.e("onResponse ","Status :"+AppConfig.LOGIN_SUCCESS );
//                        if(response.trim().equals("success")){
//                            //Creating a shared preference
//                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(AppConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//                            //Creating editor to store values to shared preferences
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                            //Adding values to editor
//                            editor.putBoolean(AppConfig.LOGGEDIN_SHARED_PREF, true);
//                            editor.putString(AppConfig.EMAIL_SHARED_PREF, mEmail);
//
//                            //Saving values to editor
//                            editor.commit();
//
//                            //Starting profile activity
//                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
//                            startActivity(intent);
//                        }else{
//                            //If the server response is not success
//                            //Displaying an error message on toast
//                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //You can handle error here if you want
//                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String,String>();
//                //Adding parameters to request
//                params.put(KEY_EMAIL, mEmail);
//                params.put(KEY_PASSWORD, mPassword);
//
//                //returning parameter
//                return params;
//            }
//        };
//
//        //Adding the string request to the queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
    }
}
