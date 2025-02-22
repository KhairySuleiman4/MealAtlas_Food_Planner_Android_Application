package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.db.MealsLocalDataSource;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealsRepositoryImp implements MealsRepository{
    MealsRemoteDataSource remote;
    MealsLocalDataSource local;
    private static MealsRepositoryImp repo = null;

    private MealsRepositoryImp(MealsRemoteDataSource remote, MealsLocalDataSource local){
        this.remote = remote;
        this.local = local;
    }

    public static MealsRepositoryImp getInstance(MealsRemoteDataSource remote, MealsLocalDataSource local){
        if(repo == null){
            repo = new MealsRepositoryImp(remote, local);
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

    @Override
    public Observable<List<Meal>> getFavMeals() {
        return local.getFavMeals();
    }

    @Override
    public Completable insertMeal(Meal meal) {
        return local.insertMeal(meal);
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return local.deleteMeal(meal);
    }

    @Override
    public Single<Boolean> doesMealExist(long mealId) {
        return local.doesMealExist(mealId);
    }
}
