package com.example.foodplanner.screens.favoritemealsscreen.view;

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

import java.util.List;

public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsViewHolder> {
    private Context context;
    private List<Meal> favMeals;
    private OnFavMealClickListener listener;

    public FavMealsAdapter(Context context, List<Meal> favMeals, OnFavMealClickListener listener) {
        this.context = context;
        this.favMeals = favMeals;
        this.listener = listener;
    }

    public void setFavMeals(List<Meal> favMeals) {
        this.favMeals = favMeals;
    }

    @NonNull
    @Override
    public FavMealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FavMealsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_favorite_meal, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavMealsViewHolder holder, int i) {
        Meal meal = favMeals.get(i);
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
        return favMeals.size();
    }
}

class FavMealsViewHolder extends RecyclerView.ViewHolder{
    ImageView ivMeal;
    TextView tvMeal;
    Button btnRemove;
    public FavMealsViewHolder(@NonNull View itemView) {
        super(itemView);
        ivMeal = itemView.findViewById(R.id.iv_favorite_photo);
        tvMeal = itemView.findViewById(R.id.tv_favorite_meal_name);
        btnRemove = itemView.findViewById(R.id.btn_remove_from_favorites);
    }
}