package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Meal;

import io.reactivex.rxjava3.core.Single;

public interface MealsRemoteDataSource {
    Single<Meal> mealNetworkCall();
}
