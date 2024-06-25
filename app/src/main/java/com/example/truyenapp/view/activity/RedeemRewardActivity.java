package com.example.truyenapp.view.activity;

import android.annotation.SuppressLint;
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
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.AttendanceResponse;
import com.example.truyenapp.response.RewardPointResponse;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.DialogHelper;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedeemRewardActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutStore, layoutHistory;
    Button btnAttendance;
    TextView tvTotalPoint, tvContinuousDay;
    private boolean isLoggedIn;
    private UserAPI userAPI;
    private DialogHelper dialogHelper;
    private Integer dayAttendanceContinuous = 0;
    private Double totalPoint = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_reward);
        init();
        getRewardPointOverall();
        setOnClickListener();
    }

    private void init() {
        layoutStore = findViewById(R.id.redeem_reward_store);
        layoutHistory = findViewById(R.id.redeem_reward_history);
        btnAttendance = findViewById(R.id.button_attendance_redeem_reward);
        tvTotalPoint = findViewById(R.id.tv_redeem_reward_total_point);
        tvContinuousDay = findViewById(R.id.tv_redeem_reward_continous);
        this.userAPI = RetrofitClient.getInstance(this.getApplicationContext()).create(UserAPI.class);
        this.dialogHelper = new DialogHelper(this);
        this.isLoggedIn = AuthenticationManager.isLoggedIn(SharedPreferencesHelper.getObject(this.getApplicationContext(), SystemConstant.JWT_TOKEN, JWTToken.class));
        getRewardPointOverall();
    }

    private void setOnClickListener() {
        layoutStore.setOnClickListener(this);
        btnAttendance.setOnClickListener(this);
        layoutHistory.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        tvContinuousDay.setText(dayAttendanceContinuous + " ngày liên tiếp");
        tvTotalPoint.setText(totalPoint + " điểm");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRewardPointOverall();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.redeem_reward_store:
                Intent intent = new Intent(this, StoreActivity.class);
                intent.putExtra(BundleConstraint.TOTAL_SCORE, totalPoint);
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
            case R.id.redeem_reward_history:
                Intent intent1 = new Intent(this, RedeemRewardHistoryActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public void getRewardPointOverall() {
        userAPI.getRewardPoint().enqueue(new Callback<APIResponse<RewardPointResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<RewardPointResponse>> call, Response<APIResponse<RewardPointResponse>> response) {
                APIResponse<RewardPointResponse> apiResponse = response.body();
                if (apiResponse.getCode() == 200) {
                    RewardPointResponse rewardPoint = apiResponse.getResult();
                    dayAttendanceContinuous = rewardPoint.getDateAttendanceContinuous();
                    totalPoint = rewardPoint.getTotalPoint();
                    setData();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<RewardPointResponse>> call, Throwable throwable) {
                Log.e("TAG", "Get reward point failed: " + throwable.getMessage());
            }
        });
    }

    public void attendanceAPI() {
        userAPI.attendance().enqueue(new Callback<APIResponse<AttendanceResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AttendanceResponse>> call, Response<APIResponse<AttendanceResponse>> response) {
                APIResponse<AttendanceResponse> apiResponse = response.body();
                if (apiResponse.getCode() == 200) {
                    AttendanceResponse attendanceResponse = apiResponse.getResult();
                    dialogHelper.showDialogAttendance("Điểm danh thành công! +" + attendanceResponse.getPoint() + " điểm").show();
                } else {
                    dialogHelper.showDialogAttendance("Hôm nay bạn đã điểm danh, chờ đến ngày mai nhé!").show();
                }
                getRewardPointOverall();
            }

            @Override
            public void onFailure(Call<APIResponse<AttendanceResponse>> call, Throwable throwable) {
                Log.e("TAG", "Attendance failed: " + throwable.getMessage());
                dialogHelper.showDialogAttendance("Lỗi, vui lòng thử lại").show();
            }
        });
    }
}
