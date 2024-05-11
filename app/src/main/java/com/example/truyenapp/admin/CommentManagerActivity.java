package com.example.truyenapp.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.view.adapter.admin.QLBinhLuanAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Comment;

import java.util.ArrayList;

public class CommentManagerActivity extends AppCompatActivity {
    Database db;
    private RecyclerView rcv;
    private QLBinhLuanAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlybinhluan);

        init();
        db=new Database(this);

        handleRecyclerView();
    }

    private void handleRecyclerView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        ArrayList<Comment> list=db.getListBinhLuan();
        adapter=new QLBinhLuanAdapter(this,list,db);
        rcv.setAdapter(adapter);
    }

    private void init() {
        rcv=findViewById(R.id.rcv_quanlybinhluan);
    }

}
