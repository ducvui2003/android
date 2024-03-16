package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.view.adapter.LSNhanDiemAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.model.RewardPoints;

import java.util.ArrayList;

public class LichSuNhanDiem extends AppCompatActivity {
    String email;
    Account account;
    Database db;

    public RecyclerView rcv;
    public LSNhanDiemAdapter rcv_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsunhandiem);
        db=new Database(this);
        Anhxa();

        Intent i = getIntent();
        email=i.getStringExtra("email");
        account = db.getTaiKhoan(email);

        recyclerViewDiemThuong();
    };

    public void recyclerViewDiemThuong() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        ArrayList<RewardPoints> rewardPoints =db.getDiemThuong(account.getId());
        rcv_adapter=new LSNhanDiemAdapter(this, rewardPoints);
        rcv.setAdapter(rcv_adapter);
    }

    public void Anhxa(){
        rcv=findViewById(R.id.rcv_lsnd);
    }
}
