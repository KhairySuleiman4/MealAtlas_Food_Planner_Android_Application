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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private Context context;
    private ArrayList<Category> cates;

    public CategoriesAdapter(Context context, ArrayList<Category> cates) {
        this.context = context;
        this.cates = cates;
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
                .load(cates.get(i).getCateImage())
                .into(holder.ivCateImage);
        holder.tvCateName.setText(cates.get(i).getCateName());
    }

    @Override
    public int getItemCount() {
        return cates.size();
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
