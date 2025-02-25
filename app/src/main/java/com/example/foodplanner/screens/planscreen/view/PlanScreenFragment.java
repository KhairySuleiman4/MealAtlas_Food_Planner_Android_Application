package com.example.foodplanner.screens.planscreen.view;

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
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.db.MealsLocalDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.favoritemealsscreen.presenter.FavoriteMealsPresenter;
import com.example.foodplanner.screens.favoritemealsscreen.view.FavMealsAdapter;
import com.example.foodplanner.screens.planscreen.presenter.PlannedMealsPresenter;
import com.example.foodplanner.screens.planscreen.presenter.PlannedMealsPresenterImp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlanScreenFragment extends Fragment implements PlanScreenView, OnPlannedMealClickListener{
    PlannedMealsPresenter presenter;
    RecyclerView rvPlannedMeals;
    PlannedMealsAdapter adapter;
    DatePicker datePicker;

    public PlanScreenFragment() {
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
        return inflater.inflate(R.layout.fragment_plan_screen, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPlannedMeals = view.findViewById(R.id.rv_planned_meals);
        datePicker = view.findViewById(R.id.dp_plan);
        presenter = new PlannedMealsPresenterImp(this, MealsRepositoryImp.getInstance(
                MealsRemoteDataSourceImp.getInstance(),
                MealsLocalDataSourceImp.getInstance(getContext())));
        adapter = new PlannedMealsAdapter(getContext(), new ArrayList<>(), this);
        rvPlannedMeals.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPlannedMeals.setAdapter(adapter);

        Calendar today = Calendar.getInstance();
        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int month, int day) {
                        String date = year + "-" + month + "-" + day;
                        presenter.getPlannedMeals(date);
                    }
                }
        );
    }
    @Override
    public void showPlannedMeals(List<Meal> meals) {
        adapter.setPlannedMeals(meals);
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
        com.example.foodplanner.screens.planscreen.view.PlanScreenFragmentDirections.ActionPlanScreenFragmentToMealDetailsFragment action =
                PlanScreenFragmentDirections.actionPlanScreenFragmentToMealDetailsFragment(meal);
        Navigation.findNavController(requireView()).navigate(action);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.closeDisposable();
    }
}