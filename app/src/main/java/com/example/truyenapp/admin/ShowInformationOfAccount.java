package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;

public class ShowInformationOfAccount extends AppCompatActivity {
    ImageView img;
    TextView tv_id,tv_email,tv_matkhau,tv_ten,tv_dienthoai,tv_trangthai,tv_diem;
    Database db;
    Account account;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showthongtintaikhoan);

        Anhxa();
        db=new Database(this);
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        account =db.getTaiKhoan(email);

        setData();
    }

    private void setData(){
        String linkanh= account.getLinkImage();
        if(linkanh!=null){
            Glide.with(this).load(linkanh).into(img);
        }else {
            img.setImageResource(R.drawable.logo);
        }
        tv_ten.setText(account.getName());
        int trangthai= account.getAccoutType();
        if(trangthai!=2){
            tv_trangthai.setText("Hoạt động");
        }else {
            tv_trangthai.setText("Bị khóa");
        }
        tv_matkhau.setText(account.getPassword());
        tv_id.setText(""+ account.getId());
        tv_dienthoai.setText(account.getPhone());
        tv_diem.setText(""+ account.getRewardPoint());
        tv_email.setText(account.getEmail());
    }

    private void Anhxa(){
        img=findViewById(R.id.img_qltk);
        tv_dienthoai=findViewById(R.id.tv_qltk_dienthoai);
        tv_email=findViewById(R.id.tv_qltk_email);
        tv_id=findViewById(R.id.tv_qltk_id);
        tv_matkhau=findViewById(R.id.tv_qltk_matkhau);
        tv_trangthai=findViewById(R.id.tv_qltk_trangthai);
        tv_ten=findViewById(R.id.tv_qltk_ten);
        tv_diem=findViewById(R.id.tv_qltk_diem);
    }
}
