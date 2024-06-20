package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyenapp.R;
import com.example.truyenapp.api.BookAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.Comic;
import com.example.truyenapp.view.fragment.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class DetailComicActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 pager2;
    private FragmentAdapter adapter;
    private ImageView imgComic;
    private Comic comic;
    private BookAPI bookAPI;
    private int idComic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_comic);
        Intent intent = getIntent();
        idComic = intent.getIntExtra(BundleConstraint.ID_COMIC, 0);
        init();
        bookAPI = RetrofitClient.getInstance(this).create(BookAPI.class);
        FragmentManager fragmentManager=getSupportFragmentManager();
        adapter=new FragmentAdapter(fragmentManager,getLifecycle());
        pager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Chi tiết"));
        tabLayout.addTab(tabLayout.newTab().setText("Chapter"));

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

        adapter.setIdComic(idComic);
    }
    private void init(){
        tabLayout=findViewById(R.id.tab_layout);
        pager2=findViewById(R.id.view_pager2);
        imgComic =findViewById(R.id.img_truyen);
    }


}
