package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.request.UserRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    EditText emailField, passwordField, passwordConfirmField, usernameField;
    Button btnSignUp;
    ImageView logoImg;
    //    RadioButton rb_check;
    private UserAPI userAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        init();
    }

    private void init() {
        emailField = findViewById(R.id.emailField);
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        passwordConfirmField = findViewById(R.id.passwordConfirmField);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signupBtn:
                Intent dialog_box = new Intent(this, SignUpActivity.class);
                startActivity(dialog_box);
                finish();
                break;
            case R.id.logoImg:
                Intent dialog_box1 = new Intent(this, HomeActivity.class);
                startActivity(dialog_box1);
                finish();
                break;
            case R.id.btnSignUp: {
                String email = emailField.getText().toString();
                String pass = passwordField.getText().toString();
                String comfirmPass = passwordConfirmField.getText().toString();
                String username = usernameField.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty() && !comfirmPass.isEmpty()) {
                    if (!validateEmail(email)) {
                        Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!vailidatePass(pass)) {
                        Toast.makeText(this, "Mật khẩu không hợp lệ (ít nhất 8 ký tự phải bao gồm chữ in hoa, chữ số và ký tự đặc biết)", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!comfirmPass.equals(pass)) {
                        Toast.makeText(this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                        return;
//                    } else if (!rb_check.isChecked()) {
//                        Toast.makeText(this, "Vui lòng đồng ý với các điều khoản!", Toast.LENGTH_SHORT).show();
//                        return;
                    } else {
                        UserRequest userRequest = new UserRequest();
                        userRequest.setEmail(email);
                        userRequest.setUsername(username);
                        userRequest.setPassword(pass);

                        handleSignUp(userRequest);
                    }
                } else {
                    if (emailField.getText().length() == 0) {
                        Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (passwordField.getText().length() == 0) {
                        Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(this, "Vui lòng nhập nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                break;
            }
        }
    }

    private void handleSignUp(UserRequest userRequest) {
        userAPI.register(userRequest).enqueue(new Callback<APIResponse<Void>>() {
            @Override
            public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                if (response.isSuccessful()) {
                    APIResponse<Void> apiResponse = response.body();
                    if (apiResponse != null) {
//                        Toast.makeText(Signup.this, "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, VerifyAccountActivity.class);
                        intent.putExtra("email", userRequest.getEmail());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Đăng ký thất bại: " + (apiResponse != null ? apiResponse.getMessage() : "Unknown error"), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Đăng ký thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static boolean vailidatePass(String pass) {
        String expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    private boolean validateEmail(String email) {
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }
}