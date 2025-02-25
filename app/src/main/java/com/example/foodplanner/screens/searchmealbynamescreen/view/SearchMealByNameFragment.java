package com.example.foodplanner.screens.searchmealbynamescreen.view;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.db.MealsLocalDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.searchmealbynamescreen.presenter.SearchMealsByNamePresenterImp;
//import com.example.foodplanner.screens.SearchMealByNameFragmentArgs;

public class SearchMealByNameFragment extends Fragment implements SearchMealByNameView, OnMealClickListener {
    TextView tvTypeName;
    EditText etSearch;
    RecyclerView rvMeals;
    SearchMealsByNamePresenterImp presenter;

    public SearchMealByNameFragment() {
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
        return inflater.inflate(R.layout.fragment_search_meal_by_name, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTypeName = view.findViewById(R.id.tv_meals_type);
        etSearch = view.findViewById(R.id.et_search_meal);
        rvMeals = view.findViewById(R.id.rv_search_meals);
        presenter = new SearchMealsByNamePresenterImp(this,
                MealsRepositoryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(),
                        MealsLocalDataSourceImp.getInstance(getContext())));
        Meal[] meals = com.example.foodplanner.screens.searchmealbynamescreen.view.SearchMealByNameFragmentArgs.fromBundle(getArguments()).getMealsOfAType();
        String type = com.example.foodplanner.screens.searchmealbynamescreen.view.SearchMealByNameFragmentArgs.fromBundle(getArguments()).getTypeName();

        tvTypeName.setText(type + " Meals: ");
        rvMeals.setAdapter(new MealsAdapter(getContext(), meals, this));
        rvMeals.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.observeSearch(etSearch, meals);
    }
    @Override
    public void updateSearchResults(Meal[] meals) {
        rvMeals.setAdapter(new MealsAdapter(getContext(), meals, this));
    }
    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMealClick(long mealId) {
        presenter.getMealById(mealId, requireView());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.closeDisposable();
    }
}