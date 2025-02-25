package com.example.foodplanner.screens.planscreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Meal;
//import com.example.foodplanner.screens.favoritemealsscreen.view.FavMealsViewHolder;
import com.example.foodplanner.screens.favoritemealsscreen.view.OnFavMealClickListener;

import java.util.List;

public class PlannedMealsAdapter extends RecyclerView.Adapter<PlannedMealsViewHolder> {
    private Context context;
    private List<Meal> plannedMeals;
    private OnPlannedMealClickListener listener;

    public PlannedMealsAdapter(Context context, List<Meal> plannedMeals, OnPlannedMealClickListener listener) {
        this.context = context;
        this.plannedMeals = plannedMeals;
        this.listener = listener;
    }

    public void setPlannedMeals(List<Meal> plannedMeals) {
        this.plannedMeals = plannedMeals;
    }

    @NonNull
    @Override
    public PlannedMealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlannedMealsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stored_meal, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlannedMealsViewHolder holder, int i) {
        Meal meal = plannedMeals.get(i);
        Glide.with(context)
                .load(meal.getMealPhoto())
                .into(holder.ivMeal);
        holder.tvMeal.setText(meal.getMealName());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveClick(meal);
            }
        });
        holder.ivMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageClick(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plannedMeals.size();
    }
}

class PlannedMealsViewHolder extends RecyclerView.ViewHolder{
    ImageView ivMeal;
    TextView tvMeal;
    ImageView btnRemove;
    public PlannedMealsViewHolder(@NonNull View itemView) {
        super(itemView);
        ivMeal = itemView.findViewById(R.id.iv_favorite_photo);
        tvMeal = itemView.findViewById(R.id.tv_favorite_meal_name);
        btnRemove = itemView.findViewById(R.id.btn_remove_from_favorites);
    }
}