package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MealsRepositoryImp implements MealsRepository{
    MealsRemoteDataSource remote;

    private static MealsRepositoryImp repo = null;

    private MealsRepositoryImp(MealsRemoteDataSource remote){
        this.remote = remote;
    }

    public static MealsRepositoryImp getInstance(MealsRemoteDataSource remote){
        if(repo == null){
            repo = new MealsRepositoryImp(remote);
        }
        return repo;
    }
    @Override
    public Single<Meal> mealNetworkCall() {
        return remote.mealNetworkCall();
    }

    @Override
    public Single<List<Meal>> ingredientsNetworkCall() {
        return remote.ingredientsNetworkCall();
    }

    @Override
    public Single<List<Meal>> mealsByCategory(String category) {
        return remote.mealsByCategory(category);
    }

    @Override
    public Single<List<Meal>> mealsByCountry(String country) {
        return remote.mealsByCountry(country);
    }

    @Override
    public Single<List<Meal>> mealsByIngredient(String ingredient) {
        return remote.mealsByIngredient(ingredient);
    }

    @Override
    public Single<Meal> mealByName(String mealName) {
        return remote.mealByName(mealName);
    }
}
