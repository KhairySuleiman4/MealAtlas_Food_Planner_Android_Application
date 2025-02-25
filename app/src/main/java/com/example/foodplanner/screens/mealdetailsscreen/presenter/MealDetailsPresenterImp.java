package com.example.foodplanner.screens.mealdetailsscreen.presenter;

import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.mealdetailsscreen.view.MealDetailsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter{
    MealDetailsView view;
    MealsRepositoryImp repo;
    CompositeDisposable disposable;
    public MealDetailsPresenterImp(MealDetailsView view, MealsRepositoryImp repo) {
        this.view = view;
        this.repo = repo;
        disposable = new CompositeDisposable();
    }
    @Override
    public void insertMeal(Meal meal) {
        disposable.add(
                repo.insertMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.showFeedback("Added Successfully!");
                                    repo.addMealToFireStore();
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void deleteMealFromFavorite(Meal meal) {
        disposable.add(
                repo.deleteMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.showFeedback("Deleted Successfully!");
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
        repo.deleteMealFromFireStore(String.valueOf(meal.getUniqueId()));
    }
    @Override
    public void checkIsFavorite(long mealId) {
        disposable.add(
                repo.doesMealExist(mealId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                exists -> {
                                    if(exists)
                                        view.mealIsFavorite();
                                    else
                                        view.mealIsNotFavorite();
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void insertPlannedMeal(Meal meal) {
        disposable.add(
                repo.insertPlannedMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.showFeedback("Added Successfully!");
                                    repo.addMealToFireStore();
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public boolean isGuest() {
        return repo.isGuest();
    }
    @Override
    public void closeDisposable() {
        disposable.dispose();
    }
}
