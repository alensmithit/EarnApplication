package com.example.grownstartech.earnapplication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grownstartech.earnapplication.Interfaces.EarnRegApi;
import com.example.grownstartech.earnapplication.RepresentativeRegistration.RegRequestSignUp;
import com.example.grownstartech.earnapplication.RepresentativeRegistration.RepResponseReg;
import com.example.grownstartech.earnapplication.SponsorRegistration.SpoRequestSignUp;
import com.example.grownstartech.earnapplication.SponsorRegistration.SponsorResponseReg;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegActivity extends AppCompatActivity {

    EditText repSignup, spoSignup;
    Boolean isSponsorValid=false;
    Boolean isRepresentativeValid=false;
    Boolean isNetAvailable=false;
    EarnRegApi service;
    Button proceedSpoRepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reg );

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        repSignup = (EditText) findViewById(R.id.repres_edit_text_signup);
        spoSignup = (EditText) findViewById(R.id.sponsor_edit_text_signup);
        proceedSpoRepBtn = (Button) findViewById(R.id.spoRepProceedBtn);


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



    }

}
