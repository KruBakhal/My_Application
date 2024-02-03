package com.example.myapplication.Practice.sample;

import java.util.List;

public class BrandCategory {

    List<Brand> brandCategory;

    public BrandCategory(List<Brand> list) {
        this.brandCategory = list;
    }

    public List<Brand> getList() {
        return brandCategory;
    }

    public void setList(List<Brand> brandCategory) {
        this.brandCategory = brandCategory;
    }
}
