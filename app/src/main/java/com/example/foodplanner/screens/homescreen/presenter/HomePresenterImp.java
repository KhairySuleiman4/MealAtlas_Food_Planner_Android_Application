package com.example.foodplanner.screens.homescreen.presenter;

//import com.example.foodplanner.model.network.category.CategoriesRepository;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.firebase.auth.FirebaseAuth;
import androidx.navigation.Navigation;
import androidx.navigation.Navigation;

import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.country.CountriesRepositoryImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.homescreen.view.HomeScreenFragmentDirections;
import com.example.foodplanner.screens.homescreen.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter {
    HomeView view;
    CategoriesRepositoryImp categoriesRepo;
    MealsRepositoryImp mealsRepo;
    CompositeDisposable disposable;
    public HomePresenterImp(HomeView view, CategoriesRepositoryImp categoriesRepo, MealsRepositoryImp mealsRepo) {
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
        //disposable.dispose();
    }
    @Override
    public void getRandomMeal() {
        Single<Meal> observable = mealsRepo.mealNetworkCall();
        disposable.add(
                observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            view.showRandomMeal(item);
                        }, error -> {
                            view.showError(error.getMessage());
                        }
                ));
        //disposable.dispose();
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
        //disposable.dispose();
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
                                    HomeScreenFragmentDirections.ActionHomeScreenFragmentToSearchMealByNameFragment action =
                                            HomeScreenFragmentDirections.actionHomeScreenFragmentToSearchMealByNameFragment(meals, category);
                                    Navigation.findNavController(v).navigate(action);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
        //disposable.dispose();
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
                                    HomeScreenFragmentDirections.ActionHomeScreenFragmentToSearchMealByNameFragment action =
                                            HomeScreenFragmentDirections.actionHomeScreenFragmentToSearchMealByNameFragment(meals, country);
                                    Navigation.findNavController(v).navigate(action);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
        //disposable.dispose();
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
                                    HomeScreenFragmentDirections.ActionHomeScreenFragmentToSearchMealByNameFragment action =
                                            HomeScreenFragmentDirections.actionHomeScreenFragmentToSearchMealByNameFragment(meals, ingredient);
                                    Navigation.findNavController(v).navigate(action);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
        //disposable.dispose();
    }
    @Override
    public void logout(View v) {
        disposable.add(
                mealsRepo.logOut()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    Log.i("TAG", "logout: db cleared");
                                },
                                error -> {
                                    Log.i("TAG", "logout: " + error.getMessage());
                                }
                        )
        );
        //disposable.dispose()
        Navigation.findNavController(v).navigate(R.id.action_homeScreenFragment_to_welcomeFragment);
    }
    @Override
    public boolean isGuest() {
        return mealsRepo.isGuest();
    }
}
