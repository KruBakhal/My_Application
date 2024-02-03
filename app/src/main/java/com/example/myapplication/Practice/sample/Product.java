package com.example.myapplication.Practice.sample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("tax_class")
    @Expose
    private String taxClass;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("is_latest")
    @Expose
    private String isLatest;
    @SerializedName("is_featured")
    @Expose
    private String isFeatured;
    @SerializedName("is_special")
    @Expose
    private String isSpecial;
    @SerializedName("available_qty")
    @Expose
    private String availableQty;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("cart_image")
    @Expose
    private String cartImage;
    @SerializedName("detail_image")
    @Expose
    private String detailImage;
    @SerializedName("main_image")
    @Expose
    private String mainImage;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("url")
    @Expose
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(String isLatest) {
        this.isLatest = isLatest;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(String availableQty) {
        this.availableQty = availableQty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCartImage() {
        return cartImage;
    }

    public void setCartImage(String cartImage) {
        this.cartImage = cartImage;
    }

    public String getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", taxClass='" + taxClass + '\'' +
                ", unit='" + unit + '\'' +
                ", weight='" + weight + '\'' +
                ", isLatest='" + isLatest + '\'' +
                ", isFeatured='" + isFeatured + '\'' +
                ", isSpecial='" + isSpecial + '\'' +
                ", availableQty='" + availableQty + '\'' +
                ", price='" + price + '\'' +
                ", cartImage='" + cartImage + '\'' +
                ", detailImage='" + detailImage + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

