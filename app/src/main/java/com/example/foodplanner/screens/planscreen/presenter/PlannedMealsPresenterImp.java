package com.example.foodplanner.screens.planscreen.presenter;

import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.planscreen.view.PlanScreenView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannedMealsPresenterImp implements PlannedMealsPresenter{
    PlanScreenView view;
    MealsRepositoryImp repo;
    CompositeDisposable disposable;

    public PlannedMealsPresenterImp(PlanScreenView view, MealsRepositoryImp repo) {
        this.view = view;
        this.repo = repo;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getPlannedMeals(String day) {
        disposable.add(
                repo.getPlannedMeals(day)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                item -> {
                                    view.showPlannedMeals(item);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }

    @Override
    public void deleteMeal(Meal meal) {
        disposable.add(
                repo.deletePlannedMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.showDeleted("Removed Successfully!");
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
        repo.deleteMealFromFireStore(String.valueOf(meal.getUniqueId()));
    }
}
