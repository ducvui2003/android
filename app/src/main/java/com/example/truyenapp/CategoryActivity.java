package com.example.truyenapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.SearchAPI;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.response.CategoryResponse;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterCategory adapter;
    ArrayAdapter<String> categoryAdapter;
    String category;
    Map<Integer, String> mapCategory;
    Database db;
    ArrayList<String> listtheloai;
    AutoCompleteTextView autoCompleteTextView;
    Integer categoryId;
    String textTheLoai;
    private final String[] TAB_TEXT = {"Mới nhất", "BXH Votes", "BXH Lượt Xem"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai);

        db = new Database(this);
        init();

//        listtheloai = db.getTheLoai();

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


        autoCompleteTextView.setAdapter(categoryAdapter);
//        textTheLoai = listtheloai.get(0);
        initCategory();

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
        categoryAdapter = new  ArrayAdapter(this, R.layout.list_item);
        mapCategory = new HashMap<>();
    }

    private void handleEvent() {
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                handleSearch();
            }

        });
    }

    public void handleSearch() {
        this.category = autoCompleteTextView.getText().toString();
        this.categoryId = getCategory();
        adapter.setCategory(this.categoryId);
}


    private Integer getCategory() {
        String category = autoCompleteTextView.getText().toString();
        return mapCategory.entrySet().stream().filter(entry -> entry.getValue().equals(category)).map(Map.Entry::getKey).findFirst().orElse(null);
    }

    private void initCategory() {
        SearchAPI response = RetrofitClient.getInstance(this).create(SearchAPI.class);
        response.getCategory().enqueue(new Callback<APIResponse<List<CategoryResponse>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<CategoryResponse>>> call, Response<APIResponse<List<CategoryResponse>>> response) {
                APIResponse<List<CategoryResponse>> data = response.body();
                for (CategoryResponse category : data.getResult()) {
                    mapCategory.put(category.getId(), category.getName());
                }
                categoryAdapter.add("Tất cả");
                categoryAdapter.addAll(mapCategory.values());
                categoryAdapter.notifyDataSetChanged();
                handleEvent();
            }

            @Override
            public void onFailure(Call<APIResponse<List<CategoryResponse>>> call, Throwable throwable) {
                Log.d("SearchActivity", "onFailure: " + throwable.getMessage());
            }
        });
    }

    private void searchAPI() {
    }
}