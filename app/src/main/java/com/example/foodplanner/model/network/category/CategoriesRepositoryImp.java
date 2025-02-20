package com.example.foodplanner.model.network.category;

import com.example.foodplanner.model.network.meal.MealsRemoteDataSource;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Category;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CategoriesRepositoryImp implements CategoriesRepository{

    CategoriesRemoteDataSource remote;

    private static CategoriesRepositoryImp repo = null;

    private CategoriesRepositoryImp(CategoriesRemoteDataSource remote){
        this.remote = remote;
    }

    public static CategoriesRepositoryImp getInstance(CategoriesRemoteDataSource remote){
        if(repo == null){
            repo = new CategoriesRepositoryImp(remote);
        }
        return repo;
    }

    @Override
    public Single<List<Category>> categoryNetworkCall() {
        return remote.categoryNetworkCall();
    }
}
