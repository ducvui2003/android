package com.example.truyenapp.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.truyenapp.view.fragment.HomeFragment;
import com.example.truyenapp.R;
import com.example.truyenapp.view.fragment.AccountFragment;
import com.example.truyenapp.ThongBaoFragment;
import com.example.truyenapp.view.fragment.TuSachFragment;
import com.example.truyenapp.database.Database;


public class HomeActivity extends AppCompatActivity {

    String email;
    MeowBottomNavigation meowBottomNavigation;
    Database db;

    private void initNavigateBottom() {
        meowBottomNavigation = findViewById(R.id.bottom_nav);
        meowBottomNavigation.show(1, true);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_noti));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_book));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_account));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        db = new Database(this);
        int notifyCount = db.countThongBaoNow();

        initNavigateBottom();

        if (notifyCount != 0) {
            meowBottomNavigation.setCount(2, notifyCount + "");
        }

        handleEventNav();
    }

    private void handleEventNav() {
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        if (email != null) {
                            fragment = new ThongBaoFragment();
                        }
                        break;
                    case 3:
                        if (email != null) {
                            fragment = new TuSachFragment();
                        }
                        break;
                    case 4:
                        if (email != null) {
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
                        if (email == null) {
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

    private AlertDialog.Builder showDialogLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhắc nhở").setMessage("Vui lòng đăng nhập để sử dụng chức năng này.");
        builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(HomeActivity.this, Signin.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                defaultIdNav();
            }
        });
        builder.setOnCancelListener(dialogInterface -> {
            defaultIdNav();
        });
        return builder;
    }

    private void defaultIdNav(){
        meowBottomNavigation.show(1, true);
    }
}