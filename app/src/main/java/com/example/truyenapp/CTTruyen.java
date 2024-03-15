package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Truyen;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CTTruyen extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    ImageView img_truyen;
    Database db;
    Truyen truyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cttruyen);

        Anhxa();

        db=new Database(this);
        Intent intent=getIntent();
        int id_truyen=intent.getIntExtra("id_truyen",1);
        String lenhSQLite="select * from truyen where id="+id_truyen;
        ArrayList<Truyen> listtruyen=db.getTruyen(lenhSQLite);
        truyen=listtruyen.get(0);
        Glide.with(this).load(truyen.getLinkhanh()).into(img_truyen);


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
    }

    private void Anhxa(){
        tabLayout=findViewById(R.id.tab_layout);
        pager2=findViewById(R.id.view_pager2);
        img_truyen=findViewById(R.id.img_truyen);
    }
}
