package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.AttendanceResponse;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.DialogHelper;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.utils.UserConstraint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedeemRewardActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ll_cuahang, ll_lichsu;
    Button btnAttendance;
    Database db;
    Account account;
    TextView tv_diemtichluy, tv_songaydd;
    private boolean isLoggedIn;
    private UserAPI userAPI;
    private DialogHelper dialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_reward);
        db = new Database(this);
        init();

        setData();
        setOnClickListener();
    }

    private void init() {
        ll_cuahang = findViewById(R.id.ll_cuahang);
        ll_lichsu = findViewById(R.id.ll_lichsu);
        btnAttendance = findViewById(R.id.button_attendance_redeem_reward);
        tv_diemtichluy = findViewById(R.id.tv_diemtichluy);
        tv_songaydd = findViewById(R.id.tv_songaydd);
        this.userAPI = RetrofitClient.getInstance(this.getApplicationContext()).create(UserAPI.class);
        this.dialogHelper = new DialogHelper(this);
    }

    private void setOnClickListener() {
        ll_cuahang.setOnClickListener(this);
        btnAttendance.setOnClickListener(this);
        ll_lichsu.setOnClickListener(this);
    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void setData() {
        tv_songaydd.setText(UserConstraint.DAY_ATTENDANCE_CONTINUOUS + " ngày liên tiếp");
        tv_diemtichluy.setText(UserConstraint.TOTAL_POINT + " điểm");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.isLoggedIn = AuthenticationManager.isLoggedIn(SharedPreferencesHelper.getObject(this.getApplicationContext(), SystemConstant.JWT_TOKEN, JWTToken.class));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_cuahang:
                Intent intent = new Intent(this, CuaHang.class);
                startActivity(intent);
                break;
            case R.id.button_attendance_redeem_reward: {
                if (isLoggedIn) {
                    attendanceAPI();
                } else {
                    DialogHelper dialogHelper = new DialogHelper(this);
                    dialogHelper.showDialogLogin().show();
                }
                break;
            }
            case R.id.ll_lichsu:
                Intent intent1 = new Intent(this, LichSuNhanDiem.class);
                startActivity(intent1);
                break;
        }
    }

    public void attendanceAPI() {
        userAPI.attendance().enqueue(new Callback<APIResponse<AttendanceResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AttendanceResponse>> call, Response<APIResponse<AttendanceResponse>> response) {
                APIResponse<AttendanceResponse> apiResponse = response.body();
                if (apiResponse.getCode() == 200) {
                    AttendanceResponse attendanceResponse = apiResponse.getResult();
                    UserConstraint.DAY_ATTENDANCE_CONTINUOUS = attendanceResponse.getDateAttendanceContinuous();
                    UserConstraint.TOTAL_POINT = attendanceResponse.getTotalPoint();
                    dialogHelper.showDialog("Điểm danh thành công! +" + attendanceResponse.getPoint() + " điểm").show();
                } else {
                    dialogHelper.showDialog("Hôm nay bạn đã điểm danh, chờ đến ngày mai nhé!").show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<AttendanceResponse>> call, Throwable throwable) {
                Log.e("TAG", "Attendance failed: " + throwable.getMessage());
                dialogHelper.showDialog("Lỗi, vui lòng thử lại").show();
            }
        });
    }
}
