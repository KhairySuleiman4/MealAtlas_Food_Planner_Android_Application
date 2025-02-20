package com.example.foodplanner.screens.homescreen.presenter;

//import com.example.foodplanner.model.network.category.CategoriesRepository;
import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.country.CountriesRepositoryImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.homescreen.view.HomeView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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


}
