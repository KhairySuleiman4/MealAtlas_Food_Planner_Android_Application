package com.example.foodplanner.screens.searchscreen.view;

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
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.screens.homescreen.view.OnItemClickListener;

import java.util.List;

public class IngredientsFilterAdapter extends RecyclerView.Adapter<IngredientsFilterViewHolder>{
    private Context context;
    private List<String> ingredients;
    private OnItemClickListener listener;
    public IngredientsFilterAdapter(Context context, List<String> ingredients, OnItemClickListener listener) {
        this.context = context;
        this.ingredients = ingredients;
        this.listener = listener;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsFilterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new IngredientsFilterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_filter, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsFilterViewHolder holder, int i) {
        String ingredient = ingredients.get(i);
        holder.tvFilter.setText(ingredient);
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/" + ingredient + ".png")
                .into(holder.ivFilter);
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

class IngredientsFilterViewHolder extends RecyclerView.ViewHolder{
    View convertedView;
    ImageView ivFilter;
    TextView tvFilter;
    public IngredientsFilterViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        ivFilter = itemView.findViewById(R.id.iv_item_filter);
        tvFilter = itemView.findViewById(R.id.tv_item_filter_title);
    }
}