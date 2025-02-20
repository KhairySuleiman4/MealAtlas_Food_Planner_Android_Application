package com.example.foodplanner.screens.splashscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

public class SplashScreenFragment extends Fragment {

    ImageView ivSplash;
    LottieAnimationView lottieAnimation;

    public SplashScreenFragment() {
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
        return inflater.inflate(R.layout.splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivSplash = view.findViewById(R.id.iv_logo);
        lottieAnimation = view.findViewById(R.id.lottie_animation);
        lottieAnimation.playAnimation();

        new Handler().postDelayed(() -> {
            Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_welcomeFragment);
        }, 2200);
    }
}