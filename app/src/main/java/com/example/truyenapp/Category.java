package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyenapp.database.Database;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Category extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterCategory adapter;
    Database db;
    ArrayList<String> listtheloai;
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;
    String textTheLoai;
    private final String[] TAB_TEXT = {"Mới nhất", "BXH Votes", "BXH Lượt Xem"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai);

        db = new Database(this);
        init();

        listtheloai = db.getTheLoai();

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new FragmentAdapterCategory(fragmentManager, getLifecycle());
        pager2.setAdapter(adapter);

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

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, listtheloai);
        autoCompleteTextView.setText(listtheloai.get(0));
        autoCompleteTextView.setAdapter(adapterItems);
        textTheLoai = listtheloai.get(0);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Thể loại: " + item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }


    private void init() {
        this.tabLayout = findViewById(R.id.tab_layout_tl);
        this.pager2 = findViewById(R.id.view_pager2_tl);
        this.autoCompleteTextView = findViewById(R.id.auto_complete_txt);
    }
}