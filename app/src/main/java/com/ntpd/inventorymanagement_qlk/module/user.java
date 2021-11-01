package com.ntpd.inventorymanagement_qlk.module;

public class user {
    private String fullName;
    private String email;
    private String uid;
    private String password;

    public user(String fullName, String email, String uid, String password) {
        this.fullName = fullName;
        this.email = email;
        this.uid = uid;
        this.password = password;
    }

    public user() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
