package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.view.adapter.ShowDanhGiaAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Evaluate;
import com.example.truyenapp.model.Account;

import java.util.ArrayList;

public class ShowDanhGia extends AppCompatActivity {

    Database db;

    private RecyclerView rcv_danhgia;
    private ShowDanhGiaAdapter rcv_adapter;
    Account account;
    String email;
    TextView tv_danhgia_tong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdanhgia);

        db=new Database(this);
        Anhxa();
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        account =db.getTaiKhoan(email);

        int tongdanhgia=db.getTongDanhGia(account.getId());
        tv_danhgia_tong.setText("Tổng đánh giá: "+tongdanhgia);
        recyclerViewDanhGia();
    };

    private void recyclerViewDanhGia(){
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcv_danhgia.setLayoutManager(linearLayoutManager2);
        ArrayList<Evaluate> listEvaluate =db.getShowDanhGia(account.getId());
        rcv_adapter=new ShowDanhGiaAdapter(this, listEvaluate,db);
        rcv_danhgia.setAdapter(rcv_adapter);
    }

    private void Anhxa(){
        rcv_danhgia=findViewById(R.id.rcv_danhgia_tong);
        tv_danhgia_tong=findViewById(R.id.tv_danhgia_tong);
    }
}
