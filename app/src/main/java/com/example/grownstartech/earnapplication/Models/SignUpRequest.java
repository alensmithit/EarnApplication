package com.example.grownstartech.earnapplication.Models;

public class SignUpRequest {

    private String parent_account_no; //Yet to set to int
    private String representative_no; //Same with upper
    private String fullname;
    private String username;
    private String email;
    private String password;

    public String getParent_account_no() {
        return parent_account_no;
    }

    public void setParent_account_no(String parent_account_no) {
        this.parent_account_no = parent_account_no;
    }

    public String getRepresentative_no() {
        return representative_no;
    }

    public void setRepresentative_no(String representative_no) {
        this.representative_no = representative_no;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
