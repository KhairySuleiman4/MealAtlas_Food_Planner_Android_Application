package com.example.foodplanner.model.network.country;

import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesRemoteDataSourceImp {
    private static List<Country> countries;

    public static List<Country> getCountries() {
        countries = new ArrayList<>();
        countries.add(new Country("American", R.drawable.american));
        countries.add(new Country("British", R.drawable.british));
        countries.add(new Country("Canadian", R.drawable.canadian));
        countries.add(new Country("Chinese", R.drawable.chinese));
        countries.add(new Country("Croatian", R.drawable.croatian));
        countries.add(new Country("Dutch", R.drawable.dutch));
        countries.add(new Country("Egyptian", R.drawable.egyptian));
        countries.add(new Country("Filipino", R.drawable.filipino));
        countries.add(new Country("French", R.drawable.french));
        countries.add(new Country("Greek", R.drawable.greek));
        countries.add(new Country("Indian", R.drawable.indian));
        countries.add(new Country("Irish", R.drawable.irish));
        countries.add(new Country("Italian", R.drawable.italian));
        countries.add(new Country("Jamaican", R.drawable.jamaican));
        countries.add(new Country("Japanese", R.drawable.japanese));
        countries.add(new Country("Kenyan", R.drawable.kenyan));
        countries.add(new Country("Malaysian", R.drawable.malaysian));
        countries.add(new Country("mexican", R.drawable.mexican));
        countries.add(new Country("Moroccan", R.drawable.moroccan));
        countries.add(new Country("Polish", R.drawable.polish));
        countries.add(new Country("Portuguese", R.drawable.portuguese));
        countries.add(new Country("Russian", R.drawable.russian));
        countries.add(new Country("Spanish", R.drawable.spanish));
        countries.add(new Country("Thai", R.drawable.thai));
        countries.add(new Country("Tunisian", R.drawable.tunisian));
        countries.add(new Country("Turkish", R.drawable.turkish));
        countries.add(new Country("Ukrainian", R.drawable.ukrainian));
        countries.add(new Country("Uruguayan", R.drawable.uruguayan));
        countries.add(new Country("Vietnamese", R.drawable.vietnamese));
        return countries;
    }
}
