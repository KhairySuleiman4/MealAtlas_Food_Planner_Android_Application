package com.example.foodplanner;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
