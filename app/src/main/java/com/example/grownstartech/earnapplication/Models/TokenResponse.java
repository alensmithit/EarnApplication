package com.example.grownstartech.earnapplication.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {

    @SerializedName("access_token") //access_token
    @Expose
    private String access_token;
    @SerializedName("token_type") //token_type
    @Expose
    private String token_type;
    @SerializedName("expires_in") //expires_in
    @Expose
    private int expires_in;
    @SerializedName("is_browser_security") //is_browser_security
    @Expose
    private boolean is_browser_security;

    @SerializedName("browser_security") //browser_security
    @Expose
    private boolean browser_security;

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public boolean isIs_browser_security() {
        return is_browser_security;
    }

    public boolean isBrowser_security() {
        return browser_security;
    }
}
