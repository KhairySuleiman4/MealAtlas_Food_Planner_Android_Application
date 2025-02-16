package com.example.foodplanner.screens.homescreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
//import com.example.foodplanner.HomeScreenFragmentDirections;
import com.example.foodplanner.R;
import com.example.foodplanner.model.network.category.CategoryService;
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

public class HomeScreenFragment extends Fragment implements HomeView {

    HomePresenter presenter;
    CategoriesAdapter categoriesAdapter;
    ImageView ivMealPhoto;
    TextView tvMealName;
    ArrayList <Meal> meals;
    RecyclerView rvCates;
    RecyclerView rvCountries;
    ArrayList<Country> countries;

    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

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
        //presenter = new HomePresenterImp(this, )
        countries = new ArrayList<>();
        //cates = new ArrayList<>();
        meals = new ArrayList<>();

        Retrofit instance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MealService ms = instance.create(MealService.class);
        Call<MealResponse> callRandom = ms.getRandomMeal();
        callRandom.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    MealResponse result = response.body();
                    meals = result.getMeals();
                    Meal rMeal = meals.get(0);
                    Glide.with(HomeScreenFragment.this)
                                    .load(rMeal.getMealPhoto())
                                            .into(ivMealPhoto);
                    tvMealName.setText(rMeal.getMealName());
                    ivMealPhoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            HomeScreenFragmentDirections.ActionHomeScreenFragmentToMealDetailsFragment action =
//                                    HomeScreenFragmentDirections.actionHomeScreenFragmentToMealDetailsFragment(rMeal);
//                            Navigation.findNavController(view).navigate(action);
                        }
                    });
                } else {
                    Log.i("TAG", "onResponse: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        CategoryService cs = instance.create(CategoryService.class);
        Call<CategoryResponse> callCategory = cs.getCategories();
        callCategory.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    CategoryResponse result = response.body();
                    //cates = result.getCates();
                    //CategoriesAdapter adapter = new CategoriesAdapter(getContext(), cates);
                    //rvCates.setAdapter(adapter);
                } else {
                    Log.i("TAG", "onResponse: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        countries.add(new Country("USA", R.drawable.usa));
        countries.add(new Country("UK", R.drawable.uk));
        countries.add(new Country("Canada", R.drawable.canada));
        countries.add(new Country("China", R.drawable.china));
        countries.add(new Country("Croatia", R.drawable.croatia));
        countries.add(new Country("Netherlands", R.drawable.netherlands));
        countries.add(new Country("Egypt", R.drawable.egypt));
        countries.add(new Country("Philippines", R.drawable.philippines));
        countries.add(new Country("France", R.drawable.france));
        countries.add(new Country("Greece", R.drawable.greece));
        CountriesAdapter adapter = new CountriesAdapter(getContext(), countries);
        rvCountries.setAdapter(adapter);

    }

    @Override
    public void showAllCategories(List<Category> categories) {

    }

    @Override
    public void showError(String error) {

    }
}