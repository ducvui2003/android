package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.BinhLuan;
import com.example.truyenapp.model.Truyen;

public class ShowThongTinBinhLuan extends AppCompatActivity {
    ImageView img;
    TextView tv_id,tv_email,tv_noidung,tv_ngaydang,tv_trangthai,tv_tentruyen,tv_tenchapter;
    Database db;
    BinhLuan binhLuan;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showthongtinbinhluan);

        Anhxa();
        db=new Database(this);
        Intent intent=getIntent();
        id=intent.getIntExtra("id_binhluan",0);
        binhLuan=db.getThongTinBinhLuan(id);

        setData();
    }

    private void setData(){

        int idtruyen=db.getIdTruyen(binhLuan.getIdchapter());
        Truyen truyen=db.getTruyenById(idtruyen);

        Glide.with(this).load(truyen.getLinkhanh()).into(img);
        tv_tentruyen.setText(truyen.getTentruyen());
        tv_tenchapter.setText(db.getTenChapter(binhLuan.getIdchapter()));
        tv_id.setText(""+binhLuan.getId());
        tv_email.setText(db.getEmail(binhLuan.getIdtaikhoan()));
        tv_noidung.setText(binhLuan.getNoidung());
        tv_ngaydang.setText(binhLuan.getNgaydang());
        int trangthai=binhLuan.getTrangthai();
        if(trangthai==1){
            tv_trangthai.setText("Hoạt động");
        }else {
            tv_trangthai.setText("Bị khóa");
        }
    }

    private void Anhxa(){
        img=findViewById(R.id.img_qlbl);
        tv_email=findViewById(R.id.tv_qlbl_email);
        tv_id=findViewById(R.id.tv_qlbl_id);
        tv_noidung=findViewById(R.id.tv_qlbl_noidung);
        tv_ngaydang=findViewById(R.id.tv_qlbl_ngaydang);
        tv_trangthai=findViewById(R.id.tv_qlbl_trangthai);
        tv_tentruyen=findViewById(R.id.tv_qlbl_tentruyen);
        tv_tenchapter=findViewById(R.id.tv_qlbl_tenchapter);
    }
}
