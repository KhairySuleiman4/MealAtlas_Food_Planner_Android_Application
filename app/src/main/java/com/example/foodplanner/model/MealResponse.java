package com.example.foodplanner.model;

import java.util.ArrayList;

public class MealResponse {
    private ArrayList<Meal> meals;

    public MealResponse(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
}
