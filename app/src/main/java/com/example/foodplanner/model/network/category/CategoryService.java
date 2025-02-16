package com.example.foodplanner.model.network.category;

import com.example.foodplanner.model.pojos.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("categories.php")
    Call<CategoryResponse> getCategories();
}
