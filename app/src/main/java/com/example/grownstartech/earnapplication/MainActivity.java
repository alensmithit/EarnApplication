package com.example.grownstartech.earnapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.nfc.Tag;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grownstartech.earnapplication.Interfaces.EarnApi;
import com.example.grownstartech.earnapplication.Models.Common;
import com.example.grownstartech.earnapplication.Models.TokenRequest;
import com.example.grownstartech.earnapplication.Models.TokenResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button frgtPwd, btnLogin;
    TextView signupView;
    EditText usernameLogin, pwdLogin;

    RelativeLayout rellay1, rellay2;
    EarnApi service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameLogin= (EditText) findViewById(R.id.usrname_login);
        pwdLogin = (EditText) findViewById(R.id.pwd_login);


        signupView = (TextView) findViewById(R.id.signup_view_login);
        frgtPwd = (Button) findViewById(R.id.frgt_pwd_view);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
// Underline Forgot Password
        signupView.setPaintFlags(signupView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //Retrofit Init

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         service = retrofit.create(EarnApi.class);


        signupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);

            }
        });

        frgtPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, ForgotPwdActivity.class);
                startActivity(i);

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TokenRequest tokenRequest = new TokenRequest();

                tokenRequest.setEmail(usernameLogin.getText().toString());
                tokenRequest.setPassword(pwdLogin.getText().toString());

               Call<TokenResponse> tokenResponseCall = service.getTokenAccess(tokenRequest);
               tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                   @Override
                   public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                       int statusCode = response.code();

                       TokenResponse tokenResponse = response.body();
                        Common.user_key = tokenResponse.getAccess_token().toString();
                        Common.tokenType = tokenResponse.getToken_type().toString();
                        Common.expiresIn= tokenResponse.getExpires_in();
                        Common.browserSecurity= tokenResponse.isBrowser_security();
                        Common.browserSecurity_is= tokenResponse.isIs_browser_security();
                       Toast.makeText(getApplicationContext(), "Success Logging In", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, IndexActivity.class);
                        startActivity(i);
                       Log.d("Main Activity", "onResponse" + statusCode);
                   }

                   @Override
                   public void onFailure(Call<TokenResponse> call, Throwable t) {
                       Log.d("Main Activity", "onFailure" + t.getMessage());
                       Toast.makeText(getApplicationContext(), "Failed Logging In"+t.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });


            }
        });

    }
}
