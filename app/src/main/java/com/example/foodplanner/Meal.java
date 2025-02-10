package com.example.foodplanner;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Meal implements Parcelable {
    @SerializedName("strMeal")
    private String mealName;
    @SerializedName("strMealThumb")
    private String mealPhoto;
    @SerializedName("strCategory")
    private String mealCate;
    @SerializedName("strArea")
    private String mealArea;
    @SerializedName("strInstructions")
    private String mealInst;

    public Meal(String mealName, String mealPhoto, String mealCate, String mealArea, String mealInst) {
        this.mealName = mealName;
        this.mealPhoto = mealPhoto;
        this.mealCate = mealCate;
        this.mealArea = mealArea;
        this.mealInst = mealInst;
    }

    protected Meal(Parcel in) {
        mealName = in.readString();
        mealPhoto = in.readString();
        mealCate = in.readString();
        mealArea = in.readString();
        mealInst = in.readString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mealName);
        dest.writeString(mealPhoto);
        dest.writeString(mealCate);
        dest.writeString(mealArea);
        dest.writeString(mealInst);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getMealName() { return mealName; }
    public void setMealName(String mealName) { this.mealName = mealName; }

    public String getMealPhoto() { return mealPhoto; }
    public void setMealPhoto(String mealPhoto) { this.mealPhoto = mealPhoto; }

    public String getMealCate() { return mealCate; }
    public void setMealCate(String mealCate) { this.mealCate = mealCate; }

    public String getMealArea() { return mealArea; }
    public void setMealArea(String mealArea) { this.mealArea = mealArea; }

    public String getMealInst() { return mealInst; }
    public void setMealInst(String mealInst) { this.mealInst = mealInst; }
}
