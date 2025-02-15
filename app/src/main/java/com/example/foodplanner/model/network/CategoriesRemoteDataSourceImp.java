package com.example.foodplanner.model.network;

import com.example.foodplanner.model.pojos.CategoryResponse;
import com.example.foodplanner.model.pojos.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesRemoteDataSourceImp implements CategoriesRemoteDataSource{
    CategoryService categoryService;
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static CategoriesRemoteDataSourceImp remote = null;
    public static CategoriesRemoteDataSourceImp getInstance(){
        if(remote == null){
            remote = new CategoriesRemoteDataSourceImp();
        }
        return remote;
    }
    private CategoriesRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoryService = retrofit.create(CategoryService.class);
    }

    @Override
    public void categoryNetworkCall(NetworkCallBack networkCallBack) {
        Call<CategoryResponse> call = categoryService.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {

            }
        });
    }
}
