package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealOfTheDayFragment extends Fragment {

    ImageView ivMealPhoto;
    TextView tvMealName;
    ArrayList <Meal> meals;
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public MealOfTheDayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_of_the_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivMealPhoto = view.findViewById(R.id.iv_meal_of_the_day);
        tvMealName = view.findViewById(R.id.tv_meal_of_the_day);
        meals = new ArrayList<Meal>();
        Retrofit instance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MealService ms = instance.create(MealService.class);
        Call<MealResponse> call = ms.getMeals();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    MealResponse result = response.body();
                    meals = result.getMeals();
                    Glide.with(MealOfTheDayFragment.this)
                                    .load(meals.get(0).getMealPhoto())
                                            .into(ivMealPhoto);
                    tvMealName.setText(meals.get(0).getMealName());
                } else {
                    Log.i("TAG", "onResponse: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}