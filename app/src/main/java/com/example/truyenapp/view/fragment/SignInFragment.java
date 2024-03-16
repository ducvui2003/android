package com.example.truyenapp.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.view.activity.HomeActivity;

public class SignInFragment extends Fragment {
    TextView tvChangeFragmentPass, tvChangeFragmentSignUp;
    EditText emailField, passwordField;
    TextView warningEmail, warningPass;
    Button btnSignIn;
    Database db;
    View view;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        init();

        db = new Database(this.getContext());
        setOnClickListener();
        return view;
    }

    private void init() {
        emailField = view.findViewById(R.id.edit_sign_in_email);
        passwordField = view.findViewById(R.id.edit_sign_in_pass);
        warningEmail = view.findViewById(R.id.warning_sign_in_email);
        warningPass = view.findViewById(R.id.warning_sign_in_password);
        tvChangeFragmentPass = view.findViewById(R.id.text_view_change_fragment_forget_password);
        tvChangeFragmentSignUp = view.findViewById(R.id.text_view_change_fragment_sign_up);
        btnSignIn = view.findViewById(R.id.button_sign_in);
    }

    private void setOnClickListener() {
        FragmentManager fragmentManager = getParentFragmentManager();
        tvChangeFragmentSignUp.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_sign, SignUpFragment.class, null);
            fragmentTransaction.addToBackStack(this.getClass().getSimpleName());
            fragmentTransaction.commit();
        });
        tvChangeFragmentPass.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_sign, ForgetPasswordFragment.class, null);
            fragmentTransaction.addToBackStack(this.getClass().getSimpleName());
            fragmentTransaction.commit();
        });
        btnSignIn.setOnClickListener(view -> {
            handleSignIn();
        });
    }

    @SuppressLint("SetTextI18n")
    private void handleSignIn() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        if (email.length() != 0 && password.length() != 0) {
            Boolean emailExits = db.checkEmailMatkhau(email, password);
            if (emailExits) {
                int kt = db.checkTrangThai(email);
                if (kt != 0) {
                    Toast.makeText(this.getActivity(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent dialog_box = new Intent(this.getActivity(), HomeActivity.class);
                    dialog_box.putExtra("email", email);
                    startActivity(dialog_box);
                } else {
                    Toast.makeText(this.getActivity(), "Tài khoản bị khóa", Toast.LENGTH_SHORT).show();
                }
            } else {
                warningEmail.setText("Email sai!");
                warningEmail.setVisibility(View.VISIBLE);
                Toast.makeText(this.getActivity(), "Email hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (email.length() == 0) {
                Toast.makeText(this.getActivity(), "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getActivity(), "Vui lòng nhập Mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }
    }

}