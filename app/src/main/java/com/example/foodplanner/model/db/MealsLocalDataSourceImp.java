package com.example.foodplanner.model.db;

import android.content.Context;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealsLocalDataSourceImp implements MealsLocalDataSource{
    private Context context;
    private FavoriteMealsDAO dao;
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
    public Completable insertMeal(Meal meal) {
        return dao.insertMeal(meal);
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return dao.deleteMeal(meal);
    }

    @Override
    public Single<Boolean> doesMealExist(long mealId) {
        return dao.doesMealExist(mealId);
    }
}
