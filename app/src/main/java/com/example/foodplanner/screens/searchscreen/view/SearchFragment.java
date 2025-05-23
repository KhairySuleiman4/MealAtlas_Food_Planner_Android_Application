package com.example.foodplanner.screens.searchscreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.db.MealsLocalDataSourceImp;
import com.example.foodplanner.model.network.category.CategoriesRemoteDataSourceImp;
import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.category.CategoryService;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.CategoryResponse;
import com.example.foodplanner.model.pojos.Country;
import com.example.foodplanner.screens.homescreen.view.CategoriesAdapter;
import com.example.foodplanner.screens.homescreen.view.CountriesAdapter;
import com.example.foodplanner.screens.homescreen.view.IngredientsAdapter;
import com.example.foodplanner.screens.homescreen.view.OnItemClickListener;
import com.example.foodplanner.screens.searchscreen.presenter.SearchPresenter;
import com.example.foodplanner.screens.searchscreen.presenter.SearchPresenterImp;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment implements SearchView, OnItemClickListener {
    ChipGroup chipGroup;
    Chip cateChip;
    Chip countryChip;
    Chip ingredientsChip;
    RecyclerView rvFilter;
    CategoriesFilterAdapter categoriesAdapter;
    CountriesFilterAdapter countriesAdapter;
    IngredientsFilterAdapter ingredientsAdapter;
    SearchPresenter presenter;
    EditText etSearch;
    View v;

    public SearchFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipGroup = view.findViewById(R.id.chip_group_filters);
        rvFilter = view.findViewById(R.id.rv_filter);
        cateChip = view.findViewById(R.id.chip_categories);
        countryChip = view.findViewById(R.id.chip_areas);
        ingredientsChip = view.findViewById(R.id.chip_ingredients);
        etSearch = view.findViewById(R.id.et_search);
        v = view;
        chipGroup.setSingleSelection(true);
        cateChip.setCheckable(true);
        countryChip.setCheckable(true);
        ingredientsChip.setCheckable(true);

        presenter = new SearchPresenterImp(this,
                CategoriesRepositoryImp.getInstance(CategoriesRemoteDataSourceImp.getInstance()),
                MealsRepositoryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(),
                        MealsLocalDataSourceImp.getInstance(getContext())));

        categoriesAdapter = new CategoriesFilterAdapter(getContext(), new ArrayList<>(), this);
        countriesAdapter = new CountriesFilterAdapter(getContext(), new ArrayList<>(), this);
        ingredientsAdapter = new IngredientsFilterAdapter(getContext(), new ArrayList<>(), this);

        cateChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryChip.setChecked(false);
                ingredientsChip.setChecked(false);
                cateChip.setChecked(true);
                etSearch.setHint("Search By Category");
                rvFilter.setAdapter(categoriesAdapter);
                presenter.getCategories();
            }
        });

        countryChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryChip.setChecked(true);
                ingredientsChip.setChecked(false);
                cateChip.setChecked(false);
                etSearch.setHint("Search By Country");
                rvFilter.setAdapter(countriesAdapter);
                presenter.getCountries();
            }
        });

        ingredientsChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryChip.setChecked(false);
                ingredientsChip.setChecked(true);
                cateChip.setChecked(false);
                etSearch.setHint("Search By Ingredient");
                rvFilter.setAdapter(ingredientsAdapter);
                presenter.getIngredients();
            }
        });
    }
    @Override
    public void showAllCategories(List<Category> categories) {
        categoriesAdapter.setCategories(categories);
        presenter.observeCategorySearch(etSearch, categories);
        categoriesAdapter.notifyDataSetChanged();
    }
    @Override
    public void showAllCountries(List<Country> countries) {
        countriesAdapter.setCountries(countries);
        presenter.observeCountrySearch(etSearch, countries);
        countriesAdapter.notifyDataSetChanged();
    }
    @Override
    public void showAllIngredients(List<String> ingredients) {
        ingredientsAdapter.setIngredients(ingredients);
        presenter.observeIngredientSearch(etSearch, ingredients);
        ingredientsAdapter.notifyDataSetChanged();
    }
    @Override
    public void updateCategoriesResults(List<Category> categories) {
        rvFilter.setAdapter(new CategoriesFilterAdapter(getContext(), categories, this));
    }
    @Override
    public void updateCountriesResults(List<Country> countries) {
        rvFilter.setAdapter(new CountriesFilterAdapter(getContext(), countries, this));
    }
    @Override
    public void updateIngredientsResults(List<String> ingredients) {
        rvFilter.setAdapter(new IngredientsFilterAdapter(getContext(), ingredients, this));
    }
    @Override
    public void showError(String error) {
        Toast.makeText(requireContext(), "Check your Internet Connection!", Toast.LENGTH_SHORT).show();
        v.setVisibility(View.GONE);
    }
    @Override
    public void onCategoryClick(String category) {
        presenter.getMealsByCategory(category, requireView());
    }
    @Override
    public void onCountryClick(String country) {
        presenter.getMealsByCountry(country, requireView());
    }
    @Override
    public void onIngredientClick(String ingredient) {
        presenter.getMealsByIngredient(ingredient, requireView());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.closeDisposable();
    }
}
