package com.example.foodplanner.model.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.pojos.Meal;

@Database(entities = {Meal.class}, version = 3)
public abstract class DataBase extends RoomDatabase {
    private static DataBase instance = null;
    public abstract MealsDAO getMealsDAO();

    public static synchronized DataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, DataBase.class, "mealsdb")
                    .build();
            Log.i("TAG", "getInstance: first time in database getInstance()");
        }
        return instance;
    }
}
