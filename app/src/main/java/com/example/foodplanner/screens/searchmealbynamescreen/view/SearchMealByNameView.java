package com.example.foodplanner.screens.searchmealbynamescreen.view;

import com.example.foodplanner.model.pojos.Meal;

public interface SearchMealByNameView {
    void updateSearchResults(Meal[] meals);
    void showError(String error);
}
