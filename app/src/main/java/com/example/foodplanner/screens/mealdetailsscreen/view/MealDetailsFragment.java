package com.example.foodplanner.screens.mealdetailsscreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.foodplanner.MealDetailsFragmentArgs;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.db.MealsLocalDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.screens.mealdetailsscreen.presenter.MealDetailsPresenterImp;
import com.google.firebase.auth.FirebaseAuth;
//import com.example.foodplanner.screens.mealdetailsscreen.MealDetailsFragmentArgs;

import java.util.ArrayList;

public class MealDetailsFragment extends Fragment implements MealDetailsView{
    MealDetailsPresenterImp presenter;
    ImageView ivMealPhoto;
    Button btnAddToFavorite;
    TextView tvMealTitle;
    TextView tvMealCate;
    TextView tvMealArea;
    TextView tvInst;
    ArrayList<String> ingredients;
    ArrayList<String> measures;
    RecyclerView rvMealIngredients;
    WebView wvYoutube;
    Boolean isGuest;
    FirebaseAuth auth;

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
        presenter = new MealDetailsPresenterImp(this,
                MealsRepositoryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(),
                        MealsLocalDataSourceImp.getInstance(getContext())));

        ivMealPhoto = view.findViewById(R.id.iv_meal_photo);
        btnAddToFavorite = view.findViewById(R.id.btn_add_to_favorite);
        tvMealTitle = view.findViewById(R.id.tv_meal_title);
        tvMealCate = view.findViewById(R.id.tv_meal_category);
        tvMealArea = view.findViewById(R.id.tv_meal_area);
        tvInst = view.findViewById(R.id.tv_meal_inst);
        rvMealIngredients = view.findViewById(R.id.rv_meal_ingredients);
        wvYoutube = view.findViewById(R.id.wv_youtube);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null)
            isGuest = true;
        else isGuest = false;

        Meal meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealDetails();

        btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGuest)
                    Toast.makeText(getContext(), "Login First!", Toast.LENGTH_SHORT).show();
                else
                    presenter.insertMeal(meal);
            }
        });

        storeIngredients(meal);
        MealIngredientsAdapter adapter = new MealIngredientsAdapter(getContext(), ingredients, measures);
        Glide.with(MealDetailsFragment.this)
                .load(meal.getMealPhoto())
                .into(ivMealPhoto);
        tvMealTitle.setText(meal.getMealName());
        tvMealCate.setText(meal.getMealCate());
        tvMealArea.setText(meal.getMealArea());
        tvInst.setText(meal.getMealInst());
        rvMealIngredients.setAdapter(adapter);

        String videoId = "";
        String youtubeUrl = meal.getStrYoutube();

        if (youtubeUrl != null && youtubeUrl.contains("v=")) {
            String[] splitUrl = youtubeUrl.split("v=");
            if (splitUrl.length > 1) {
                videoId = splitUrl[1];
            }
        }
        String embedUrl = "https://www.youtube.com/embed/" + videoId;
        wvYoutube.getSettings().setJavaScriptEnabled(true);
        wvYoutube.getSettings().setDomStorageEnabled(true);
        wvYoutube.getSettings().setMediaPlaybackRequiresUserGesture(false);
        wvYoutube.setWebViewClient(new WebViewClient());
        String iframeHtml = "<html><body><iframe width=\"100%\" height=\"100%\" " +
                "src=\"" + embedUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        wvYoutube.loadData(iframeHtml, "text/html", "utf-8");

    }

    private void storeIngredients(Meal meal) {
        for (int i = 1; i <= 20; i++) {
            String ingredient = getIngredientByIndex(meal, i);
            String measure = getMeasureByIndex(meal, i);

            if (ingredient == null || measure == null || ingredient.isBlank() || measure.isBlank()) {
                continue;
            }

            ingredients.add(ingredient);
            measures.add(measure);
        }
    }

    private String getIngredientByIndex(Meal meal, int index) {
        switch (index) {
            case 1: return meal.getMealIng1();
            case 2: return meal.getMealIng2();
            case 3: return meal.getMealIng3();
            case 4: return meal.getMealIng4();
            case 5: return meal.getMealIng5();
            case 6: return meal.getMealIng6();
            case 7: return meal.getMealIng7();
            case 8: return meal.getMealIng8();
            case 9: return meal.getMealIng9();
            case 10: return meal.getMealIng10();
            case 11: return meal.getMealIng11();
            case 12: return meal.getMealIng12();
            case 13: return meal.getMealIng13();
            case 14: return meal.getMealIng14();
            case 15: return meal.getMealIng15();
            case 16: return meal.getMealIng16();
            case 17: return meal.getMealIng17();
            case 18: return meal.getMealIng18();
            case 19: return meal.getMealIng19();
            case 20: return meal.getMealIng20();
            default: return null;
        }
    }

    private String getMeasureByIndex(Meal meal, int index) {
        switch (index) {
            case 1: return meal.getMealMeas1();
            case 2: return meal.getMealMeas2();
            case 3: return meal.getMealMeas3();
            case 4: return meal.getMealMeas4();
            case 5: return meal.getMealMeas5();
            case 6: return meal.getMealMeas6();
            case 7: return meal.getMealMeas7();
            case 8: return meal.getMealMeas8();
            case 9: return meal.getMealMeas9();
            case 10: return meal.getMealMeas10();
            case 11: return meal.getMealMeas11();
            case 12: return meal.getMealMeas12();
            case 13: return meal.getMealMeas13();
            case 14: return meal.getMealMeas14();
            case 15: return meal.getMealMeas15();
            case 16: return meal.getMealMeas16();
            case 17: return meal.getMealMeas17();
            case 18: return meal.getMealMeas18();
            case 19: return meal.getMealMeas19();
            case 20: return meal.getMealMeas20();
            default: return null;
        }
    }

    @Override
    public void showAdded(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}