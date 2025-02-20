package com.example.foodplanner.model.network.category;

import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.CategoryResponse;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        categoryService = retrofit.create(CategoryService.class);
    }

    @Override
    public Single<List<Category>> categoryNetworkCall() {
        Single<CategoryResponse> call = categoryService.getCategories();
        return call.map(l -> l.getCates());
    }
}
