package com.example.foodplanner.model.network;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface NetworkCallBack {
    public void onSuccessResult(List<Meal> products);
    public void onFailResult(String error);
}
