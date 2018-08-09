package com.example.grownstartech.earnapplication.Interfaces;

import com.example.grownstartech.earnapplication.Models.SignUpRequest;
import com.example.grownstartech.earnapplication.Models.SignUpResponse;
import com.example.grownstartech.earnapplication.Models.SignupResNew;
import com.example.grownstartech.earnapplication.RepresentativeRegistration.RegRequestSignUp;
import com.example.grownstartech.earnapplication.RepresentativeRegistration.RepResponseReg;
import com.example.grownstartech.earnapplication.SponsorRegistration.SpoData;
import com.example.grownstartech.earnapplication.SponsorRegistration.SpoRequestSignUp;
import com.example.grownstartech.earnapplication.SponsorRegistration.SponsorResponseReg;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EarnRegApi {



    //For Registration
    @POST("auth/register")
    Call<SignupResNew> getSignUpAccess(@Body SignUpRequest signUpRequest);

    //For Sponsor Check
    @POST("v1/sponsorInfo")
    Call<SponsorResponseReg> getSpoAccess(@Body SpoRequestSignUp spoRequestSignUp);

    //For Representative Check
    @POST("v1/representativeInfo")
    Call<RepResponseReg> getRegAccess(@Body RegRequestSignUp regRequestSignUp);
}
