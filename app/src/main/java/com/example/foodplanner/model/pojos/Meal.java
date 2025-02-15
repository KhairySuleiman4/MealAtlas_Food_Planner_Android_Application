package com.example.foodplanner.model.pojos;

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

    @SerializedName("strIngredient1") private String mealIng1;
    @SerializedName("strIngredient2") private String mealIng2;
    @SerializedName("strIngredient3") private String mealIng3;
    @SerializedName("strIngredient4") private String mealIng4;
    @SerializedName("strIngredient5") private String mealIng5;
    @SerializedName("strIngredient6") private String mealIng6;
    @SerializedName("strIngredient7") private String mealIng7;
    @SerializedName("strIngredient8") private String mealIng8;
    @SerializedName("strIngredient9") private String mealIng9;
    @SerializedName("strIngredient10") private String mealIng10;
    @SerializedName("strIngredient11") private String mealIng11;
    @SerializedName("strIngredient12") private String mealIng12;
    @SerializedName("strIngredient13") private String mealIng13;
    @SerializedName("strIngredient14") private String mealIng14;
    @SerializedName("strIngredient15") private String mealIng15;
    @SerializedName("strIngredient16") private String mealIng16;
    @SerializedName("strIngredient17") private String mealIng17;
    @SerializedName("strIngredient18") private String mealIng18;
    @SerializedName("strIngredient19") private String mealIng19;
    @SerializedName("strIngredient20") private String mealIng20;

    @SerializedName("strMeasure1") private String mealMeas1;
    @SerializedName("strMeasure2") private String mealMeas2;
    @SerializedName("strMeasure3") private String mealMeas3;
    @SerializedName("strMeasure4") private String mealMeas4;
    @SerializedName("strMeasure5") private String mealMeas5;
    @SerializedName("strMeasure6") private String mealMeas6;
    @SerializedName("strMeasure7") private String mealMeas7;
    @SerializedName("strMeasure8") private String mealMeas8;
    @SerializedName("strMeasure9") private String mealMeas9;
    @SerializedName("strMeasure10") private String mealMeas10;
    @SerializedName("strMeasure11") private String mealMeas11;
    @SerializedName("strMeasure12") private String mealMeas12;
    @SerializedName("strMeasure13") private String mealMeas13;
    @SerializedName("strMeasure14") private String mealMeas14;
    @SerializedName("strMeasure15") private String mealMeas15;
    @SerializedName("strMeasure16") private String mealMeas16;
    @SerializedName("strMeasure17") private String mealMeas17;
    @SerializedName("strMeasure18") private String mealMeas18;
    @SerializedName("strMeasure19") private String mealMeas19;
    @SerializedName("strMeasure20") private String mealMeas20;

    public Meal(String mealName, String mealPhoto, String mealCate, String mealArea, String mealInst,
                String mealIng1, String mealIng2, String mealIng3, String mealIng4, String mealIng5,
                String mealIng6, String mealIng7, String mealIng8, String mealIng9, String mealIng10,
                String mealIng11, String mealIng12, String mealIng13, String mealIng14, String mealIng15,
                String mealIng16, String mealIng17, String mealIng18, String mealIng19, String mealIng20,
                String mealMeas1, String mealMeas2, String mealMeas3, String mealMeas4, String mealMeas5,
                String mealMeas6, String mealMeas7, String mealMeas8, String mealMeas9, String mealMeas10,
                String mealMeas11, String mealMeas12, String mealMeas13, String mealMeas14, String mealMeas15,
                String mealMeas16, String mealMeas17, String mealMeas18, String mealMeas19, String mealMeas20) {

        this.mealName = mealName;
        this.mealPhoto = mealPhoto;
        this.mealCate = mealCate;
        this.mealArea = mealArea;
        this.mealInst = mealInst;

        this.mealIng1 = mealIng1;
        this.mealIng2 = mealIng2;
        this.mealIng3 = mealIng3;
        this.mealIng4 = mealIng4;
        this.mealIng5 = mealIng5;
        this.mealIng6 = mealIng6;
        this.mealIng7 = mealIng7;
        this.mealIng8 = mealIng8;
        this.mealIng9 = mealIng9;
        this.mealIng10 = mealIng10;
        this.mealIng11 = mealIng11;
        this.mealIng12 = mealIng12;
        this.mealIng13 = mealIng13;
        this.mealIng14 = mealIng14;
        this.mealIng15 = mealIng15;
        this.mealIng16 = mealIng16;
        this.mealIng17 = mealIng17;
        this.mealIng18 = mealIng18;
        this.mealIng19 = mealIng19;
        this.mealIng20 = mealIng20;

        this.mealMeas1 = mealMeas1;
        this.mealMeas2 = mealMeas2;
        this.mealMeas3 = mealMeas3;
        this.mealMeas4 = mealMeas4;
        this.mealMeas5 = mealMeas5;
        this.mealMeas6 = mealMeas6;
        this.mealMeas7 = mealMeas7;
        this.mealMeas8 = mealMeas8;
        this.mealMeas9 = mealMeas9;
        this.mealMeas10 = mealMeas10;
        this.mealMeas11 = mealMeas11;
        this.mealMeas12 = mealMeas12;
        this.mealMeas13 = mealMeas13;
        this.mealMeas14 = mealMeas14;
        this.mealMeas15 = mealMeas15;
        this.mealMeas16 = mealMeas16;
        this.mealMeas17 = mealMeas17;
        this.mealMeas18 = mealMeas18;
        this.mealMeas19 = mealMeas19;
        this.mealMeas20 = mealMeas20;
    }

    protected Meal(Parcel in) {
        mealName = in.readString();
        mealPhoto = in.readString();
        mealCate = in.readString();
        mealArea = in.readString();
        mealInst = in.readString();

        mealIng1 = in.readString();
        mealIng2 = in.readString();
        mealIng3 = in.readString();
        mealIng4 = in.readString();
        mealIng5 = in.readString();
        mealIng6 = in.readString();
        mealIng7 = in.readString();
        mealIng8 = in.readString();
        mealIng9 = in.readString();
        mealIng10 = in.readString();
        mealIng11 = in.readString();
        mealIng12 = in.readString();
        mealIng13 = in.readString();
        mealIng14 = in.readString();
        mealIng15 = in.readString();
        mealIng16 = in.readString();
        mealIng17 = in.readString();
        mealIng18 = in.readString();
        mealIng19 = in.readString();
        mealIng20 = in.readString();

        mealMeas1 = in.readString();
        mealMeas2 = in.readString();
        mealMeas3 = in.readString();
        mealMeas4 = in.readString();
        mealMeas5 = in.readString();
        mealMeas6 = in.readString();
        mealMeas7 = in.readString();
        mealMeas8 = in.readString();
        mealMeas9 = in.readString();
        mealMeas10 = in.readString();
        mealMeas11 = in.readString();
        mealMeas12 = in.readString();
        mealMeas13 = in.readString();
        mealMeas14 = in.readString();
        mealMeas15 = in.readString();
        mealMeas16 = in.readString();
        mealMeas17 = in.readString();
        mealMeas18 = in.readString();
        mealMeas19 = in.readString();
        mealMeas20 = in.readString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mealName);
        dest.writeString(mealPhoto);
        dest.writeString(mealCate);
        dest.writeString(mealArea);
        dest.writeString(mealInst);

        dest.writeString(mealIng1);
        dest.writeString(mealIng2);
        dest.writeString(mealIng3);
        dest.writeString(mealIng4);
        dest.writeString(mealIng5);
        dest.writeString(mealIng6);
        dest.writeString(mealIng7);
        dest.writeString(mealIng8);
        dest.writeString(mealIng9);
        dest.writeString(mealIng10);
        dest.writeString(mealIng11);
        dest.writeString(mealIng12);
        dest.writeString(mealIng13);
        dest.writeString(mealIng14);
        dest.writeString(mealIng15);
        dest.writeString(mealIng16);
        dest.writeString(mealIng17);
        dest.writeString(mealIng18);
        dest.writeString(mealIng19);
        dest.writeString(mealIng20);

        dest.writeString(mealMeas1);
        dest.writeString(mealMeas2);
        dest.writeString(mealMeas3);
        dest.writeString(mealMeas4);
        dest.writeString(mealMeas5);
        dest.writeString(mealMeas6);
        dest.writeString(mealMeas7);
        dest.writeString(mealMeas8);
        dest.writeString(mealMeas9);
        dest.writeString(mealMeas10);
        dest.writeString(mealMeas11);
        dest.writeString(mealMeas12);
        dest.writeString(mealMeas13);
        dest.writeString(mealMeas14);
        dest.writeString(mealMeas15);
        dest.writeString(mealMeas16);
        dest.writeString(mealMeas17);
        dest.writeString(mealMeas18);
        dest.writeString(mealMeas19);
        dest.writeString(mealMeas20);
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

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealPhoto() {
        return mealPhoto;
    }

    public void setMealPhoto(String mealPhoto) {
        this.mealPhoto = mealPhoto;
    }

    public String getMealCate() {
        return mealCate;
    }

    public void setMealCate(String mealCate) {
        this.mealCate = mealCate;
    }

    public String getMealArea() {
        return mealArea;
    }

    public void setMealArea(String mealArea) {
        this.mealArea = mealArea;
    }

    public String getMealInst() {
        return mealInst;
    }

    public void setMealInst(String mealInst) {
        this.mealInst = mealInst;
    }

    public String getMealIng1() {
        return mealIng1;
    }

    public void setMealIng1(String mealIng1) {
        this.mealIng1 = mealIng1;
    }

    public String getMealIng2() {
        return mealIng2;
    }

    public void setMealIng2(String mealIng2) {
        this.mealIng2 = mealIng2;
    }

    public String getMealIng3() {
        return mealIng3;
    }

    public void setMealIng3(String mealIng3) {
        this.mealIng3 = mealIng3;
    }

    public String getMealIng4() {
        return mealIng4;
    }

    public void setMealIng4(String mealIng4) {
        this.mealIng4 = mealIng4;
    }

    public String getMealIng5() {
        return mealIng5;
    }

    public void setMealIng5(String mealIng5) {
        this.mealIng5 = mealIng5;
    }

    public String getMealIng6() {
        return mealIng6;
    }

    public void setMealIng6(String mealIng6) {
        this.mealIng6 = mealIng6;
    }

    public String getMealIng7() {
        return mealIng7;
    }

    public void setMealIng7(String mealIng7) {
        this.mealIng7 = mealIng7;
    }

    public String getMealIng8() {
        return mealIng8;
    }

    public void setMealIng8(String mealIng8) {
        this.mealIng8 = mealIng8;
    }

    public String getMealIng9() {
        return mealIng9;
    }

    public void setMealIng9(String mealIng9) {
        this.mealIng9 = mealIng9;
    }

    public String getMealIng10() {
        return mealIng10;
    }

    public void setMealIng10(String mealIng10) {
        this.mealIng10 = mealIng10;
    }

    public String getMealIng11() {
        return mealIng11;
    }

    public void setMealIng11(String mealIng11) {
        this.mealIng11 = mealIng11;
    }

    public String getMealIng12() {
        return mealIng12;
    }

    public void setMealIng12(String mealIng12) {
        this.mealIng12 = mealIng12;
    }

    public String getMealIng13() {
        return mealIng13;
    }

    public void setMealIng13(String mealIng13) {
        this.mealIng13 = mealIng13;
    }

    public String getMealIng14() {
        return mealIng14;
    }

    public void setMealIng14(String mealIng14) {
        this.mealIng14 = mealIng14;
    }

    public String getMealIng15() {
        return mealIng15;
    }

    public void setMealIng15(String mealIng15) {
        this.mealIng15 = mealIng15;
    }

    public String getMealIng16() {
        return mealIng16;
    }

    public void setMealIng16(String mealIng16) {
        this.mealIng16 = mealIng16;
    }

    public String getMealIng17() {
        return mealIng17;
    }

    public void setMealIng17(String mealIng17) {
        this.mealIng17 = mealIng17;
    }

    public String getMealIng18() {
        return mealIng18;
    }

    public void setMealIng18(String mealIng18) {
        this.mealIng18 = mealIng18;
    }

    public String getMealIng19() {
        return mealIng19;
    }

    public void setMealIng19(String mealIng19) {
        this.mealIng19 = mealIng19;
    }

    public String getMealIng20() {
        return mealIng20;
    }

    public void setMealIng20(String mealIng20) {
        this.mealIng20 = mealIng20;
    }

    public String getMealMeas1() {
        return mealMeas1;
    }

    public void setMealMeas1(String mealMeas1) {
        this.mealMeas1 = mealMeas1;
    }

    public String getMealMeas2() {
        return mealMeas2;
    }

    public void setMealMeas2(String mealMeas2) {
        this.mealMeas2 = mealMeas2;
    }

    public String getMealMeas3() {
        return mealMeas3;
    }

    public void setMealMeas3(String mealMeas3) {
        this.mealMeas3 = mealMeas3;
    }

    public String getMealMeas4() {
        return mealMeas4;
    }

    public void setMealMeas4(String mealMeas4) {
        this.mealMeas4 = mealMeas4;
    }

    public String getMealMeas5() {
        return mealMeas5;
    }

    public void setMealMeas5(String mealMeas5) {
        this.mealMeas5 = mealMeas5;
    }

    public String getMealMeas6() {
        return mealMeas6;
    }

    public void setMealMeas6(String mealMeas6) {
        this.mealMeas6 = mealMeas6;
    }

    public String getMealMeas7() {
        return mealMeas7;
    }

    public void setMealMeas7(String mealMeas7) {
        this.mealMeas7 = mealMeas7;
    }

    public String getMealMeas8() {
        return mealMeas8;
    }

    public void setMealMeas8(String mealMeas8) {
        this.mealMeas8 = mealMeas8;
    }

    public String getMealMeas9() {
        return mealMeas9;
    }

    public void setMealMeas9(String mealMeas9) {
        this.mealMeas9 = mealMeas9;
    }

    public String getMealMeas10() {
        return mealMeas10;
    }

    public void setMealMeas10(String mealMeas10) {
        this.mealMeas10 = mealMeas10;
    }

    public String getMealMeas11() {
        return mealMeas11;
    }

    public void setMealMeas11(String mealMeas11) {
        this.mealMeas11 = mealMeas11;
    }

    public String getMealMeas12() {
        return mealMeas12;
    }

    public void setMealMeas12(String mealMeas12) {
        this.mealMeas12 = mealMeas12;
    }

    public String getMealMeas13() {
        return mealMeas13;
    }

    public void setMealMeas13(String mealMeas13) {
        this.mealMeas13 = mealMeas13;
    }

    public String getMealMeas14() {
        return mealMeas14;
    }

    public void setMealMeas14(String mealMeas14) {
        this.mealMeas14 = mealMeas14;
    }

    public String getMealMeas15() {
        return mealMeas15;
    }

    public void setMealMeas15(String mealMeas15) {
        this.mealMeas15 = mealMeas15;
    }

    public String getMealMeas16() {
        return mealMeas16;
    }

    public void setMealMeas16(String mealMeas16) {
        this.mealMeas16 = mealMeas16;
    }

    public String getMealMeas17() {
        return mealMeas17;
    }

    public void setMealMeas17(String mealMeas17) {
        this.mealMeas17 = mealMeas17;
    }

    public String getMealMeas18() {
        return mealMeas18;
    }

    public void setMealMeas18(String mealMeas18) {
        this.mealMeas18 = mealMeas18;
    }

    public String getMealMeas19() {
        return mealMeas19;
    }

    public void setMealMeas19(String mealMeas19) {
        this.mealMeas19 = mealMeas19;
    }

    public String getMealMeas20() {
        return mealMeas20;
    }

    public void setMealMeas20(String mealMeas20) {
        this.mealMeas20 = mealMeas20;
    }
}
