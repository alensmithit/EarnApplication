package com.example.grownstartech.earnapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CaptchaRegActivity extends AppCompatActivity {

    private static final String TAG = CaptchaRegActivity.class.getSimpleName();

    public static final String SITE_KEY = "6LdyEGkUAAAAAIT51N8nwAaACwo_H3kmITMASy7b";
    public static final String SITE_SECRET_KEY = "6LdyEGkUAAAAAG4WYIB_oMU-ivIRwEHnSFVawHCY";
    String userResponseToken;
    Button CapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha_reg);


        CapBtn = (Button) findViewById(R.id.captchaRegBtn);
        CapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyWithRecaptcha();
            }
        });


    }

    private void verifyWithRecaptcha() {

        SafetyNet.getClient(this).verifyWithRecaptcha("6LdyEGkUAAAAAIT51N8nwAaACwo_H3kmITMASy7b")
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        userResponseToken = response.getTokenResult();
                        if (!userResponseToken.isEmpty()) {
                            Toast.makeText(CaptchaRegActivity.this, "Success", Toast.LENGTH_LONG).show();
                            Intent i =new Intent(CaptchaRegActivity.this, RegActivity.class);
                            startActivity(i);
                             //   sendRequest();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d(TAG, "Error message: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            Log.d(TAG, "Unknown type of error: " + e.getMessage());
                        }
                    }
                });
    }

    private void sendRequest() {

        String url = "https://www.google.com/recaptcha/api/siteverify";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(CaptchaRegActivity.this, obj.getString("success"), Toast.LENGTH_LONG).show();
                            if (obj.getString("success").equals("true")){
                                Intent i =new Intent(CaptchaRegActivity.this, IndexActivity.class);
                                startActivity(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CaptchaRegActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("secret", SITE_SECRET_KEY);
                params.put("response", userResponseToken);
                return params;
            }
        };
    }
}
