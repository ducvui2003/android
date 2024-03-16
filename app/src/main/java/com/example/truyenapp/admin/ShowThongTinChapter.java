package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.model.Story;
import com.example.truyenapp.view.adapter.admin.QLNDChapterAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Chapter;
import com.example.truyenapp.model.ContentOfChapter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ShowThongTinChapter extends AppCompatActivity implements View.OnClickListener{
    ImageView img_truyen,img_new;
    TextView tv_id,tv_danhgia,tv_luotxem,tv_ngaydang,tv_tentruyen;
    EditText edt_tenchapter,edt_linkanh;
    Button bt_chinhsua,bt_them,bt_huy,bt_xacnhanchaper,bt_huychinhsuachapter;
    Database db;
    Story story;
    Chapter chapter;
    int id;
    CardView cv_themndchapter;
    private RecyclerView rcv;
    private QLNDChapterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showthongtinchapter);

        Anhxa();
        db=new Database(this);
        Intent intent=getIntent();
        id=intent.getIntExtra("id_chapter",1);
        chapter=db.getOneChapter(id);
        story =db.getTruyenById(chapter.getIdtruyen());

        setEnable(0);
        setData();
        recyclerViewQLNDChapter();
        setOnClickListener();
    }

    private void recyclerViewQLNDChapter() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        String lenhSQlite="select * from noidungchapter where idchapter="+id;
        ArrayList<ContentOfChapter> list=db.getNoiDungChapter(lenhSQlite);
        adapter=new QLNDChapterAdapter(this,list,db);
        rcv.setAdapter(adapter);
    }

    private void setData(){
        Glide.with(this).load(story.getLinkImage()).into(img_truyen);
        tv_id.setText(""+chapter.getId());
        tv_tentruyen.setText(story.getNameStory());
        edt_tenchapter.setText(chapter.getTenchapter());
        tv_danhgia.setText(""+chapter.getDanhgia());
        tv_luotxem.setText(""+chapter.getSoluotxem());
        tv_ngaydang.setText(chapter.getNgaydang());
    }

    private void setOnClickListener(){
        img_new.setOnClickListener(this);
        bt_them.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
        bt_chinhsua.setOnClickListener(this);
        bt_huychinhsuachapter.setOnClickListener(this);
        bt_xacnhanchaper.setOnClickListener(this);

    }

    private void Anhxa() {
        img_new=findViewById(R.id.img_newnoidungchapter);
        img_truyen=findViewById(R.id.img_qlc);
        bt_chinhsua=findViewById(R.id.bt_chinhsuachapter);
        bt_huy=findViewById(R.id.bt_huy_newndchapter);
        bt_them=findViewById(R.id.bt_them_newndchapter);
        tv_id=findViewById(R.id.tv_qlc_id);
        edt_tenchapter=findViewById(R.id.edt_qlc_tenchapter);
        edt_linkanh=findViewById(R.id.edt_linkanh_newndchapter);
        cv_themndchapter=findViewById(R.id.cv_themndchapter);
        bt_xacnhanchaper=findViewById(R.id.bt_xacnhanchapter);
        bt_huychinhsuachapter=findViewById(R.id.bt_huychinhsuachapter);
        rcv=findViewById(R.id.rcv_quanlynoidungchapter);
        tv_danhgia=findViewById(R.id.tv_qlc_danhgia);
        tv_luotxem=findViewById(R.id.tv_qlc_luotxem);
        tv_ngaydang=findViewById(R.id.tv_qlc_ngaydang);
        tv_tentruyen=findViewById(R.id.tv_qlc_tentruyen);
        rcv=findViewById(R.id.rcv_quanlynoidungchapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_newnoidungchapter:
                cv_themndchapter.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_chinhsuachapter:
                setEnable(1);
                break;
            case R.id.bt_huychinhsuachapter:
                setEnable(0);
                break;
            case R.id.bt_xacnhanchapter:
                String tenchapter=edt_tenchapter.getText().toString().trim();
                if(!tenchapter.isEmpty()){
                    db.updateChapter(id,tenchapter);
                        Toast.makeText(this,"Đã cập nhật thông tin chapter",Toast.LENGTH_SHORT).show();
                        reload();

                }else {
                    Toast.makeText(this,"Vui lòng nhập tên chapter",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_them_newndchapter:
                String linkanh=edt_linkanh.getText().toString().trim();
                if (checkLinkAnh(linkanh)==1){
                    Toast.makeText(this,"Link ảnh đã tồn tại",Toast.LENGTH_SHORT).show();
                }else {
                    db.insertNDChapter(linkanh,id);
                    Toast.makeText(this,"Thêm link ảnh thành công",Toast.LENGTH_SHORT).show();
                    recyclerViewQLNDChapter();
                    cv_themndchapter.setVisibility(View.GONE);
                }
                break;
            case R.id.bt_huy_newndchapter:
                cv_themndchapter.setVisibility(View.GONE);
                break;
        }
    }
    private void setEnable(int i){
        if(i==1){
            edt_tenchapter.setEnabled(true);
            bt_xacnhanchaper.setVisibility(View.VISIBLE);
            bt_huychinhsuachapter.setVisibility(View.VISIBLE);
            bt_chinhsua.setVisibility(View.GONE);
        }else {
            edt_tenchapter.setEnabled(false);
            bt_xacnhanchaper.setVisibility(View.GONE);
            bt_huychinhsuachapter.setVisibility(View.GONE);
            bt_chinhsua.setVisibility(View.VISIBLE);
        }
    }
    public static String removeAccent(String s){
        s=s.toLowerCase();
        s=s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
    private void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }

    public int checkLinkAnh(String linkanh){
        ArrayList<String> listlinkanh=db.getLinkAnhNDC(id);
        for(int i=0;i<listlinkanh.size();i++){
            if(linkanh.equals(removeAccent(listlinkanh.get(i)).trim())){
                return 1;
            }
        }
        return 0;
    }
}
