package com.example.foodplanner.screens.favoritemealsscreen.presenter;

import com.example.foodplanner.model.pojos.Meal;

public interface FavoriteMealsPresenter {
    void getFavMeals();
    void deleteMeal(Meal meal);
}
