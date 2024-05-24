package com.example.truyenapp.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.view.adapter.admin.CommentManagerAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentManagerActivity extends AppCompatActivity {
    Database db;
    private RecyclerView rcv;
    private CommentManagerAdapter adapter;

    List<Comment> comments = new ArrayList<>();


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
        adapter=new CommentManagerAdapter(this,list,db);
        rcv.setAdapter(adapter);
    }

    private void init() {
        rcv=findViewById(R.id.rcv_quanlybinhluan);
    }

    private void callAPI(){

    }
}
