package com.example.truyenapp.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.adapter.AdapterAdmin.QLBinhLuanAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.BinhLuan;

import java.util.ArrayList;

public class QuanLyBinhLuan extends AppCompatActivity {
    Database db;
    private RecyclerView rcv;
    private QLBinhLuanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlybinhluan);

        Anhxa();
        db=new Database(this);

        recyclerViewQLBinhLuan();
    }

    private void recyclerViewQLBinhLuan(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        ArrayList<BinhLuan> list=db.getListBinhLuan();
        adapter=new QLBinhLuanAdapter(this,list,db);
        rcv.setAdapter(adapter);
    }

    private void Anhxa() {
        rcv=findViewById(R.id.rcv_quanlybinhluan);
    }
}
