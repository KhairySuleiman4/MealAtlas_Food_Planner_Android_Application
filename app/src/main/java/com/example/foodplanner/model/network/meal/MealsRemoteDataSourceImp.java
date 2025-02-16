package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.MealResponse;

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
                .build();
        mealService = retrofit.create(MealService.class);
    }

    @Override
    public void mealNetworkCall(MealNetworkCallBack mealNetworkCallBack) {
        Call<MealResponse> call = mealService.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful())
                    mealNetworkCallBack.onSuccessRandomMealResult(response.body().getMeals());
                else
                    mealNetworkCallBack.onFailRandomMealResult(response.message());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                mealNetworkCallBack.onFailRandomMealResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
