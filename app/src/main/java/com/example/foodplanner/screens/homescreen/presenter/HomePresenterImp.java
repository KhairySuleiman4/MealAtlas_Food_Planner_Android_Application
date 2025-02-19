package com.example.foodplanner.screens.homescreen.presenter;

//import com.example.foodplanner.model.network.category.CategoriesRepository;
import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.category.CategoryNetworkCallBack;
import com.example.foodplanner.model.network.country.CountriesRepositoryImp;
import com.example.foodplanner.model.network.meal.MealNetworkCallBack;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Country;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.homescreen.view.HomeView;

import java.util.Collections;
import java.util.List;

public class HomePresenterImp implements HomePresenter, CategoryNetworkCallBack, MealNetworkCallBack {
    HomeView view;
    CategoriesRepositoryImp categoriesRepo;
    MealsRepositoryImp mealsRepo;

    public HomePresenterImp(HomeView view, CategoriesRepositoryImp categoriesRepo, MealsRepositoryImp mealsRepo) {
        this.view = view;
        this.categoriesRepo = categoriesRepo;
        this.mealsRepo = mealsRepo;
    }

    @Override
    public void getCategories() {
        categoriesRepo.categoryNetworkCall(this);
    }

    @Override
    public void getRandomMeal() {
        mealsRepo.mealNetworkCall(this);
    }

    @Override
    public void getCountries() {
        view.showAllCountries(CountriesRepositoryImp.getCountries());
    }

    @Override
    public void onSuccessResult(List<Category> categories) {
        view.showAllCategories(categories);
    }

    @Override
    public void onFailResult(String error) {
        view.showError(error);
    }

    @Override
    public void onSuccessRandomMealResult(List<Meal> meals) {
        view.showRandomMeal(meals.get(0));
    }

    @Override
    public void onFailRandomMealResult(String error) {
        view.showError(error);
    }

}
