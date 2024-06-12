package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RedeemRewardAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.FragmentAdapterStore;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 pager2;
    private FragmentAdapterStore adapterFragmentStore;
    private TextView tvTotalScore;

    private String[] TAB_TEXT = {"Cửa hàng", "Kho vật phẩm"};
    private RedeemRewardAPI redeemRewardAPI;

    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        init();

        Intent intent = getIntent();
        Double totalScore = intent.getDoubleExtra(BundleConstraint.TOTAL_SCORE, 0);
        tvTotalScore.setText(String.valueOf(totalScore));


        FragmentManager fragmentManager = getSupportFragmentManager();
        adapterFragmentStore = new FragmentAdapterStore(fragmentManager, getLifecycle());
        pager2.setAdapter(adapterFragmentStore);

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


    private void init() {
        tabLayout = findViewById(R.id.tab_layout_cuahang);
        pager2 = findViewById(R.id.view_pager2_cuahang);
        tvTotalScore = findViewById(R.id.tv_diemtichluy);
        items = new ArrayList<>();

    }
}
