package com.example.foodplanner.model.network.category;

import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface CategoryNetworkCallBack {
    public void onSuccessResult(List<Category> categories);
    public void onFailResult(String error);
}
