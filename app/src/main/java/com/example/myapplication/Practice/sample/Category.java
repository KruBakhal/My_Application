package com.example.myapplication.Practice.sample;

import java.util.ArrayList;
import java.util.List;

public class Category {

    String category_id;
    String category_name;
    String sub_category_name;
    String product_name;
    List<Product> product_list;

    public Category(String category_id, String category_name, String sub_category_name, String sub_sub_category_name, List<Product> product_list) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.sub_category_name = sub_category_name;
        this.product_name = sub_sub_category_name;
        this.product_list = product_list;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Category(String category_id, String category_name, String sub_category_name, List<Product> allproduct) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.sub_category_name = sub_category_name;
        this.product_list = allproduct;
    }

    public Category(String categoryId, ArrayList<Product> allproduct) {
        this.category_id = categoryId;
        this.product_list = allproduct;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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
        return "Category{" +
                "category_id='" + category_id + '\'' +
                ", category_name='" + category_name + '\'' +
                ", sub_category_name='" + sub_category_name + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_list=" + product_list +
                '}';
    }
}
