package com.example.foodplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.model.pojos.Country;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesViewHolder> {

    private Context context;
    private ArrayList<Country> countries;

    public CountriesAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
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
        holder.ivFlag.setImageResource(countries.get(i).getFlag());
        holder.tvName.setText(countries.get(i).getName());
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
