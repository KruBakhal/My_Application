package com.example.myapplication.Practice.sample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("allproducts")
    @Expose
    private List<Product> allproducts = null;

    public List<Product> getAllproducts() {
        return allproducts;
    }

    public void setAllproducts(List<Product> allproducts) {
        this.allproducts = allproducts;
    }


}