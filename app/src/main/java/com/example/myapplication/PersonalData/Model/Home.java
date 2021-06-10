
package com.example.myapplication.PersonalData.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Home {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("catgeory")
    @Expose
    private String catgeory;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sub_category")
    @Expose
    private List<SubCategory__1> subCategory = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getCatgeory() {
        return catgeory;
    }

    public void setCatgeory(String catgeory) {
        this.catgeory = catgeory;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SubCategory__1> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategory__1> subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", position=" + position +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", catgeory='" + catgeory + '\'' +
                ", status=" + status +
                ", subCategory=" + subCategory +
                '}';
    }
}
