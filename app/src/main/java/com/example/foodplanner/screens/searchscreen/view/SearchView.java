package com.example.foodplanner.screens.searchscreen.view;

import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Country;

import java.util.List;

public interface SearchView {
    void showAllCategories(List<Category> categories);
    void showAllCountries(List<Country> countries);
    void showError(String error);
}
