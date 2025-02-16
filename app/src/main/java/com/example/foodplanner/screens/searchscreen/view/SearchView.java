package com.example.foodplanner.screens.searchscreen.view;

import com.example.foodplanner.model.pojos.Category;

import java.util.List;

public interface SearchView {
    void showAllCategories(List<Category> categories);
    void showError(String error);
}
