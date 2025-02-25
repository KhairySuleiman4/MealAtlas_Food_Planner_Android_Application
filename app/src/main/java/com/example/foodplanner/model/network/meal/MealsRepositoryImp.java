package com.example.foodplanner.model.network.meal;

import android.util.Log;

import com.example.foodplanner.model.db.MealsLocalDataSource;
import com.example.foodplanner.model.network.firebase.AuthCallback;
import com.example.foodplanner.model.network.firebase.Firebase;
import com.example.foodplanner.model.network.firebase.MealsRestoreCallback;
import com.example.foodplanner.model.pojos.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsRepositoryImp implements MealsRepository{
    MealsRemoteDataSource remote;
    MealsLocalDataSource local;
    CompositeDisposable disposable;
    Firebase firebase;
    private static MealsRepositoryImp repo = null;
    private MealsRepositoryImp(MealsRemoteDataSource remote, MealsLocalDataSource local){
        this.remote = remote;
        this.local = local;
        disposable = new CompositeDisposable();
        firebase = Firebase.getInstance();
    }

    public static MealsRepositoryImp getInstance(MealsRemoteDataSource remote, MealsLocalDataSource local){
        if(repo == null){
            repo = new MealsRepositoryImp(remote, local);
        }
        return repo;
    }
    public void createAccount(String email, String password, AuthCallback callback) {
        firebase.createAccount(email, password, callback);
    }
    public void signIn(String email, String password, AuthCallback callback){
        firebase.signIn(email, password, callback);
    }
    public void handleGoogleSignIn(String idToken, AuthCallback callback) {
        firebase.handleGoogleSignIn(idToken, callback);
    }
    @Override
    public Single<Meal> mealNetworkCall() {
        return remote.mealNetworkCall();
    }
    @Override
    public Single<List<Meal>> ingredientsNetworkCall() {
        return remote.ingredientsNetworkCall();
    }
    @Override
    public Single<List<Meal>> mealsByCategory(String category) {
        return remote.mealsByCategory(category);
    }
    @Override
    public Single<List<Meal>> mealsByCountry(String country) {
        return remote.mealsByCountry(country);
    }
    @Override
    public Single<List<Meal>> mealsByIngredient(String ingredient) {
        return remote.mealsByIngredient(ingredient);
    }
    @Override
    public Single<Meal> getMealById(long mealId) {
        return remote.getMealById(mealId);
    }
    @Override
    public Observable<List<Meal>> getFavMeals() {
        return local.getFavMeals();
    }
    @Override
    public Completable insertMeal(Meal meal) {
        return local.insertFavMeal(meal);
    }
    @Override
    public Completable deleteMeal(Meal meal) {
        return local.deleteFavMeal(meal);
    }
    @Override
    public Single<Boolean> doesMealExist(long mealId) {
        return local.isMealFavorite(mealId);
    }
    @Override
    public Observable<List<Meal>> getPlannedMeals(String dayDate) {
        return local.getPlannedMeals(dayDate);
    }
    @Override
    public Completable insertPlannedMeal(Meal meal) {
        return local.insertPlannedMeal(meal);
    }
    @Override
    public Completable deletePlannedMeal(Meal meal) {
        return local.deletePlannedMeal(meal);
    }
    @Override
    public void addMealToFireStore() {
        disposable.add(
                local.getLastMeal()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meal -> {
                                    firebase.addMealToFireStore(meal);
                                },
                                error -> {
                                    Log.i("TAG", "addMealToFireStore: " + error.getMessage());
                                }
                        )
        );
    }
    @Override
    public void deleteMealFromFireStore(String mealId) {
        firebase.deleteMealFromFireStore(mealId);
    }
    @Override
    public boolean isGuest() {
        return firebase.isGuest();
    }
    @Override
    public Completable logOut() {
        firebase.logOut();
        return local.clearAllMeals();
    }
    @Override
    public void restoreMealsFromFireStore() {
        firebase.restoreMealsFromFireStore(new MealsRestoreCallback() {
            @Override
            public void onMealsRestored(List<Meal> meals) {
                for (Meal meal : meals) {
                    disposable.add(
                            local.insertFavMeal(meal)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            () -> {
                                                Log.i("TAG", "Meal inserted: " + meal.getMealName());
                                            }, error -> {
                                                Log.e("TAG", "Error inserting meal: " + error.getMessage());
                                            }
                                    )
                    );
                }
            }
            @Override
            public void onFailure(String errorMessage) {
                Log.e("RestoreMeals", "Failed to restore meals: " + errorMessage);
            }
        });
    }


}
