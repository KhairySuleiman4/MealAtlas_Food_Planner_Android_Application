package com.example.foodplanner.screens.searchmealbynamescreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.homescreen.view.HomeScreenFragment;
//import com.example.foodplanner.screens.searchscreen.view.IngredientsFilterViewHolder;

public class MealsAdapter extends RecyclerView.Adapter<MealsViewHolder> {
    private Context context;
    private Meal[] meals;

    public MealsAdapter(Context context, Meal[] meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MealsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_filter, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int i) {
        Glide.with(context)
                .load(meals[i].getMealPhoto())
                .into(holder.ivMealPhoto);
        holder.tvMealName.setText(meals[i].getMealName());
    }

    @Override
    public int getItemCount() {
        return meals.length;
    }
}

class MealsViewHolder extends RecyclerView.ViewHolder{
    TextView tvMealName;
    ImageView ivMealPhoto;
    public MealsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMealName = itemView.findViewById(R.id.tv_item_filter_title);
        ivMealPhoto = itemView.findViewById(R.id.iv_item_filter);

    }
}
