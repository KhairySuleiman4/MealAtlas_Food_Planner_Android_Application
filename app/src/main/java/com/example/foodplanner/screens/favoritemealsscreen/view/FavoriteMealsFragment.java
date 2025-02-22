package com.example.foodplanner.screens.favoritemealsscreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.db.MealsLocalDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.favoritemealsscreen.presenter.FavoriteMealsPresenter;
import com.example.foodplanner.screens.favoritemealsscreen.presenter.FavoriteMealsPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMealsFragment extends Fragment implements FavoriteMealsView, OnFavMealClickListener {
    FavoriteMealsPresenter presenter;
    RecyclerView rvFavMeals;
    FavMealsAdapter adapter;

    public FavoriteMealsFragment() {
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
        return inflater.inflate(R.layout.fragment_favorite_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavMeals = view.findViewById(R.id.rv_favorite_meals);

        presenter = new FavoriteMealsPresenterImp(this,
                MealsRepositoryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(),
                        MealsLocalDataSourceImp.getInstance(getContext())));
        adapter = new FavMealsAdapter(getContext(), new ArrayList<>(), this);
        rvFavMeals.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavMeals.setAdapter(adapter);
        presenter.getFavMeals();
    }

    @Override
    public void showFavMeals(List<Meal> meals) {
        adapter.setFavMeals(meals);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDeleted(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveClick(Meal meal) {
        presenter.deleteMeal(meal);
    }

    @Override
    public void onImageClick(Meal meal) {
        com.example.foodplanner.screens.favoritemealsscreen.view.FavoriteMealsFragmentDirections.ActionFavoriteMealsFragmentToMealDetailsFragment action =
                FavoriteMealsFragmentDirections.actionFavoriteMealsFragmentToMealDetailsFragment(meal);
        Navigation.findNavController(requireView()).navigate(action);
    }
}