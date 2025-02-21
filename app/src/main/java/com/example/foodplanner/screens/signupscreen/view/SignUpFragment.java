package com.example.foodplanner.screens.signupscreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.screens.signupscreen.presenter.SignUpPresenterImp;

public class SignUpFragment extends Fragment implements SignUpView{
    Button btnSignUp;
    EditText etSignUpEmail;
    EditText etSignUpPassword;
    SignUpPresenterImp presenter;

    public SignUpFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        etSignUpEmail = view.findViewById(R.id.et_sign_up_email);
        etSignUpPassword = view.findViewById(R.id.et_sign_password);

        presenter = new SignUpPresenterImp(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(etSignUpEmail.getText());
                String password = String.valueOf(etSignUpPassword.getText());
                presenter.giveCredentials(email, password);
            }
        });
    }

    @Override
    public void onSuccess(String email, String password) {
        Toast.makeText(getContext(), "Welcome " + email + "!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_signUpFragment_to_homeScreenFragment);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}