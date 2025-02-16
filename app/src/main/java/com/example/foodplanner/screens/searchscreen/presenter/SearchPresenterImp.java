package com.example.foodplanner.screens.searchscreen.presenter;

import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.category.CategoryNetworkCallBack;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.screens.searchscreen.view.SearchView;

import java.util.List;

public class SearchPresenterImp implements SearchPresenter, CategoryNetworkCallBack {
    SearchView view;
    CategoriesRepositoryImp repo;

    public SearchPresenterImp(SearchView view, CategoriesRepositoryImp repo) {
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
