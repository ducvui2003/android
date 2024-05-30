package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Comment;
import com.example.truyenapp.model.Story;

public class CommentDetailManagerActivity extends AppCompatActivity {
    ImageView img;
    TextView tv_id, tv_email, tv_noidung, tv_ngaydang, tv_trangthai, tv_tentruyen, tv_tenchapter;
    Database db;
    Comment binhLuan;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail_management);

        init();
        db = new Database(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id_binhluan", 0);
        binhLuan = db.getThongTinBinhLuan(id);

        setData();
    }

    private void setData() {
        int idtruyen = db.getIdTruyen(binhLuan.getIdChapter());
        Story story = db.getTruyenById(idtruyen);

        Glide.with(this).load(story.getLinkImage()).into(img);
        tv_tentruyen.setText(story.getNameStory());
        tv_tenchapter.setText(db.getTenChapter(binhLuan.getIdChapter()));
        tv_id.setText("" + binhLuan.getId());
        tv_email.setText(db.getEmail(binhLuan.getIdAccount()));
        tv_noidung.setText(binhLuan.getContent());
        tv_ngaydang.setText(binhLuan.getPostingDay());
        int trangthai = binhLuan.getState();
        if (trangthai == 1) {
            tv_trangthai.setText("Hoạt động");
        } else {
            tv_trangthai.setText("Bị khóa");
        }
    }

    private void init() {
        img = findViewById(R.id.img_qlbl);
        tv_email = findViewById(R.id.tv_qlbl_email);
        tv_id = findViewById(R.id.tv_qlbl_id);
        tv_noidung = findViewById(R.id.tv_qlbl_noidung);
        tv_ngaydang = findViewById(R.id.tv_qlbl_ngaydang);
        tv_trangthai = findViewById(R.id.tv_qlbl_trangthai);
        tv_tentruyen = findViewById(R.id.tv_qlbl_tentruyen);
        tv_tenchapter = findViewById(R.id.tv_qlbl_tenchapter);
    }
}
