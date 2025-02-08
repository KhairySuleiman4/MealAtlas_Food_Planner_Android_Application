package com.example.foodplanner;

import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("strMeal")
    private String mealName;
    @SerializedName("strMealThumb")
    private String mealPhoto;

    public Meal(String mealPhoto, String mealName) {
        this.mealPhoto = mealPhoto;
        this.mealName = mealName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealPhoto() {
        return mealPhoto;
    }

    public void setMealPhoto(String mealPhoto) {
        this.mealPhoto = mealPhoto;
    }
}
