package com.example.grownstartech.earnapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grownstartech.earnapplication.Interfaces.EarnApi;
import com.example.grownstartech.earnapplication.Interfaces.EarnRegApi;
import com.example.grownstartech.earnapplication.Models.Common;
import com.example.grownstartech.earnapplication.Models.SignUpRequest;
import com.example.grownstartech.earnapplication.Models.SignUpResponse;
import com.example.grownstartech.earnapplication.Models.SignupResNew;
import com.example.grownstartech.earnapplication.RepresentativeRegistration.RegRequestSignUp;
import com.example.grownstartech.earnapplication.RepresentativeRegistration.RepResponseReg;
import com.example.grownstartech.earnapplication.SponsorRegistration.SpoRequestSignUp;
import com.example.grownstartech.earnapplication.SponsorRegistration.SponsorResponseReg;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    Button signUpBtn;
    EditText repSignup, spoSignup, fnameSignup, usrnameSignup, emailSignup, pwdSignup;
    EarnRegApi service;
    Boolean isSponsorValid=false;
    Boolean isRepresentativeValid=false;
    Boolean isNetAvailable=false;
    Boolean isUsernameValid= false;
    private String valid_email;
    boolean isMyEmailValid=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Prevent Keyboard from loading at the start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        signUpBtn = (Button) findViewById(R.id.btn_sign_up);
        repSignup = (EditText) findViewById(R.id.repres_edit_text_signup);
        spoSignup = (EditText) findViewById(R.id.sponsor_edit_text_signup);
        fnameSignup = (EditText) findViewById(R.id.fullname_edit_text_signup);
        usrnameSignup = (EditText) findViewById(R.id.usrname_edit_text_signup);
        emailSignup = (EditText) findViewById(R.id.email_edit_text_signup);
        pwdSignup = (EditText) findViewById(R.id.pswrd_edit_text_signup);



        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        Toast.makeText(getApplicationContext(), "Space Not Allowed", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }
        };
        usrnameSignup.setFilters(new InputFilter[] { filter });




        //Place these lines in between On focus change listener




        emailSignup.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(emailSignup); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                   // edt.setError("Invalid Email Address");
                    isMyEmailValid=false;
                    valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                  //  edt.setError("Invalid Email Address");
                    isMyEmailValid=false;
                    valid_email = null;
                } else {
                    isMyEmailValid=true;
                    valid_email = signUpBtn.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {

                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });






        //Retrofit Init

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(EarnRegApi.class);

        //Focus Change
        spoSignup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    SpoRequestSignUp spoRequestSignUp= new SpoRequestSignUp();

                    spoRequestSignUp.setAccount_no(spoSignup.getText().toString());
                    Call<SponsorResponseReg> sponsorResponseRegCall= service.getSpoAccess(spoRequestSignUp);
                    sponsorResponseRegCall.enqueue(new Callback<SponsorResponseReg>() {
                        @Override
                        public void onResponse(Call<SponsorResponseReg> call, Response<SponsorResponseReg> response) {
                            int statusCode= response.code();
                            SponsorResponseReg sponsorResponseReg= response.body();
                            if(sponsorResponseReg.getData()==null){
                                Snackbar.make(findViewById(R.id.signup_root_view), "Retry", Snackbar.LENGTH_LONG)
                                        .setActionTextColor(getResources().getColor(R.color.RedColor))
                                        .show();
                                Toast.makeText(getApplicationContext(), "Not a valid Sponsor", Toast.LENGTH_SHORT).show();

                                spoSignup.setError("Not a valid Sponsor");
                                isSponsorValid=false;
                            }else{
                                isSponsorValid=true;
                              //  Snackbar.make(findViewById(R.id.signup_root_view), "Sponsor is Valid", Snackbar.LENGTH_LONG)
                                //        .setActionTextColor(Color.GREEN)
                                  //      .show();
                                //Toast.makeText(getApplicationContext(), "Sponsor is Valid", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SponsorResponseReg> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failed Sponsor Check " +t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        repSignup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    RegRequestSignUp regRequestSignUp= new RegRequestSignUp();
                    regRequestSignUp.setRepresentative_no(repSignup.getText().toString());
                    Call<RepResponseReg> repResponseRegCall= service.getRegAccess(regRequestSignUp);
                    repResponseRegCall.enqueue(new Callback<RepResponseReg>() {
                        @Override
                        public void onResponse(Call<RepResponseReg> call, Response<RepResponseReg> response) {
                            int statusCode= response.code();
                            RepResponseReg repResponseReg=response.body();
                            if(repResponseReg.getData()==null){
                                Snackbar.make(findViewById(R.id.signup_root_view), "Retry", Snackbar.LENGTH_LONG)
                                        .setActionTextColor(getResources().getColor(R.color.RedColor))
                                        .show();

                                Toast.makeText(getApplicationContext(), "Not a valid Representative", Toast.LENGTH_SHORT).show();
                                repSignup.setError("Not a valid Representative");
                                isRepresentativeValid=false;

                            }else{
                                isRepresentativeValid=true;
                              //  Snackbar.make(findViewById(R.id.signup_root_view), "Representative is Valid", Snackbar.LENGTH_LONG)
                                //        .setActionTextColor(Color.GREEN)
                                  //      .show();
                                //Toast.makeText(getApplicationContext(), "Sponsor is Valid", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RepResponseReg> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failed Representative  Check " +t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



        usrnameSignup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){

                    String input;
                    usrnameSignup.setText(usrnameSignup.getText().toString().toLowerCase());

                    input = usrnameSignup.getText().toString();
                    if(input.length() < 8){
                    usrnameSignup.setError("Must exceed 7 characters!");
                    } else {

                    }
                }
            }
        });

        emailSignup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(isMyEmailValid){
                      //  Snackbar.make(findViewById(R.id.signup_root_view), "Email is Valid", Snackbar.LENGTH_LONG)
                        //        .setActionTextColor(Color.GREEN)
                          //      .show();
                    }else{
                        emailSignup.setError("Invalid Email Address");
                    }

                }
            }
        });

        pwdSignup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){

                    String input;
                    pwdSignup.setText(pwdSignup.getText().toString().toLowerCase());

                    input = pwdSignup.getText().toString();
                    if(input.length() < 6){
                        pwdSignup.setError("Must exceed 5 characters!");
                    } else {

                    }

                }

            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check Network Connectivity
                ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                        || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

                    // notify user you are online
                    isNetAvailable=true;

                    if (isSponsorValid){
                        if(isRepresentativeValid){
                            if(isMyEmailValid){
                        SignUpRequest signUpRequest = new SignUpRequest();

                        signUpRequest.setRepresentative_no(repSignup.getText().toString());
                        signUpRequest.setParent_account_no(spoSignup.getText().toString());
                        signUpRequest.setFullname(fnameSignup.getText().toString());
                        signUpRequest.setUsername(usrnameSignup.getText().toString());
                        signUpRequest.setEmail(emailSignup.getText().toString());
                        signUpRequest.setPassword(pwdSignup.getText().toString());

                        Call<SignupResNew> signUpResponseCall = service.getSignUpAccess(signUpRequest);
                        signUpResponseCall.enqueue(new Callback<SignupResNew>() {
                            @Override
                            public void onResponse(Call<SignupResNew> call, Response<SignupResNew> response) {
                                int statusCode= response.code();

                                SignupResNew signUpResponse = response.body();
                                Common.errorSignUpRes=signUpResponse.getData().isError();
                                Common.successSignUpRes= signUpResponse.getData().isSuccess();
                                Common.SignUpResMessage= signUpResponse.getData().getMessage().toString();
                                if (Common.successSignUpRes == false){
                                    Toast.makeText(getApplicationContext(), ""+Common.SignUpResMessage, Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(getApplicationContext(), "You have been Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i =new Intent(SignupActivity.this, SuccessReg.class);
                                    startActivity(i);
                                }

                                Log.d("Signup Activity", "onResponse" + statusCode);

                            }

                            @Override
                            public void onFailure(Call<SignupResNew> call, Throwable t) { Log.d("Signup Activity", "onFailure" + t.getMessage());
                                Toast.makeText(getApplicationContext(), "Failed Registration" +t.getMessage(), Toast.LENGTH_SHORT).show();



                            }

                        });}else{ Toast.makeText(getApplicationContext(), "Your Email is not valid", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Your Representative is not valid", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Your Sponsor is not valid", Toast.LENGTH_SHORT).show();
                    }


                }
                else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                        || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

                    // notify user you are not online
                    isNetAvailable=false;
                    Toast.makeText(getApplicationContext(), "Please check your network connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
