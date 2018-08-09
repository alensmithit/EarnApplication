
package com.example.grownstartech.earnapplication.RepresentativeRegistration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegData {

    @SerializedName("username_is_public")
    @Expose
    private int usernameIsPublic;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("phone_no_is_public")
    @Expose
    private int phoneNoIsPublic;
    @SerializedName("phone_no")
    @Expose
    private Object phoneNo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;

    public int getUsernameIsPublic() {
        return usernameIsPublic;
    }

    public void setUsernameIsPublic(int usernameIsPublic) {
        this.usernameIsPublic = usernameIsPublic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhoneNoIsPublic() {
        return phoneNoIsPublic;
    }

    public void setPhoneNoIsPublic(int phoneNoIsPublic) {
        this.phoneNoIsPublic = phoneNoIsPublic;
    }

    public Object getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Object phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
