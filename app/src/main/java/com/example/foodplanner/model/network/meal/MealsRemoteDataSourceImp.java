package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.model.pojos.MealResponse;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp implements MealsRemoteDataSource{
    MealService mealService;
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealsRemoteDataSourceImp remote = null;
    public static MealsRemoteDataSourceImp getInstance(){
        if(remote == null){
            remote = new MealsRemoteDataSourceImp();
        }
        return remote;
    }
    private MealsRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }

    @Override
    public Single<Meal> mealNetworkCall() {
        Single<MealResponse> call = mealService.getRandomMeal();
        return call.map(l -> l.getMeals().get(0));
    }

    @Override
    public Single<List<Meal>> ingredientsNetworkCall() {
        Single<MealResponse> call = mealService.getAllIngredients();
        return call.map(l -> l.getMeals());
    }

    @Override
    public Single<List<Meal>> mealsByCategory(String category) {
        Single<MealResponse> call = mealService.getMealsByCategory(category);
        return call.map(l -> l.getMeals());
    }

    @Override
    public Single<List<Meal>> mealsByCountry(String country) {
        Single<MealResponse> call = mealService.getMealsByCountry(country);
        return call.map(l -> l.getMeals());
    }

    @Override
    public Single<List<Meal>> mealsByIngredient(String ingredient) {
        Single<MealResponse> call = mealService.getMealsByIngredient(ingredient);
        return call.map(l -> l.getMeals());
    }
}
