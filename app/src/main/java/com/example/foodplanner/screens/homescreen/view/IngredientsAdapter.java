package com.example.foodplanner.screens.homescreen.view;

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

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {
    private Context context;
    private List<String> ingredients;
    private OnItemClickListener listener;

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public IngredientsAdapter(Context context, List<String> ingredients, OnItemClickListener listener) {
        this.context = context;
        this.ingredients = ingredients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int i) {
        String ingredient = ingredients.get(i);
        holder.tvIngredient.setText(ingredient);
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/" + ingredient + ".png")
                .into(holder.ivIngredient);
        holder.convertedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIngredientClick(ingredient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder{
    View convertedView;
    ImageView ivIngredient;
    TextView tvIngredient;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        ivIngredient = itemView.findViewById(R.id.iv_card_item);
        tvIngredient = itemView.findViewById(R.id.tv_card_item);
    }
}