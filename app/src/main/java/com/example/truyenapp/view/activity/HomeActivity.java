package com.example.truyenapp.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.DialogEvent;
import com.example.truyenapp.utils.DialogHelper;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.fragment.HomeFragment;
import com.example.truyenapp.R;
import com.example.truyenapp.view.fragment.AccountFragment;
import com.example.truyenapp.view.fragment.NotificationFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    String email;
    int numberNotification;
    MeowBottomNavigation meowBottomNavigation;


    private void initNavigateBottom() {
        meowBottomNavigation = findViewById(R.id.bottom_nav);
        meowBottomNavigation.show(1, true);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_noti));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_account));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initNavigateBottom();
//        getNumberNotifications();
        if (numberNotification != 0) {
            meowBottomNavigation.setCount(2, numberNotification + "");
        }

        handleEventNav();
    }

    /**
     * Handle event navigation
     * author: Hoang
     * status: done
     */
    private void handleEventNav() {
        boolean isLoggedIn = AuthenticationManager.isLoggedIn(SharedPreferencesHelper.getObject(getApplicationContext(), SystemConstant.JWT_TOKEN, JWTToken.class));
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        if (isLoggedIn) {
                            fragment = new NotificationFragment();
                        }
                        break;
                    case 4:
                        if (isLoggedIn) {
                            fragment = new AccountFragment();
                        }
                        break;
                }
                if (fragment != null) {
                    loadFragment(fragment);
                }
            }
        });

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 2:
                    case 3:
                    case 4:
                        if (!isLoggedIn) {
                            showDialogLogin().show();
                        }
                        break;
                }
            }
        });

        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                return;
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

   public  AlertDialog.Builder showDialogLogin() {
        DialogHelper dialogHelper  = new DialogHelper(this, new DialogEvent() {
            @Override
            public void onPositiveClick() {
            }
            @Override
            public void onNegativeClick() {
                defaultIdNav();
            }

            @Override
            public void onCancel() {
                defaultIdNav();
            }
        });
        return dialogHelper.showDialogLogin();
    }

    private void getNumberNotifications() {
        Context context = this;
        UserAPI response = RetrofitClient.getInstance(context).create(UserAPI.class);
        response.getNumberNotifications().enqueue(new Callback<APIResponse<Integer>>() {
            @Override
            public void onResponse(Call<APIResponse<Integer>> call, Response<APIResponse<Integer>> response) {
                if (response.isSuccessful()) {
                    APIResponse<Integer> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getResult() != null) {
                        numberNotification = apiResponse.getResult();
                    }
                } else {
                    Toast.makeText(context, "Trống", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<APIResponse<Integer>> call, Throwable throwable) {
                Log.e("TAG", "Login failed: " + throwable.getMessage());
                // Show a toast message indicating that an error occurred
                Toast.makeText(context, "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void defaultIdNav(){
        meowBottomNavigation.show(1, true);
    }
}