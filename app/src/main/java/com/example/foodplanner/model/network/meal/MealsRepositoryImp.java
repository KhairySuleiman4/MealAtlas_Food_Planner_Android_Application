package com.example.foodplanner.model.network.meal;

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
    public void mealNetworkCall(MealNetworkCallBack mealNetworkCallBack) {
        remote.mealNetworkCall(mealNetworkCallBack);
    }
}
