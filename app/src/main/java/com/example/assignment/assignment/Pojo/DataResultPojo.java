package com.example.assignment.assignment.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResultPojo {
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private NameDataPojo name;
    @SerializedName("location")
    @Expose
    private LocationDataPojo location;
    private DobDataPojo dob;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("picture")
    @Expose
    private PictureDataPojo picture;
    @SerializedName("nat")
    @Expose
    private String nat;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public NameDataPojo getName() {
        return name;
    }

    public void setName(NameDataPojo name) {
        this.name = name;
    }

    public DobDataPojo getDob() {
        return dob;
    }

    public void setDob(DobDataPojo dob) {
        this.dob = dob;
    }

    public LocationDataPojo getLocation() {
        return location;
    }

    public void setLocation(LocationDataPojo dob) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PictureDataPojo getPicture() {
        return picture;
    }

    public void setPicture(PictureDataPojo picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }
}
