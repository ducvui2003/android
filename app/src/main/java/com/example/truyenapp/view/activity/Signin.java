package com.example.truyenapp.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.R;
import com.example.truyenapp.api.AuthAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.request.AuthenticationRequest;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signin extends AppCompatActivity implements View.OnClickListener {
    private ImageView passwordConfirmField;
    private EditText usernameField, passwordField;
    TextView signupBtn, forgotPassBtn;
    private Button signinBtn;
    private AuthAPI authAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        init();
        authAPI = RetrofitClient.getInstance(this).create(AuthAPI.class);
    }

    private void init() {
        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);
        signinBtn = findViewById(R.id.signinBtn);
        passwordConfirmField = findViewById(R.id.logo_image);
        signupBtn = findViewById(R.id.sign_up_btn);
        forgotPassBtn = findViewById(R.id.forgotPassBtn);
        signinBtn.setOnClickListener(this);
        passwordConfirmField.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        forgotPassBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logo_image:
                // Redirect to HomeActivity when logo is clicked
                Intent dialog_box = new Intent(this, HomeActivity.class);
                startActivity(dialog_box);
                finish();
                break;

            case R.id.sign_up_btn:
                // Redirect to Signup activity when 'signupBtn' is clicked
                Intent dialog_box1 = new Intent(this, SignUpActivity.class);
                startActivity(dialog_box1);
                break;

            case R.id.forgotPassBtn:
                // Redirect to ForgotPassword activity when 'forgotPassBtn' is clicked
                Intent dialog_box2 = new Intent(this, ForgotPassword.class);
                startActivity(dialog_box2);
                break;

            case R.id.signinBtn:
                // Handle sign in button click
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                // Check if username contains space
                if (username.contains(" ")) {
                    Toast.makeText(this, "Tên đăng nhập không chứa khoảng trắng", Toast.LENGTH_SHORT).show();
                }
                // Check if username and password are not empty
                if (!username.isEmpty() && !password.isEmpty()) {
                    loginHandle(username, password);
                } else {
                    // Show appropriate message if either username or password is empty
                    if (username.isEmpty()) {
                        Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    private void loginHandle(String username, String password) {
        final Context context = this; // Save the reference of the Signin class to the context variable

        // Perform login authentication with provided username and password
        authAPI.login(new AuthenticationRequest(username, password)).enqueue(new Callback<JWTToken>() {
            @Override
            public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {
                JWTToken jwt = response.body();
                if (jwt != null) {
                    // Save JWT token and redirect to HomeActivity on successful login
                    SharedPreferencesHelper.savePreference(context, jwt, SystemConstant.JWT_TOKEN);
                    Toast.makeText(Signin.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

//
                    Intent intent = new Intent(Signin.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Show error message if login response is null
                    Toast.makeText(Signin.this, "Lỗi, Tên đăng nhập hoặc tài khoản không đúng!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JWTToken> call, Throwable throwable) {
                // Log error message and show error toast on login failure
                Log.e("TAG", "Login failed: " + throwable.getMessage());
                Toast.makeText(Signin.this, "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }


}