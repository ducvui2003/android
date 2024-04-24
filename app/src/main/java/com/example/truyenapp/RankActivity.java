package com.example.truyenapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyenapp.view.fragment.RankViewPaperAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RankActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    RankViewPaperAdapter adapter;
    private final String[] TAB_TEXT = {"BXH Đánh giá", "BXH Lượt xem"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        tabLayout = findViewById(R.id.tab_layout_rank);
        pager2 = findViewById(R.id.view_pager2_rank);

        adapter = new RankViewPaperAdapter(this);
        pager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, pager2,
                (tab, position) -> tab.setText(TAB_TEXT[position])
        ).attach();
    }
}
