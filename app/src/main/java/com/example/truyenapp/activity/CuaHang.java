package com.example.truyenapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyenapp.R;
import com.example.truyenapp.adapter.FragmentAdapterCuaHang;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.TaiKhoan;
import com.google.android.material.tabs.TabLayout;

public class CuaHang extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterCuaHang adapterCuaHang;
    Database db;
    TaiKhoan taiKhoan;
    TextView tv_diemtichluy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuahang);

        Anhxa();

        db=new Database(this);
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        taiKhoan=db.getTaiKhoan(email);
        tv_diemtichluy.setText(""+taiKhoan.getDiemthuong());


        FragmentManager fragmentManager=getSupportFragmentManager();
        adapterCuaHang=new FragmentAdapterCuaHang(fragmentManager,getLifecycle());
        pager2.setAdapter(adapterCuaHang);

        tabLayout.addTab(tabLayout.newTab().setText("Cửa hàng"));
        tabLayout.addTab(tabLayout.newTab().setText("Kho vật phẩm"));
        tabLayout.addTab(tabLayout.newTab().setText("Lịch sử"));

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
    };

    public void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }

    private void Anhxa(){
        tabLayout=findViewById(R.id.tab_layout_cuahang);
        pager2=findViewById(R.id.view_pager2_cuahang);
        tv_diemtichluy=findViewById(R.id.tv_diemtichluy);
    }
}
