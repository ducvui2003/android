package com.example.truyenapp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.request.ForgotPasswordRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    private Button submitBtn;
    private EditText emailField;
    private ImageView logo;
    private UserAPI userAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        init();
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgot_pass_btn: {
                sendMail();
                break;
            }
            case R.id.logo_forgot:
                Intent dialog_box1 = new Intent(this, HomeActivity.class);
                startActivity(dialog_box1);
                finish();
                break;
        }
    }

    /**
     * Sends a password reset email to the provided email address.
     */
    private void sendMail() {
        // Get the email address entered by the user
        String email = emailField.getText().toString();

        // Check if the email address is not empty
        if (!email.isEmpty()) {
            // Validate the email address format
            if (!validateEmail(email)) {
                // Show a toast message if the email address is invalid
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            } else {
                // Send a request to the server to reset the password
                userAPI.forgotPass(new ForgotPasswordRequest(email)).enqueue(new Callback<APIResponse<Void>>() {
                    @Override
                    public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                        // Check if the request was successful
                        if (response.isSuccessful()) {
                            // Show a toast message indicating that the email has been sent
                            Toast.makeText(ForgotPassword.this, "Đã gửi email", Toast.LENGTH_SHORT).show();

                            // Proceed to the NewPassword activity
                            Intent dialog_box = new Intent(ForgotPassword.this, NewPassword.class);
                            dialog_box.putExtra("email", email);
                            startActivity(dialog_box);
                        } else {
                            // Show a toast message if the email address does not exist in the system
                            Toast.makeText(ForgotPassword.this, "Email không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse<Void>> call, Throwable throwable) {
                        // Log any error that occurred during the password reset process
                        Log.e("TAG", "Forgot password failed: " + throwable.getMessage());

                        // Show a toast message indicating that an error occurred
                        Toast.makeText(ForgotPassword.this, "Lỗi, voi lòng thử lại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            // Show a toast message if the email address field is empty
            Toast.makeText(this, "Vui lòng nhập địa chỉ email", Toast.LENGTH_SHORT).show();
        }
    }


    private void init() {
        submitBtn = findViewById(R.id.forgot_pass_btn);
        emailField = findViewById(R.id.email_forgot);
        logo = findViewById(R.id.logo_forgot);
        submitBtn.setOnClickListener(this);
        logo.setOnClickListener(this);
    }

    private boolean validateEmail(String email) {
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
