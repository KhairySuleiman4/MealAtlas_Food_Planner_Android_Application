package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("random.php")
    Single<MealResponse> getRandomMeal();
}
