package com.example.foodplanner.screens.homeactivity.presenter;

import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.screens.homeactivity.view.HomeActivityView;

public class HomeActivityPresenterImp implements HomeActivityPresenter{
    HomeActivityView view;
    MealsRepositoryImp repo;

    public HomeActivityPresenterImp(HomeActivityView view, MealsRepositoryImp repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public boolean isGuest() {
        return repo.isGuest();
    }
}
