package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MealDetailsFragment extends Fragment {

    ImageView ivMealPhoto;
    TextView tvMealTitle;
    TextView tvMealCate;
    TextView tvMealArea;
    TextView tvInst;

    public MealDetailsFragment() {
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
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivMealPhoto = view.findViewById(R.id.iv_meal_photo);
        tvMealTitle = view.findViewById(R.id.tv_meal_title);
        tvMealCate = view.findViewById(R.id.tv_meal_category);
        tvMealArea = view.findViewById(R.id.tv_meal_area);
        tvInst = view.findViewById(R.id.tv_meal_inst);

        Meal meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealDetails();

        Glide.with(MealDetailsFragment.this)
                .load(meal.getMealPhoto())
                .into(ivMealPhoto);
        tvMealTitle.setText(meal.getMealName());
        tvMealCate.setText(meal.getMealCate());
        tvMealArea.setText(meal.getMealArea());
        tvInst.setText(meal.getMealInst());
    }
}