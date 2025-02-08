package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesListFragment extends Fragment {

    ArrayList<Category> cates;
    RecyclerView rvCates;
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public CategoriesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCates = view.findViewById(R.id.rv_categories);
        cates = new ArrayList<>();

        Retrofit instance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CategoryService cs = instance.create(CategoryService.class);
        Call<CategoryResponse> call = cs.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    CategoryResponse result = response.body();
                    cates = result.getCates();
                    CategoriesAdapter adapter = new CategoriesAdapter(getContext(), cates);
                    rvCates.setAdapter(adapter);
                } else {
                    Log.i("TAG", "onResponse: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}