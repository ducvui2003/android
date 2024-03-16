package com.example.truyenapp.admin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.view.adapter.admin.QLThongKeAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Statistical;

import java.util.ArrayList;

public class QuanLyThongKe extends AppCompatActivity {
    Database db;
    private RecyclerView rcv;
    private QLThongKeAdapter adapter;
    TextView tv_qltk_sltruyen,tv_qltk_slchapter,tv_qltk_slview,tv_qltk_slvote,tv_qltk_slcomment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qlthongke);

        Anhxa();
        db=new Database(this);

        setData();
        recyclerViewQLThongKe();
    }

    private void recyclerViewQLThongKe(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        ArrayList<Statistical> list=db.getListThongKe();
        adapter=new QLThongKeAdapter(this,list,db);
        rcv.setAdapter(adapter);
    }

    private void setData(){
        ArrayList<String> list=db.getTongThongKe();
        tv_qltk_slchapter.setText("Tổng số chapter: "+list.get(1));
        tv_qltk_slcomment.setText("Tổng bình luận: "+list.get(4));
        tv_qltk_slview.setText("Tổng lượt xem: "+list.get(2));
        tv_qltk_slvote.setText("Tổng đánh giá: "+list.get(3));
        tv_qltk_sltruyen.setText("Tổng số truyện: "+list.get(0));
    }

    private void Anhxa() {
        rcv=findViewById(R.id.rcv_quanlythongke);
        tv_qltk_sltruyen=findViewById(R.id.tv_qltk_sltruyen);
        tv_qltk_slchapter=findViewById(R.id.tv_qltk_slchapter);
        tv_qltk_slview=findViewById(R.id.tv_qltk_slview);
        tv_qltk_slvote=findViewById(R.id.tv_qltk_slvote);
        tv_qltk_slcomment=findViewById(R.id.tv_qltk_slcomment);
    }
}
