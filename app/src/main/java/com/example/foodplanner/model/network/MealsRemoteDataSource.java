package com.example.foodplanner.model.network;

import android.net.ConnectivityManager;

public interface MealsRemoteDataSource {
    void mealNetworkCall(NetworkCallBack networkCallBack);
}
