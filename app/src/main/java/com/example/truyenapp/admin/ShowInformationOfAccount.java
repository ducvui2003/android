package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.model.Account;

public class ShowInformationOfAccount extends AppCompatActivity {
    private ImageView img;
    private TextView tvId, tvEmail,  tvTen, tvPhone, tvStatus, tvPoint;
    private Account account;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information_account);
        init();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        account = (Account) getIntent().getSerializableExtra("account");
        if (account != null) {
            setData();
        }
    }

    private void setData() {
        String linkImage = account.getLinkImage();
        if (linkImage != null) {
            Glide.with(this).load(linkImage).into(img);
        } else {
            img.setImageResource(R.drawable.logo);
        }
        tvTen.setText(account.getFullName());
        int status = account.getStatus();
        if (status != 1) {
            tvStatus.setText("Hoạt động");
        } else {
            tvStatus.setText("Bị khóa");
        }
        tvId.setText("" + account.getId());
        tvPhone.setText(account.getPhone());
        tvPoint.setText("" + account.getPoint());
        tvEmail.setText(account.getEmail());
    }

    private void init() {
        img = findViewById(R.id.img_qltk);
        tvPhone = findViewById(R.id.tv_qltk_dienthoai);
        tvEmail = findViewById(R.id.tv_qltk_email);
        tvId = findViewById(R.id.tv_qltk_id);
        tvStatus = findViewById(R.id.tv_qltk_trangthai);
        tvTen = findViewById(R.id.tv_qltk_ten);
        tvPoint = findViewById(R.id.tv_qltk_diem);
    }
}
