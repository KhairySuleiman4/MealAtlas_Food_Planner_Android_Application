package com.example.foodplanner.screens.mealdetailsscreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;

public class MealIngredientsAdapter extends RecyclerView.Adapter<MealIngredientsViewHolder> {
    private Context context;
    private ArrayList<String> ingriedents;
    private ArrayList<String> measures;

    public MealIngredientsAdapter(Context context, ArrayList<String> ingriedents, ArrayList<String> measures) {
        this.context = context;
        this.ingriedents = ingriedents;
        this.measures = measures;
    }

    @NonNull
    @Override
    public MealIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_meal_ingredient, viewGroup, false);
        MealIngredientsViewHolder holder = new MealIngredientsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealIngredientsViewHolder holder, int i) {
        holder.tvMeasure.setText(measures.get(i));
        holder.tvIngredient.setText(ingriedents.get(i));
    }

    @Override
    public int getItemCount() {
        return ingriedents.size();
    }
}

class MealIngredientsViewHolder extends RecyclerView.ViewHolder{
    View convertedView;
    TextView tvMeasure;
    TextView tvIngredient;
    ImageView ivIngredient;
    public MealIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        tvMeasure = itemView.findViewById(R.id.tv_meal_measure);
        tvIngredient = itemView.findViewById(R.id.tv_meal_ingredient);
        ivIngredient = itemView.findViewById(R.id.iv_ingredient);
    }
}
