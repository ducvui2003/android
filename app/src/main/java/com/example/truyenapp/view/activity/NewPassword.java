package com.example.truyenapp.view.activity;

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
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.request.ChangePasswordRequest;
import com.example.truyenapp.request.ForgotPasswordRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPassword extends AppCompatActivity implements View.OnClickListener {
    EditText otpInput, newPasswordInput, confirmPasswordInput;
    Button submitBtn, resentOTPBtn;
    ImageView logoForgot;
    TextView tvEmailForgot;

    public String email;
    private UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_2);

        init();
        userAPI = RetrofitClient.getInstance().create(UserAPI.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_btn: {
                String password = newPasswordInput.getText().toString();
                String otp = otpInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();
                ChangePasswordRequest passwordRequest = ChangePasswordRequest.builder()
                        .email(email).otp(otp).password(password).confirmPassword(confirmPassword).build();

                if (hasError(passwordRequest))
                    return;

                changePasswordHandle(passwordRequest);
                break;
            }
            case R.id.logo_forgot:
                Intent dialog_box1 = new Intent(this, HomeActivity.class);
                startActivity(dialog_box1);
                finish();
                break;
            case R.id.resent_OTP_btn:
                resendOTPHandle();
                break;
        }
    }

    /**
     * Checks if there is any error in the provided change password request.
     * @param passwordRequest The change password request object.
     * @return True if there is an error, false otherwise.
     */
    private boolean hasError(ChangePasswordRequest passwordRequest) {
        // Check if any field in the password request is empty
        if (passwordRequest.getPassword().isEmpty() || passwordRequest.getOtp().isEmpty()
                || passwordRequest.getConfirmPassword().isEmpty()) {
            // Show a toast message if any field is empty
            Toast.makeText(this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Check if the password and confirm password fields match
        if (!passwordRequest.getConfirmPassword().equals(passwordRequest.getPassword())) {
            // Show a toast message if the passwords do not match
            Toast.makeText(this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /**
     * Handles the process of changing the password.
     * @param passwordRequest The change password request object.
     */
    private void changePasswordHandle(ChangePasswordRequest passwordRequest) {
        // Send a request to the server to change the password
        userAPI.changePass(passwordRequest).enqueue(new Callback<APIResponse<Void>>() {
            @Override
            public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                // Check if the request was successful
                if (response.isSuccessful()) {
                    // Show a toast message indicating that the password has been changed successfully
                    Toast.makeText(NewPassword.this, "Thay đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();

                    // Proceed to the Signin activity
                    Intent dialog_box = new Intent(NewPassword.this, Signin.class);
                    startActivity(dialog_box);
                } else {
                    // Show a toast message if the OTP is incorrect
                    Toast.makeText(NewPassword.this, "Mã xác nhận không chính xác. Vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Void>> call, Throwable throwable) {
                // Log any error that occurred during the password change process
                Log.e("TAG", "Change password failed: " + throwable.getMessage());

                // Show a toast message indicating that an error occurred
                Toast.makeText(NewPassword.this, "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Resends the OTP (One-Time Password) to the user's email.
     */
    private void resendOTPHandle() {
        // Send a request to the server to resend the OTP
        userAPI.forgotPass(new ForgotPasswordRequest(email)).enqueue(new Callback<APIResponse<Void>>() {
            @Override
            public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                // Check if the request was successful
                if (response.isSuccessful()) {
                    // Show a toast message indicating that the OTP has been resent
                    Toast.makeText(NewPassword.this, "Mã OTP đã được gửi lại!", Toast.LENGTH_SHORT).show();
                } else {
                    // Show a toast message if the email is not found
                    Toast.makeText(NewPassword.this, "Không tìm thấy email!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Void>> call, Throwable throwable) {
                // Log any error that occurred during the OTP resend process
                Log.e("TAG", "OTP resend failed: " + throwable.getMessage());

                // Show a toast message indicating that an error occurred
                Toast.makeText(NewPassword.this, "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        logoForgot = findViewById(R.id.logo_forgot);
        resentOTPBtn = findViewById(R.id.resent_OTP_btn);
        submitBtn = findViewById(R.id.submit_btn);
        otpInput = findViewById(R.id.otp_input);
        newPasswordInput = findViewById(R.id.new_password_input);
        confirmPasswordInput = findViewById(R.id.confirm_password_input);
        tvEmailForgot = findViewById(R.id.tv_email_forgot);

        submitBtn.setOnClickListener(this);
        resentOTPBtn.setOnClickListener(this);
        logoForgot.setOnClickListener(this);

        Intent i = getIntent();
        email = i.getStringExtra("email");
        tvEmailForgot.setText(email);
    }

}