package com.example.truyenapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.adapter.ShowBinhLuanAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.BinhLuan;
import com.example.truyenapp.model.TaiKhoan;

import java.util.ArrayList;

public class ShowBinhLuan extends AppCompatActivity {

    Database db;

    private RecyclerView rcv_binhluan;
    private ShowBinhLuanAdapter rcv_adapter;
    TaiKhoan taiKhoan;
    String email;
    TextView tv_binhluan_tong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showbinhluan);

        db=new Database(this);
        Anhxa();
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        taiKhoan=db.getTaiKhoan(email);

        int tongbinhluan=db.getTongBinhLuan(taiKhoan.getId());
        tv_binhluan_tong.setText("Tổng bình luận: "+tongbinhluan);
        recyclerViewBinhLuan();
    };

    private void recyclerViewBinhLuan(){
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcv_binhluan.setLayoutManager(linearLayoutManager2);
        ArrayList<BinhLuan> listBinhLuan=db.getShowBinhLuan(taiKhoan.getId());
        rcv_adapter=new ShowBinhLuanAdapter(this,listBinhLuan,db);
        rcv_binhluan.setAdapter(rcv_adapter);
    }
    private void Anhxa(){
        rcv_binhluan=findViewById(R.id.rcv_binhluan_tong);
        tv_binhluan_tong=findViewById(R.id.tv_binhluan_tong);
    }
}
