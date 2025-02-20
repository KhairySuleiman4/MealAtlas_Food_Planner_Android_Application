package com.example.foodplanner.screens.homescreen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesViewHolder> {

    private Context context;
    private List<Country> countries;
    private OnItemClickListener listener;

    public CountriesAdapter(Context context, List<Country> countries, OnItemClickListener listener) {
        this.context = context;
        this.countries = countries;
        this.listener = listener;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item, viewGroup, false);
        CountriesViewHolder holder = new CountriesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int i) {
        String countryName = countries.get(i).getName();
        holder.ivFlag.setImageResource(countries.get(i).getFlag());
        holder.tvName.setText(countryName);
        holder.convertedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCountryClick(countryName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}

class CountriesViewHolder extends RecyclerView.ViewHolder{
    View convertedView;
    ImageView ivFlag;
    TextView tvName;
    public CountriesViewHolder(@NonNull View itemView) {
        super(itemView);
        convertedView = itemView;
        ivFlag = itemView.findViewById(R.id.iv_card_item);
        tvName = itemView.findViewById(R.id.tv_card_item);
    }
}
