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

import java.util.List;

public class CategoriesFilterAdapter extends RecyclerView.Adapter<CategoriesFilterViewHolder> {

    private Context context;
    private List<Category> categories;

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public CategoriesFilterAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesFilterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_filter, viewGroup, false);
        CategoriesFilterViewHolder holder = new CategoriesFilterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesFilterViewHolder holder, int i) {
        Glide.with(context)
                .load(categories.get(i).getCateImage())
                .into(holder.ivFilter);
        holder.tvFilter.setText(categories.get(i).getCateName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}

class CategoriesFilterViewHolder extends RecyclerView.ViewHolder{

    View convertedView;
    ImageView ivFilter;
    TextView tvFilter;
    public CategoriesFilterViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        ivFilter = itemView.findViewById(R.id.iv_item_filter);
        tvFilter = itemView.findViewById(R.id.tv_item_filter_title);
    }
}
