package com.example.foodplanner.model.network.category;

import com.example.foodplanner.model.pojos.Category;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CategoriesRepository {
    Single<List<Category>> categoryNetworkCall();
}
