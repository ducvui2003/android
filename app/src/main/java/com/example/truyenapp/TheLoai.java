package com.example.truyenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.truyenapp.database.Database;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TheLoai extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterTheLoai adapter;
    Database db;

    ArrayList<String> listtheloai;
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;
    String textTheLoai;

    TheLoaiNewFragment theLoaiNewFragment;
    TheLoaiVoteFragment theLoaiVoteFragment;
    TheLoaiLuotXemFragment theLoaiLuotXemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai);

        db=new Database(this);
        Anhxa();

        listtheloai=db.getTheLoai();

        FragmentManager fragmentManager=getSupportFragmentManager();
        adapter=new FragmentAdapterTheLoai(fragmentManager,getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Mới nhất"));
        tabLayout.addTab(tabLayout.newTab().setText("BXH Votes"));
        tabLayout.addTab(tabLayout.newTab().setText("BXH Lượt Xem"));

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

        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,listtheloai);
        autoCompleteTextView.setText(listtheloai.get(0));
        autoCompleteTextView.setAdapter(adapterItems);
        textTheLoai=listtheloai.get(0);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Thể loại: "+item,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }


    private void Anhxa(){
        tabLayout=findViewById(R.id.tab_layout_tl);
        pager2=findViewById(R.id.view_pager2_tl);
        autoCompleteTextView=findViewById(R.id.auto_complete_txt);
    }
}