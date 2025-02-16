package com.example.foodplanner.screens.homescreen.presenter;

import com.example.foodplanner.model.network.category.CategoriesRepository;
import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.category.CategoryNetworkCallBack;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.screens.homescreen.view.HomeView;

import java.util.List;

public class HomePresenterImp implements HomePresenter, CategoryNetworkCallBack {
    HomeView view;
    CategoriesRepositoryImp repo;

    public HomePresenterImp(HomeView view, CategoriesRepositoryImp repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getCategories() {
        repo.categoryNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<Category> categories) {
        view.showAllCategories(categories);
    }

    @Override
    public void onFailResult(String error) {
        view.showError(error);
    }

}
