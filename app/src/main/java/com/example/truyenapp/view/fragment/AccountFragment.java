package com.example.truyenapp.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.AuthAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.request.LogoutRequest;
import com.example.truyenapp.response.UserResponse;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.activity.DoiMatKhau;
import com.example.truyenapp.view.activity.HomeActivity;
import com.example.truyenapp.view.activity.ShowBinhLuan;
import com.example.truyenapp.view.activity.ShowDanhGia;
import com.example.truyenapp.view.activity.ThongTinTaiKhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment implements View.OnClickListener {

    TextView tv_email, tv_level, tv_totalAttendanceDate, tv_point, tv_bookNumbers, tv_commentNumbers, tv_rating;
    TextView changePasswordBtn, logoutBtn, accountInfoBtn, myCommentBtn, myRatingBtn;
    ImageView avatar;
    View view;
    private UserAPI userAPI;
    private UserResponse userResponse;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AuthAPI authAPI;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // create API connection
        userAPI = RetrofitClient.getInstance(getContext()).create(UserAPI.class);
        authAPI = RetrofitClient.getInstance(getContext()).create(AuthAPI.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        init();
        setOnClickListener();
        getUserInfo();
        return view;
    }

    private void init() {
        tv_email = view.findViewById(R.id.tv_tk_email);
        tv_level = view.findViewById(R.id.tv_tk_lv);
        tv_totalAttendanceDate = view.findViewById(R.id.tv_tongngaydiemdanh);
        tv_point = view.findViewById(R.id.tv_tk_diem);
        tv_bookNumbers = view.findViewById(R.id.tv_tk_sotruyen);
        tv_commentNumbers = view.findViewById(R.id.tv_tk_sobinhluan);
        tv_rating = view.findViewById(R.id.tv_tk_sodanhgia);
        avatar = view.findViewById(R.id.img_tk_avatar);
        myCommentBtn = view.findViewById(R.id.tv_binhluancuatoi);
        myRatingBtn = view.findViewById(R.id.tv_danhgiacuatoi);
        changePasswordBtn = view.findViewById(R.id.tv_doimatkhau);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        accountInfoBtn = view.findViewById(R.id.tv_tttk);
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        tv_email.setText(userResponse.getEmail());
        tv_point.setText("" + userResponse.getRewardPoint());
        if (userResponse.getAvatar() == null) {
            avatar.setImageResource(R.drawable.logo);
        } else {
            Glide.with(getActivity()).load(userResponse.getAvatar()).into(avatar);
        }
        tv_totalAttendanceDate.setText("" + userResponse.getTotalAttendanceDates() + " ngày");
        tv_level.setText("Lv." + (userResponse.getTotalAttendanceDates() / 20));
        tv_commentNumbers.setText("" + userResponse.getTotalComments());
        tv_bookNumbers.setText("" + userResponse.getTotalBookReads());
        tv_rating.setText("" + userResponse.getNumberOfRatings());

    }

    private void getUserInfo() {
        // Call the getUserInfo method from the UserAPI interface
        JWTToken jwtToken = SharedPreferencesHelper.getObject(getContext(), SystemConstant.JWT_TOKEN, JWTToken.class);
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
                Toast.makeText(getActivity(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOnClickListener() {
        myRatingBtn.setOnClickListener(this);
        myCommentBtn.setOnClickListener(this);
        changePasswordBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        accountInfoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_binhluancuatoi:
                intent = new Intent(getActivity(), ShowBinhLuan.class);
                startActivity(intent);
                break;
            case R.id.tv_danhgiacuatoi:
                intent = new Intent(getActivity(), ShowDanhGia.class);
                startActivity(intent);
                break;
            case R.id.tv_doimatkhau:
                intent = new Intent(getActivity(), DoiMatKhau.class);
                startActivity(intent);
                break;
            case R.id.logoutBtn:
                logoutHandle();
                break;
            case R.id.tv_tttk:
                intent = new Intent(getActivity(), ThongTinTaiKhoan.class);
                startActivity(intent);
                break;
        }
    }

    private void logoutHandle() {
        JWTToken token = SharedPreferencesHelper.getObject(getActivity(), SystemConstant.JWT_TOKEN, JWTToken.class);
        authAPI.logout(new LogoutRequest(token.getToken())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                SharedPreferencesHelper.deletePreference(getActivity(), SystemConstant.JWT_TOKEN);
                Toast.makeText(getActivity(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.e("TAG", "Logout failed: " + throwable.getMessage());
                Toast.makeText(getActivity(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}