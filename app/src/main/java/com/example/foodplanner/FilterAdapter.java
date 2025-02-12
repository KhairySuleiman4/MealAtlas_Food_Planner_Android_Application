package com.example.foodplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterViewHolder> {

    private Context context;

    ArrayList<Category> categories;

    public FilterAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_filter, viewGroup, false);
        FilterViewHolder holder = new FilterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int i) {
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

class FilterViewHolder extends RecyclerView.ViewHolder{

    View convertedView;
    ImageView ivFilter;
    TextView tvFilter;
    public FilterViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        ivFilter = itemView.findViewById(R.id.iv_item_filter);
        tvFilter = itemView.findViewById(R.id.tv_item_filter_title);
    }
}
