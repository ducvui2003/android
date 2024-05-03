package com.example.truyenapp.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.request.UserRequest;
import com.example.truyenapp.response.UserResponse;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountInfoActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView avatarImg;
    TextView tv_id, tv_email, tv_username, tv_point;
    EditText edt_phone, edt_fullName;
    Button updateBtn, saveBtn, cancelBtn;
    private UserAPI userAPI;
    private UserResponse userResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintaikhoan);
        // create API connection
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        init();
        In_Visible(0);
        setOnClickListener();
        getUserInfo();
    }

    private void setOnClickListener() {
        updateBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        tv_id.setText("" + userResponse.getId());
        tv_email.setText(userResponse.getEmail());
        tv_point.setText("" + userResponse.getRewardPoint());
        tv_username.setText(userResponse.getUsername());
        edt_phone.setText(userResponse.getPhone());
        edt_fullName.setText(userResponse.getFullName());
        String avatar = userResponse.getAvatar();
        if (avatar != null) {
            Glide.with(this).load(avatar).into(avatarImg);
        } else {
            avatarImg.setImageResource(R.drawable.logo);
        }

    }

    private void getUserInfo() {
        // Call the getUserInfo method from the UserAPI interface
        JWTToken jwtToken = SharedPreferencesHelper.getObject(this, SystemConstant.JWT_TOKEN, JWTToken.class);
        if (jwtToken == null) {
            return;
        }
        userAPI.getUserInfo(jwtToken.getToken()).enqueue(new Callback<UserResponse>() {
            // This method is called when the server response is received
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Get the UserResponse object from the response
                UserResponse user = response.body();
                // Check if the user object is not null
                if (user != null) {
                    // Assign the user object to the userResponse variable
                    userResponse = user;
                    setData();
                }
            }

            // This method is called when the request could not be executed due to cancellation, a connectivity problem or a timeout
            @Override
            public void onFailure(Call<UserResponse> call, Throwable throwable) {
                // Log the error message
                Log.e("TAG", "Login failed: " + throwable.getMessage());
                // Show a toast message indicating that an error occurred
                Toast.makeText(getApplicationContext(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        avatarImg = findViewById(R.id.img_tttk);
        tv_point = findViewById(R.id.tv_tttk_diem);
        tv_email = findViewById(R.id.tv_tttk_email);
        tv_id = findViewById(R.id.tv_tttk_id);
        edt_phone = findViewById(R.id.edt_tttk_dienthoai);
        edt_fullName = findViewById(R.id.edt_tttk_ten);
        tv_username = findViewById(R.id.tv_username);
        updateBtn = findViewById(R.id.bt_chinhsua);
        cancelBtn = findViewById(R.id.bt_huychinhsua);
        saveBtn = findViewById(R.id.bt_xacnhan);
    }

    private void In_Visible(int i) {
        if (i == 1) {
            edt_fullName.setEnabled(true);
            edt_phone.setEnabled(true);
            saveBtn.setVisibility(View.VISIBLE);
            cancelBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.GONE);
        } else {
            edt_fullName.setEnabled(false);
            edt_phone.setEnabled(false);
            saveBtn.setVisibility(View.GONE);
            cancelBtn.setVisibility(View.GONE);
            updateBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_chinhsua:
                In_Visible(1);
                break;
            case R.id.bt_huychinhsua:
                getUserInfo();
                In_Visible(0);
                break;
            case R.id.bt_xacnhan:
                handleUpdateUserInfo();
                break;
        }
    }

    private void handleUpdateUserInfo() {
        // Get the user information from the input fields
        String fullName = edt_fullName.getText().toString().trim();
        String phone = edt_phone.getText().toString().trim();
        // Check if the full name is empty
        if (fullName.isEmpty()) {
            // Show a toast message indicating that the full name is empty
            Toast.makeText(this, "Họ tên đang trống!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Check if the phone number is empty
        if (phone.isEmpty()) {
            // Show a toast message indicating that the phone number is empty
            Toast.makeText(this, "Điện thoại đang trống!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Call the updateInfo method from the UserAPI interface
        // Build a UserRequest object with the phone and fullName from the input fields
        userAPI.updateInfo(UserRequest.builder().phone(phone).fullName(fullName).build()).enqueue(new Callback<Void>() {
            // This method is called when the server response is received
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Check if the response is successful
                if (response.isSuccessful()) {
                    // If the response is successful, make the input fields uneditable and show a success message
                    In_Visible(0);
                    Toast.makeText(AccountInfoActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // If the response is not successful, show a failure message
                    Toast.makeText(AccountInfoActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            // This method is called when the request could not be executed due to cancellation, a connectivity problem or a timeout
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Show a toast message indicating that an error occurred
                Toast.makeText(AccountInfoActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
