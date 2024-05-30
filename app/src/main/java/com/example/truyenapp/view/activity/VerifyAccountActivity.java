package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.truyenapp.model.AccountVerifyRequest;
import com.example.truyenapp.request.ChangePasswordRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyAccountActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonSubmit;
    private ImageView logo;
    private TextView emailVerify;
    private EditText otpInput;
    private UserAPI userAPI;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_account);
        email = getIntent().getStringExtra("email");
        init();
        emailVerify.setText(email);
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logo_verify:
                Intent dialog_box = new Intent(this, HomeActivity.class);
                startActivity(dialog_box);
                finish();
                break;
            case R.id.submitBtn:
                handleVerifyAccount(AccountVerifyRequest.builder()
                        .email(email)
                        .otp(otpInput.getText().toString())
                        .build());
                break;
        }
    }

    private void init() {
        buttonSubmit = findViewById(R.id.submitBtn);
        emailVerify = findViewById(R.id.email_verify);
        otpInput = findViewById(R.id.otp_input);
        logo = findViewById(R.id.logo_verify);
        buttonSubmit.setOnClickListener(this);
        logo.setOnClickListener(this);
    }

    private void handleVerifyAccount(AccountVerifyRequest accountVerifyRequest) {
        userAPI.verifyAccount(accountVerifyRequest).enqueue(new Callback<APIResponse<Void>>() {
            @Override
            public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(VerifyAccountActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyAccountActivity.this, Signin.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(VerifyAccountActivity.this, "OTP không đúng", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyAccountActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                Toast.makeText(VerifyAccountActivity.this, "Xác thực không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}