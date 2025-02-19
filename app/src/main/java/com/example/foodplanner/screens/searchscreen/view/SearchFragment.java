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

import com.example.foodplanner.R;
import com.example.foodplanner.model.network.category.CategoriesRemoteDataSourceImp;
import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.category.CategoryService;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.CategoryResponse;
import com.example.foodplanner.model.pojos.Country;
import com.example.foodplanner.screens.homescreen.view.CategoriesAdapter;
import com.example.foodplanner.screens.homescreen.view.CountriesAdapter;
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

public class SearchFragment extends Fragment implements SearchView {

    ChipGroup chipGroup;
    Chip cateChip;
    Chip countryChip;
    RecyclerView rvFilter;
    CategoriesFilterAdapter categoriesAdapter;
    CountriesFilterAdapter countriesAdapter;
    SearchPresenter presenter;
    //FilterAdapter adapter;

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
        chipGroup.setSingleSelection(true);
        presenter = new SearchPresenterImp(this,
                CategoriesRepositoryImp.getInstance(CategoriesRemoteDataSourceImp.getInstance()));
        categoriesAdapter = new CategoriesFilterAdapter(getContext(), new ArrayList<>());
        countriesAdapter = new CountriesFilterAdapter(getContext(), new ArrayList<>());
        //adapter = new FilterAdapter(getContext(), new ArrayList<>(), new ArrayList<>());
        //rvFilter.setLayoutManager(new LinearLayoutManager(getContext()));
        //rvFilter.setAdapter(adapter);
        cateChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvFilter.setAdapter(categoriesAdapter);
                presenter.getCategories();
            }
        });
        countryChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvFilter.setAdapter(countriesAdapter);
                presenter.getCountries();
            }
        });

    }

    @Override
    public void showAllCategories(List<Category> categories) {
        categoriesAdapter.setCategories(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllCountries(List<Country> countries) {
        countriesAdapter.setCountries(countries);
        countriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {

    }

}
