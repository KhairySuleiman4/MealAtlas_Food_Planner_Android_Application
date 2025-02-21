package com.example.foodplanner.screens.favoritemealsscreen.presenter;

import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.favoritemealsscreen.view.FavoriteMealsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealsPresenterImp implements FavoriteMealsPresenter{

    FavoriteMealsView view;
    MealsRepositoryImp repo;
    CompositeDisposable disposable;

    public FavoriteMealsPresenterImp(FavoriteMealsView view, MealsRepositoryImp repo) {
        this.view = view;
        this.repo = repo;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getFavMeals() {
        disposable.add(
                repo.getFavMeals().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(item -> view.showFavMeals(item))
        );
    }

    @Override
    public void deleteMeal(Meal meal) {
        disposable.add(
                repo.deleteMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.showDeleted("Removed Successfully!");
                                }
                        )
        );
    }
}
