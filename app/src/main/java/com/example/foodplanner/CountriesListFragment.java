package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CountriesListFragment extends Fragment {

    RecyclerView rvCountries;
    ArrayList<Country> countries;

    public CountriesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCountries = view.findViewById(R.id.rv_countries);
        countries = new ArrayList<>();
        countries.add(new Country("USA", R.drawable.usa));
        countries.add(new Country("UK", R.drawable.uk));
        countries.add(new Country("Canada", R.drawable.canada));
        countries.add(new Country("China", R.drawable.china));
        countries.add(new Country("Croatia", R.drawable.croatia));
        countries.add(new Country("Netherlands", R.drawable.netherlands));
        countries.add(new Country("Egypt", R.drawable.egypt));
        countries.add(new Country("Philippines", R.drawable.philippines));
        countries.add(new Country("France", R.drawable.france));
        countries.add(new Country("Greece", R.drawable.greece));

        CountriesAdapter adapter = new CountriesAdapter(getContext(), countries);
        rvCountries.setAdapter(adapter);
    }
}