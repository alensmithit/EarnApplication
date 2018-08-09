package com.example.grownstartech.earnapplication.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse { //This class is of no use

    @SerializedName("data") //data
    @Expose
    private String data;
    @SerializedName("error") //error
    @Expose
    private boolean error;
    @SerializedName("success") //success
    @Expose
    private boolean success;
    @SerializedName("message") //message
    @Expose
    private String message;

    public String getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
