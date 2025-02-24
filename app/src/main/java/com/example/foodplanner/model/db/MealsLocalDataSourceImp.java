package com.example.foodplanner.model.db;

import android.content.Context;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealsLocalDataSourceImp implements MealsLocalDataSource{
    private Context context;
    private MealsDAO dao;
    private static MealsLocalDataSourceImp local = null;
    private Observable<List<Meal>> favMeals;

    private MealsLocalDataSourceImp(Context context){
        this.context = context;
        DataBase db = DataBase.getInstance(context);
        dao = db.getMealsDAO();
        favMeals = dao.getFavMeals();
    }

    public static MealsLocalDataSourceImp getInstance(Context context){
        if(local == null){
            local = new MealsLocalDataSourceImp(context);
        }
        return local;
    }
    @Override
    public Observable<List<Meal>> getFavMeals() {
        return favMeals;
    }

    @Override
    public Completable insertFavMeal(Meal meal) {
        return dao.insertFavMeal(meal);
    }

    @Override
    public Completable deleteFavMeal(Meal meal) {
        return dao.deleteFavMeal(meal);
    }

    @Override
    public Single<Boolean> isMealFavorite(long mealId) {
        return dao.isMealFavorite(mealId);
    }
    @Override
    public Completable insertPlannedMeal(Meal meal) {
        return dao.insertPlannedMeal(meal);
    }

    @Override
    public Completable deletePlannedMeal(Meal meal) {
        return dao.deletePlannedMeal(meal);
    }

    @Override
    public Observable<List<Meal>> getPlannedMeals(String date) {
        return dao.getPlannedMeals(date);
    }

    @Override
    public Single<Meal> getLastMeal() {
        return dao.getLastMeal();
    }

    @Override
    public Completable clearAllMeals() {
        return dao.clearAllMeals();
    }
}
