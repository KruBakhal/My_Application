package com.example.myapplication.Practice.sample;

import java.util.List;

public class Sub_Category {
    String sub_category_name;
    List<Product> product_list;

    public Sub_Category(String sub_category_name, List<Product> product_list) {
        this.sub_category_name = sub_category_name;
        this.product_list = product_list;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }

    public List<Product> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(List<Product> product_list) {
        this.product_list = product_list;
    }

    @Override
    public String toString() {
        return "Sub_Category{" +
                "sub_category_name='" + sub_category_name + '\'' +
                ", product_list=" + product_list +
                '}';
    }
}
