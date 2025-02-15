package com.example.foodplanner.model.pojos;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("strCategory")
    private String cateName;
    @SerializedName("strCategoryThumb")
    private String cateImage;

    public Category(String cateName, String cateImage) {
        this.cateName = cateName;
        this.cateImage = cateImage;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateImage() {
        return cateImage;
    }

    public void setCateImage(String cateImage) {
        this.cateImage = cateImage;
    }

}
