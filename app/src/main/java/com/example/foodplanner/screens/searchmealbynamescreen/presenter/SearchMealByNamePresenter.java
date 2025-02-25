package com.example.foodplanner.screens.searchmealbynamescreen.presenter;

import android.view.View;
import android.widget.EditText;

import com.example.foodplanner.model.pojos.Meal;

public interface SearchMealByNamePresenter {
    void observeSearch(EditText etSearch, Meal[] meals);
    void getMealById(long mealId, View view);
    void closeDisposable();
}
