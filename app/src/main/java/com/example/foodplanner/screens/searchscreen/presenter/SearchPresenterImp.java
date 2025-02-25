package com.example.foodplanner.screens.searchscreen.presenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.navigation.Navigation;

import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.country.CountriesRepositoryImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Country;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.homescreen.view.HomeScreenFragmentDirections;
import com.example.foodplanner.screens.searchscreen.view.SearchFragmentDirections;
import com.example.foodplanner.screens.searchscreen.view.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {
    SearchView view;
    CategoriesRepositoryImp categoriesRepo;
    MealsRepositoryImp mealsRepo;
    private TextWatcher currentTextWatcher;
    CompositeDisposable disposable;
    public SearchPresenterImp(SearchView view, CategoriesRepositoryImp categoriesRepo, MealsRepositoryImp mealsRepo) {
        this.view = view;
        this.categoriesRepo = categoriesRepo;
        this.mealsRepo = mealsRepo;
        disposable = new CompositeDisposable();
    }
    @Override
    public void getCategories() {
        Single<List<Category>> observable = categoriesRepo.categoryNetworkCall();
        disposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                item -> {
                                    view.showAllCategories(item);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void getCountries() {
        view.showAllCountries(CountriesRepositoryImp.getCountries());
    }
    @Override
    public void getIngredients() {
        Single<List<Meal>> observable = mealsRepo.ingredientsNetworkCall();
        disposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(meals -> {
                            List<String> ingredients = new ArrayList<>();
                            for (Meal meal : meals) {
                                ingredients.add(meal.getStrIngredient());
                            }
                            return Single.just(ingredients);
                        })
                        .subscribe(
                                ingredients -> {
                                    view.showAllIngredients(ingredients);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        ));
    }
    @Override
    public void getMealsByCategory(String category, View v) {
        Single<List<Meal>> observable = mealsRepo.mealsByCategory(category);
        disposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(list -> list.toArray(new Meal[0]))
                        .subscribe(
                                meals -> {
                                    SearchFragmentDirections.ActionSearchFragmentToSearchMealByNameFragment action =
                                            SearchFragmentDirections.actionSearchFragmentToSearchMealByNameFragment(meals, category);
                                    Navigation.findNavController(v).navigate(action);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void getMealsByCountry(String country, View v) {
        Single<List<Meal>> observable = mealsRepo.mealsByCountry(country);
        disposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(list -> list.toArray(new Meal[0]))
                        .subscribe(
                                meals -> {
                                    SearchFragmentDirections.ActionSearchFragmentToSearchMealByNameFragment action =
                                            SearchFragmentDirections.actionSearchFragmentToSearchMealByNameFragment(meals, country);
                                    Navigation.findNavController(v).navigate(action);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void getMealsByIngredient(String ingredient, View v) {
        Single<List<Meal>> observable = mealsRepo.mealsByIngredient(ingredient);
        disposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(list -> list.toArray(new Meal[0]))
                        .subscribe(
                                meals -> {
                                    SearchFragmentDirections.ActionSearchFragmentToSearchMealByNameFragment action =
                                            SearchFragmentDirections.actionSearchFragmentToSearchMealByNameFragment(meals, ingredient);
                                    Navigation.findNavController(v).navigate(action);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void observeCategorySearch(EditText etSearch, List<Category> categories) {
        setSearchObserver(etSearch, categories, query -> {
            List<Category> filteredList = categories.stream()
                    .filter(category -> category.getCateName().toLowerCase().contains(query))
                    .collect(Collectors.toList());
            view.updateCategoriesResults(filteredList);
        });
    }
    @Override
    public void observeCountrySearch(EditText etSearch, List<Country> countries) {
        setSearchObserver(etSearch, countries, query -> {
            List<Country> filteredList = countries.stream()
                    .filter(country -> country.getName().toLowerCase().contains(query))
                    .collect(Collectors.toList());
            view.updateCountriesResults(filteredList);
        });
    }
    @Override
    public void observeIngredientSearch(EditText etSearch, List<String> ingredients) {
        setSearchObserver(etSearch, ingredients, query -> {
            List<String> filteredList = ingredients.stream()
                    .filter(ingredient -> ingredient.toLowerCase().contains(query))
                    .collect(Collectors.toList());
            view.updateIngredientsResults(filteredList);
        });
    }
    private void setSearchObserver(EditText etSearch, List<?> items, SearchObserver observer) {
        if (currentTextWatcher != null) {
            etSearch.removeTextChangedListener(currentTextWatcher);
        }
        currentTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                observer.onSearchQuery(s.toString().toLowerCase());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        etSearch.addTextChangedListener(currentTextWatcher);
    }
    @Override
    public void closeDisposable() {
        disposable.dispose();
    }
}
