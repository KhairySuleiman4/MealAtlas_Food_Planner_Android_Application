package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    ChipGroup chipGroup;
    Chip cateChip;
    RecyclerView rvFilter;
    ArrayList<Category> cates;
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public SearchFragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cates = new ArrayList<>();
        chipGroup = view.findViewById(R.id.chip_group_filters);
        rvFilter = view.findViewById(R.id.rv_filter);
        cateChip = view.findViewById(R.id.chip_categories);
        chipGroup.setSingleSelection(true);
        Retrofit instance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cateChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryService cs = instance.create(CategoryService.class);
                Call<CategoryResponse> callCategory = cs.getCategories();
                callCategory.enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                        if(response.isSuccessful()){
                            CategoryResponse result = response.body();
                            cates = result.getCates();
                            FilterAdapter adapter = new FilterAdapter(getContext(), cates);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            rvFilter.setLayoutManager(layoutManager);
                            rvFilter.setAdapter(adapter);
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
        });

        }
    }
