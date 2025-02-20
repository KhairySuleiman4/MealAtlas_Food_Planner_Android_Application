package com.example.foodplanner.screens.searchmealbynamescreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Meal;
//import com.example.foodplanner.screens.SearchMealByNameFragmentArgs;

public class SearchMealByNameFragment extends Fragment {
    TextView tvTypeName;
    EditText etSearch;
    RecyclerView rvMeals;

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

        Meal[] meals = com.example.foodplanner.screens.searchmealbynamescreen.view.SearchMealByNameFragmentArgs.fromBundle(getArguments()).getMealsOfAType();
        String type = com.example.foodplanner.screens.searchmealbynamescreen.view.SearchMealByNameFragmentArgs.fromBundle(getArguments()).getTypeName();

        tvTypeName.setText(type + " Meals: ");
        rvMeals.setAdapter(new MealsAdapter(getContext(), meals));
        rvMeals.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}