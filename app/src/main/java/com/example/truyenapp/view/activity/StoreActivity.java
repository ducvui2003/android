package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyenapp.R;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.view.adapter.FragmentAdapterStore;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StoreActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterStore adpaterFragmentStore;
    TextView tvTotalScore;

    String[] TAB_TEXT = {"Cửa hàng", "Kho vật phẩm", "Lịch sử"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        init();

        Intent intent = getIntent();
        Double totalScore = intent.getDoubleExtra(BundleConstraint.TOTAL_SCORE, 0);
        tvTotalScore.setText(String.valueOf(totalScore));


        FragmentManager fragmentManager = getSupportFragmentManager();
        adpaterFragmentStore = new FragmentAdapterStore(fragmentManager, getLifecycle());
        pager2.setAdapter(adpaterFragmentStore);

        new TabLayoutMediator(tabLayout, pager2,
                (tab, position) -> tab.setText(TAB_TEXT[position])
        ).attach();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    ;

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void init() {
        tabLayout = findViewById(R.id.tab_layout_cuahang);
        pager2 = findViewById(R.id.view_pager2_cuahang);
        tvTotalScore = findViewById(R.id.tv_diemtichluy);
    }

    private void getRedeemReward() {

    }
}
