package com.example.foodplanner.model.network.country;

import com.example.foodplanner.model.pojos.Country;

import java.util.List;

public class CountriesRepositoryImp {
    public static List<Country> getCountries(){
        return CountriesRemoteDataSourceImp.getCountries();
    }
}
