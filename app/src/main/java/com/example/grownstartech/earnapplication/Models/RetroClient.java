package com.example.grownstartech.earnapplication.Models;

import com.example.grownstartech.earnapplication.Interfaces.EarnRegApi;
import com.example.grownstartech.earnapplication.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ROOT_URL= "http://192.168.88.246/api/auth/";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static EarnRegApi getApiService(){
        return getRetrofitInstance().create(EarnRegApi.class);
    }

}
