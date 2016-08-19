package com.project.app.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.app.loginapp.Config.AppConfig;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        registerUser();
    }

    private void registerUser(){
        final String nama = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put(AppConfig.KEY_NAME,nama);
                params.put(AppConfig.KEY_EMAIL,email);
                params.put(AppConfig.KEY_PASSWORD,password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
