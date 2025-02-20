package com.example.foodplanner.model.network.category;

import com.example.foodplanner.model.pojos.CategoryResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("categories.php")
    Single<CategoryResponse> getCategories();
}
