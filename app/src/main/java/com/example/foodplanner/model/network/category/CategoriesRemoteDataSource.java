package com.example.foodplanner.model.network.category;

import com.example.foodplanner.model.network.meal.MealNetworkCallBack;

public interface CategoriesRemoteDataSource {
    void categoryNetworkCall(CategoryNetworkCallBack categoryNetworkCallBack);
}
