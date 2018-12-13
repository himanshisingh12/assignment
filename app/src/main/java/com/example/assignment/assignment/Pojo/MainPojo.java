package com.example.assignment.assignment.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainPojo {
    @SerializedName("results")
    @Expose
    private List<DataResultPojo> results = null;
    @SerializedName("info")
    @Expose
    private InfoPojo info;


    public List<DataResultPojo> getResults() {
        return results;
    }

    public void setResults(List<DataResultPojo> results) {
        this.results = results;
    }

    public InfoPojo getInfo() {
        return info;
    }

    public void setInfo(InfoPojo info) {
        this.info = info;
    }



}
