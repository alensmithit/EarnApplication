
package com.example.grownstartech.earnapplication.SponsorRegistration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SponsorResponseReg {

    @SerializedName("data")
    @Expose
    private SpoData data;

    public SpoData getData() {
        return data;
    }

    public void setData(SpoData data) {
        this.data = data;
    }

}
