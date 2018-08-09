package com.example.grownstartech.earnapplication.Interfaces;

import com.example.grownstartech.earnapplication.Models.SignUpRequest;
import com.example.grownstartech.earnapplication.Models.SignUpResponse;
import com.example.grownstartech.earnapplication.Models.TokenRequest;
import com.example.grownstartech.earnapplication.Models.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EarnApi {

    @POST("auth/login")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

}
