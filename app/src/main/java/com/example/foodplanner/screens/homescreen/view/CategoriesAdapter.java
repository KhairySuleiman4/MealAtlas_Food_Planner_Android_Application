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
import com.example.foodplanner.model.pojos.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private Context context;
    private List<Category> categories;

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public CategoriesAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item, viewGroup, false);
        CategoriesViewHolder holder = new CategoriesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int i) {
        Glide.with(context)
                .load(categories.get(i).getCateImage())
                .into(holder.ivCateImage);
        holder.tvCateName.setText(categories.get(i).getCateName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}

class CategoriesViewHolder extends RecyclerView.ViewHolder{

    View convertedView;
    ImageView ivCateImage;
    TextView tvCateName;

    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        ivCateImage = itemView.findViewById(R.id.iv_card_item);
        tvCateName = itemView.findViewById(R.id.tv_card_item);
    }
}
