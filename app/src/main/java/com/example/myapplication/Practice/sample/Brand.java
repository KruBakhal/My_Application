package com.example.myapplication.Practice.sample;

import java.util.List;

public class Brand {

    String brandName;
    List<Category> categoryList;

    public Brand(String brandName, List<Category> categoryList) {
        this.brandName = brandName;
        this.categoryList = categoryList;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

}
