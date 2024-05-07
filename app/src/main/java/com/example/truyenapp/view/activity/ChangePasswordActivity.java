package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.request.ChangePasswordRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_currentPassword, edt_newPassword, edt_confirmPassword;
    Button saveBtn, cancelBtn;
    UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        setContentView(R.layout.doimatkhau);
        init();
        setOnClickListener();
    }

    private void init() {
        edt_currentPassword = findViewById(R.id.edt_dmk_mkht);
        edt_newPassword = findViewById(R.id.edt_dmk_mkm);
        edt_confirmPassword = findViewById(R.id.edt_dmk_nlmk);
        cancelBtn = findViewById(R.id.bt_huy);
        saveBtn = findViewById(R.id.bt_xndmk);
    }

    private void setOnClickListener() {
        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_huy:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_xndmk:
                handleChangePassword();
                break;
        }
    }

    private void handleChangePassword() {
        String currentPassword = edt_currentPassword.getText().toString();
        String newPassword = edt_newPassword.getText().toString();
        String confirmPassword = edt_confirmPassword.getText().toString();

        if (currentPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu hiện tại", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập nhập lại mật khẩu mới", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!confirmPassword.equals(newPassword)) {
            Toast.makeText(this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
            return;
        }

        ChangePasswordRequest passwordRequest = ChangePasswordRequest.builder()
                .password(currentPassword)
                .newPassword(newPassword)
                .confirmPassword(confirmPassword)
                .build();

        // Call the changePass method from the UserAPI interface with the passwordRequest object
        userAPI.changePass(passwordRequest).enqueue(new Callback<APIResponse<Void>>() {
            // This method is called when the server response is received
            @Override
            public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                // Check if the response is successful
                if (response.isSuccessful()) {
                    // If the response is successful, show a success message and navigate to the HomeActivity
                    Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent1);
                    finish();
                } else {
                    // If the response is not successful, show a failure message
                    Toast.makeText(getApplicationContext(), "Mật khẩu hiện tại không chính xác", Toast.LENGTH_SHORT).show();
                }
            }

            // This method is called when the request could not be executed due to cancellation, a connectivity problem or a timeout
            @Override
            public void onFailure(Call<APIResponse<Void>> call, Throwable throwable) {
                // Log the error message and show a toast message indicating that an error occurred
                Log.e("TAG", "Login failed: " + throwable.getMessage());
                Toast.makeText(ChangePasswordActivity.this, "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
