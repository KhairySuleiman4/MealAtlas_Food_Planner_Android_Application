package com.example.foodplanner.screens.searchscreen.presenter;

import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.country.CountriesRepositoryImp;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.screens.searchscreen.view.SearchView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {
    SearchView view;
    CategoriesRepositoryImp categoriesRepo;
    CompositeDisposable disposable;

    public SearchPresenterImp(SearchView view, CategoriesRepositoryImp categoriesRepo) {
        this.view = view;
        this.categoriesRepo = categoriesRepo;
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
    public void getCountries() {
        view.showAllCountries(CountriesRepositoryImp.getCountries());
    }

}
