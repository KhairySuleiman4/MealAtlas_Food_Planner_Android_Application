package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Meal;

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
}
