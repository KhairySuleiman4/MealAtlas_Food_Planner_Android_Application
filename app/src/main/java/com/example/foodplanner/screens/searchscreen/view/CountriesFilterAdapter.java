package com.example.foodplanner.screens.searchscreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Country;

import java.util.List;

public class CountriesFilterAdapter extends RecyclerView.Adapter<CountriesFilterViewHolder> {
    private Context context;
    private List<Country> countries;

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public CountriesFilterAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountriesFilterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_filter, viewGroup, false);
        CountriesFilterViewHolder holder = new CountriesFilterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesFilterViewHolder holder, int i) {
        holder.ivFilter.setImageResource(countries.get(i).getFlag());
        holder.tvFilter.setText(countries.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}

class CountriesFilterViewHolder extends RecyclerView.ViewHolder{
    View convertedView;
    ImageView ivFilter;
    TextView tvFilter;
    public CountriesFilterViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        ivFilter = itemView.findViewById(R.id.iv_item_filter);
        tvFilter = itemView.findViewById(R.id.tv_item_filter_title);
    }
}