package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.ThongKe;
import com.example.truyenapp.model.Truyen;

public class ShowThongTinThongKe extends AppCompatActivity {
    ImageView img;
    TextView tv_qltk_tentruyen,tv_qltk_id,tv_qltk_chapter,tv_qltk_danhgia,tv_qltk_tongdanhgia,tv_qltk_tongluotxem,tv_qltk_tongbinhluan;
    Database db;
    ThongKe thongKe;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showthongtinthongke);

        Anhxa();
        db=new Database(this);
        Intent intent=getIntent();
        id=intent.getIntExtra("id_thongke",1);
        thongKe=db.getThongKeById(id);

        setData();
    }

    private void setData(){
        Truyen truyen=db.getTruyenById(thongKe.getIdtruyen());

        Glide.with(this).load(truyen.getLinkhanh()).into(img);
        tv_qltk_tentruyen.setText(truyen.getTentruyen());
        tv_qltk_id.setText(""+thongKe.getId());
        tv_qltk_danhgia.setText(""+db.TBDanhGiaTruyen(thongKe.getIdtruyen()));
        tv_qltk_tongdanhgia.setText(""+db.getTongDanhGiaTruyen(thongKe.getIdtruyen()));
        tv_qltk_chapter.setText(""+db.getSumChapter(thongKe.getIdtruyen()));
        tv_qltk_tongluotxem.setText(""+db.TongLuotXemTruyen(thongKe.getIdtruyen()));
        tv_qltk_tongbinhluan.setText(""+db.getTongBinhLuanTruyen(thongKe.getIdtruyen()));

    }

    private void Anhxa(){
        img=findViewById(R.id.img_qltk);
        tv_qltk_tentruyen=findViewById(R.id.tv_qltk_tentruyen);
        tv_qltk_id=findViewById(R.id.tv_qltk_id);
        tv_qltk_chapter=findViewById(R.id.tv_qltk_chapter);
        tv_qltk_danhgia=findViewById(R.id.tv_qltk_danhgia);
        tv_qltk_tongdanhgia=findViewById(R.id.tv_qltk_tongdanhgia);
        tv_qltk_tongluotxem=findViewById(R.id.tv_qltk_tongluotxem);
        tv_qltk_tongbinhluan=findViewById(R.id.tv_qltk_tongbinhluan);

    }
}
