package com.example.foodplanner.screens.mealdetailsscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.foodplanner.MealDetailsFragmentArgs;
import com.example.foodplanner.R;
import com.example.foodplanner.model.pojos.Meal;

import java.util.ArrayList;

public class MealDetailsFragment extends Fragment {

    ImageView ivMealPhoto;
    TextView tvMealTitle;
    TextView tvMealCate;
    TextView tvMealArea;
    TextView tvInst;
    ArrayList<String> ingredients;
    ArrayList<String> measures;
    RecyclerView rvMealIngredients;

    public MealDetailsFragment() {
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
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingredients = new ArrayList<>();
        measures = new ArrayList<>();
        ivMealPhoto = view.findViewById(R.id.iv_meal_photo);
        tvMealTitle = view.findViewById(R.id.tv_meal_title);
        tvMealCate = view.findViewById(R.id.tv_meal_category);
        tvMealArea = view.findViewById(R.id.tv_meal_area);
        tvInst = view.findViewById(R.id.tv_meal_inst);
        rvMealIngredients = view.findViewById(R.id.rv_meal_ingredients);

//        Meal meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealDetails();
//        storeIngredients(meal);
//        MealIngredientsAdapter adapter = new MealIngredientsAdapter(getContext(), ingredients, measures);
//        Glide.with(MealDetailsFragment.this)
//                .load(meal.getMealPhoto())
//                .into(ivMealPhoto);
//        tvMealTitle.setText(meal.getMealName());
//        tvMealCate.setText(meal.getMealCate());
//        tvMealArea.setText(meal.getMealArea());
//        tvInst.setText(meal.getMealInst());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMealIngredients.setLayoutManager(layoutManager);
        //rvMealIngredients.setAdapter(adapter);


    }

    private void storeIngredients(Meal meal) {
        for(int i = 1; i < 21; i++){
            switch (i){
                case 1:
                    if(meal.getMealIng1().isBlank() || meal.getMealMeas1().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng1());
                    measures.add(meal.getMealMeas1());
                    break;
                case 2:
                    if(meal.getMealIng2().isBlank() || meal.getMealMeas2().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng2());
                    measures.add(meal.getMealMeas2());
                    break;
                case 3:
                    if(meal.getMealIng3().isBlank() || meal.getMealMeas3().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng3());
                    measures.add(meal.getMealMeas3());
                    break;
                case 4:
                    if(meal.getMealIng4().isBlank() || meal.getMealMeas4().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng4());
                    measures.add(meal.getMealMeas4());
                    break;
                case 5:
                    if(meal.getMealIng5().isBlank() || meal.getMealMeas5().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng5());
                    measures.add(meal.getMealMeas5());
                    break;
                case 6:
                    if(meal.getMealIng6().isBlank() || meal.getMealMeas6().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng6());
                    measures.add(meal.getMealMeas6());
                    break;
                case 7:
                    if(meal.getMealIng7().isBlank() || meal.getMealMeas7().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng7());
                    measures.add(meal.getMealMeas7());
                    break;
                case 8:
                    if(meal.getMealIng8().isBlank() || meal.getMealMeas8().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng8());
                    measures.add(meal.getMealMeas8());
                    break;
                case 9:
                    if(meal.getMealIng9().isBlank() || meal.getMealMeas9().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng9());
                    measures.add(meal.getMealMeas9());
                    break;
                case 10:
                    if(meal.getMealIng10().isBlank() || meal.getMealMeas10().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng10());
                    measures.add(meal.getMealMeas10());
                    break;
                case 11:
                    if(meal.getMealIng11().isBlank() || meal.getMealMeas11().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng11());
                    measures.add(meal.getMealMeas11());
                    break;
                case 12:
                    if(meal.getMealIng12().isBlank() || meal.getMealMeas12().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng12());
                    measures.add(meal.getMealMeas12());
                    break;
                case 13:
                    if(meal.getMealIng13().isBlank() || meal.getMealMeas13().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng13());
                    measures.add(meal.getMealMeas13());
                    break;
                case 14:
                    if(meal.getMealIng14().isBlank() || meal.getMealMeas14().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng14());
                    measures.add(meal.getMealMeas14());
                    break;
                case 15:
                    if(meal.getMealIng15().isBlank() || meal.getMealMeas15().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng15());
                    measures.add(meal.getMealMeas15());
                    break;
                case 16:
                    if(meal.getMealIng16().isBlank() || meal.getMealMeas16().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng16());
                    measures.add(meal.getMealMeas16());
                    break;
                case 17:
                    if(meal.getMealIng17().isBlank() || meal.getMealMeas17().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng17());
                    measures.add(meal.getMealMeas17());
                    break;
                case 18:
                    if(meal.getMealIng18().isBlank() || meal.getMealMeas18().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng18());
                    measures.add(meal.getMealMeas18());
                    break;
                case 19:
                    if(meal.getMealIng19().isBlank() || meal.getMealMeas19().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng19());
                    measures.add(meal.getMealMeas19());
                    break;
                case 20:
                    if(meal.getMealIng20().isBlank() || meal.getMealMeas20().isBlank()){
                        return;
                    }
                    ingredients.add(meal.getMealIng20());
                    measures.add(meal.getMealMeas20());
                    break;
            }
        }
    }
}