package com.example.foodplanner;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("categories")
    ArrayList<Category> cates;

    public CategoryResponse(ArrayList<Category> cates) {
        this.cates = cates;
    }

    public ArrayList<Category> getCates() {
        return cates;
    }

    public void setCates(ArrayList<Category> cates) {
        this.cates = cates;
    }
}
