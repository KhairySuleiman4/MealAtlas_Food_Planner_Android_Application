package com.example.foodplanner.screens.loginscreen.view;

import android.os.Bundle;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.db.MealsLocalDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.screens.loginscreen.presenter.LoginPresenterImp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginFragment extends Fragment implements LoginView{
    LoginPresenterImp presenter;
    EditText etLoginEmail;
    EditText etLoginPassword;
    Button btnLogin;
    TextView tvSignUp;
    ImageView ivGoogle;
    GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin = view.findViewById(R.id.btn_login);
        tvSignUp = view.findViewById(R.id.tv_to_sign_up_from_login);
        etLoginEmail = view.findViewById(R.id.et_login_email);
        etLoginPassword = view.findViewById(R.id.et_login_password);
        ivGoogle = view.findViewById(R.id.iv_google);

        presenter = new LoginPresenterImp(this, MealsRepositoryImp.getInstance(
                MealsRemoteDataSourceImp.getInstance(),
                MealsLocalDataSourceImp.getInstance(getContext())));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        tvSignUp.setClickable(true);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(etLoginEmail.getText());
                String password = String.valueOf(etLoginPassword.getText());
                presenter.giveCredentials(email, password);
            }
        });

        ivGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.handleGoogleSignIn(account.getIdToken());
            } catch (ApiException e) {
                onFailure("Google Sign-In failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void onSuccess(String email, String password) {
        Toast.makeText(getContext(), "Welcome " + email + "!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeScreenFragment);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}