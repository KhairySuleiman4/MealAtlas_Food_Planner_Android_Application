package com.example.foodplanner.screens.searchmealbynamescreen.presenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.navigation.Navigation;

import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.searchmealbynamescreen.view.SearchMealByNameFragmentDirections;
import com.example.foodplanner.screens.searchmealbynamescreen.view.SearchMealByNameView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchMealsByNamePresenterImp implements SearchMealByNamePresenter{
    SearchMealByNameView view;
    CompositeDisposable disposable;
    MealsRepositoryImp repo;
    public SearchMealsByNamePresenterImp(SearchMealByNameView view, MealsRepositoryImp repo) {
        this.view = view;
        this.repo = repo;
        disposable = new CompositeDisposable();
    }
    @Override
    public void observeSearch(EditText etSearch, Meal[] meals) {
        Observable<String> observable = Observable.create(emitter -> {
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString().toLowerCase());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        });
        disposable.add(
                observable.debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                query -> {
                                    Meal[] filteredMeals = Arrays.stream(meals)
                                            .filter(meal -> meal.getMealName().toLowerCase().contains(query))
                                            .toArray(Meal[]::new);
                                    view.updateSearchResults(filteredMeals);
                                }, error -> {
                                    view.showError(error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void getMealById(long mealId, View v) {
        Single<Meal> call = repo.getMealById(mealId);
        disposable.add(
                call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            SearchMealByNameFragmentDirections.ActionSearchMealByNameFragmentToMealDetailsFragment action =
                                    SearchMealByNameFragmentDirections.actionSearchMealByNameFragmentToMealDetailsFragment(item);
                            Navigation.findNavController(v).navigate(action);
                        }, error -> {
                            view.showError(error.getMessage());
                        }
                )
        );
    }
    @Override
    public void closeDisposable() {
        disposable.dispose();
    }
}
