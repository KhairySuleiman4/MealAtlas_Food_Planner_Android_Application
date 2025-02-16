package com.example.foodplanner.screens.homescreen.view;

import com.example.foodplanner.model.pojos.Category;

import java.util.List;

public interface HomeView {
    void showAllCategories(List<Category> categories);
    void showError(String error);
}
