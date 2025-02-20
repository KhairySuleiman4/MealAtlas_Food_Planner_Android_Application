package com.example.foodplanner.screens.homescreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
//import com.example.foodplanner.HomeScreenFragmentDirections;
import com.example.foodplanner.R;
import com.example.foodplanner.model.network.category.CategoriesRemoteDataSourceImp;
import com.example.foodplanner.model.network.category.CategoriesRepositoryImp;
import com.example.foodplanner.model.network.category.CategoryService;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Country;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.model.pojos.MealResponse;
import com.example.foodplanner.model.network.meal.MealService;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.CategoryResponse;
import com.example.foodplanner.screens.homescreen.presenter.HomePresenter;
import com.example.foodplanner.screens.homescreen.presenter.HomePresenterImp;
//import com.example.foodplanner.screens.homescreen.HomeScreenFragmentDirections;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreenFragment extends Fragment implements HomeView, OnItemClickListener {

    HomePresenter presenter;
    CategoriesAdapter categoriesAdapter;
    CountriesAdapter countriesAdapter;
    IngredientsAdapter ingredientsAdapter;
    ImageView ivMealPhoto;
    TextView tvMealName;
    RecyclerView rvCates;
    RecyclerView rvCountries;
    RecyclerView rvIngredients;

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivMealPhoto = view.findViewById(R.id.iv_meal_of_the_day);
        tvMealName = view.findViewById(R.id.tv_meal_of_the_day_title);
        rvCates = view.findViewById(R.id.rv_categories);
        rvCountries = view.findViewById(R.id.rv_countries);
        rvIngredients = view.findViewById(R.id.rv_ingredients);
        presenter = new HomePresenterImp(this,
                CategoriesRepositoryImp.getInstance(CategoriesRemoteDataSourceImp.getInstance()),
                MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance()));
        categoriesAdapter = new CategoriesAdapter(getContext(), new ArrayList<>(), this);
        countriesAdapter = new CountriesAdapter(getContext(), new ArrayList<>(), this);
        ingredientsAdapter = new IngredientsAdapter(getContext(), new ArrayList<>(), this);
        rvCates.setAdapter(categoriesAdapter);
        rvCountries.setAdapter(countriesAdapter);
        rvIngredients.setAdapter(ingredientsAdapter);
        presenter.getCategories();
        presenter.getRandomMeal();
        presenter.getCountries();
        presenter.getIngredients();
    }

    @Override
    public void showAllCategories(List<Category> categories) {
        categoriesAdapter.setCategories(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRandomMeal(Meal meal) {
        Glide.with(HomeScreenFragment.this)
                .load(meal.getMealPhoto())
                .into(ivMealPhoto);
        tvMealName.setText(meal.getMealName());
        ivMealPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreenFragmentDirections.ActionHomeScreenFragmentToMealDetailsFragment action =
                        HomeScreenFragmentDirections.actionHomeScreenFragmentToMealDetailsFragment(meal);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public void showAllCountries(List<Country> countries) {
        countriesAdapter.setCountries(countries);
        countriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllIngredients(List<String> ingredients) {
        ingredientsAdapter.setIngredients(ingredients);
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
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
}