
package com.example.grownstartech.earnapplication.RepresentativeRegistration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepResponseReg {

    @SerializedName("data")
    @Expose
    private RegData data;

    public RegData getData() {
        return data;
    }

    public void setData(RegData data) {
        this.data = data;
    }

}
