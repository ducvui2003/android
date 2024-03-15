package com.example.truyenapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.CTTruyen;
import com.example.truyenapp.R;
import com.example.truyenapp.adapter.BinhLuanAdapter;
import com.example.truyenapp.adapter.DocChapterAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.BinhLuan;
import com.example.truyenapp.model.Chapter;
import com.example.truyenapp.model.NoiDungChapter;
import com.example.truyenapp.model.TaiKhoan;

import java.util.ArrayList;

public class DocChapter extends AppCompatActivity implements View.OnClickListener{
    Database db;

    private RecyclerView rcv,rcv_binhluan;
    private DocChapterAdapter rcv_adapter;
    private BinhLuanAdapter rcv_binhluanadapter;

    public int id_chapter,id_truyen;
    TextView tv_tenchapter,tv_sosaochapter;
    ImageView img_backdoctruyen, img_pre, img_next;
    Button bt_danhgia, bt_binhluan;
    EditText edt_binhluan;
    TaiKhoan taiKhoan;
    String email;
    RatingBar rtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docchapter);

        db=new Database(this);
        Anhxa();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        Intent intent=getIntent();
        id_chapter=intent.getIntExtra("id_chapter",0);
        id_truyen=intent.getIntExtra("id_truyen",0);
        email=intent.getStringExtra("email");
        taiKhoan=db.getTaiKhoan(email);

        if(id_chapter!=0){
            String lenhSqlite_chapter="select * from chapter where id="+id_chapter;
            ArrayList<Chapter> Chapters=db.getChapter(lenhSqlite_chapter);
            Chapter chapter=Chapters.get(0);
            tv_tenchapter.setText(chapter.getTenchapter());
            db.updateLuotXemChapter(chapter);
        }
        String lenhSqlite_docchapter="select * from noidungchapter where idchapter="+id_chapter;
        ArrayList<NoiDungChapter> noiDungChapters=db.getNoiDungChapter(lenhSqlite_docchapter);
        rcv_adapter=new DocChapterAdapter(noiDungChapters,this);
        rcv.setAdapter(rcv_adapter);

        recyclerViewBinhLuan();
        check();
        setData();
        setOnClickListener();
    };

    private void recyclerViewBinhLuan(){
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_binhluan.setLayoutManager(linearLayoutManager2);
        ArrayList<BinhLuan> listBinhLuan=db.getListBinhLuanChapter(id_chapter);
        rcv_binhluanadapter=new BinhLuanAdapter(this,listBinhLuan,db,taiKhoan);
        rcv_binhluan.setAdapter(rcv_binhluanadapter);
    }

    private void check(){
        Boolean kt=db.checkTruyenDaDoc(taiKhoan.getId(),id_truyen);
        if(kt==true){
            int idchapterdadoc=db.getIdChapterTruyenDaDoc(taiKhoan.getId(),id_truyen);
            if(idchapterdadoc!=id_chapter){
                int id=db.getIdLichSuDocTruyen(taiKhoan.getId(),idchapterdadoc);
                db.updateTruyenDaDoc(id_chapter,id);
            }
        }
        else {
            db.insertTruyenDaDoc(taiKhoan.getId(),id_chapter);
        }
    }

    private void Anhxa(){
        rcv=findViewById(R.id.rcv_docchapter);
        rcv_binhluan=findViewById(R.id.rcv_binhluan);
        tv_tenchapter=findViewById(R.id.tv_tenchapter);
        img_backdoctruyen=findViewById(R.id.img_backdoctruyen);
        img_next=findViewById(R.id.img_next);
        img_pre=findViewById(R.id.img_pre);
        edt_binhluan=findViewById(R.id.edt_binhluan);
        bt_binhluan=findViewById(R.id.bt_binhluan);
        bt_danhgia=findViewById(R.id.bt_danhgia);
        rtb=findViewById(R.id.rtb);
        tv_sosaochapter=findViewById(R.id.tv_sosaochapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_pre:
                if(id_chapter==db.getMinIdChapter(id_truyen)){
                    Toast.makeText(this,"Bạn đang ở Chapter đầu tiên!",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(this, DocChapter.class);
                    intent.putExtra("email",email);
                    intent.putExtra("id_chapter",id_chapter-1);
                    intent.putExtra("id_truyen",id_truyen);
                    startActivity(intent);
                }
                break;
            case R.id.img_next:
                if(id_chapter==db.getMaxIdChapter(id_truyen)){
                    Toast.makeText(this,"Bạn đang ở Chapter cuối",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent1 = new Intent(this, DocChapter.class);
                    intent1.putExtra("email",email);;
                    intent1.putExtra("id_chapter",id_chapter+1);
                    intent1.putExtra("id_truyen",id_truyen);
                    startActivity(intent1);
                }
                break;
            case R.id.img_backdoctruyen:
                Intent intent2 = new Intent(this, CTTruyen.class);
                intent2.putExtra("email",email);;
                intent2.putExtra("id_truyen",id_truyen);
                startActivity(intent2);
                finish();
                break;
            case R.id.bt_binhluan:
                if(edt_binhluan.getText().length()!=0){
                    db.insertBinhLuan(id_chapter,taiKhoan.getId(),edt_binhluan.getText().toString());
                    edt_binhluan.setText("");
                    recyclerViewBinhLuan();
                }else{
                    Toast.makeText(this,"Vui lòng nhập bình luận!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_danhgia:
                Boolean kt=db.checkDanhGia(taiKhoan.getId(),id_chapter);
                float sosao=rtb.getRating();
                if(kt==true){
                    db.updateDanhGia(id_chapter,taiKhoan.getId(),sosao);
                    setData();
                }else{
                    db.insertDanhGia(id_chapter,taiKhoan.getId(),sosao);
                    setData();
                }
                break;
        }
    }

    private void setData(){
        float sosaochapter=db.getSoSaoChapter(id_chapter);
        tv_sosaochapter.setText(""+sosaochapter);
    }

    private void setOnClickListener(){
        img_backdoctruyen.setOnClickListener(this);
        img_pre.setOnClickListener(this);
        img_next.setOnClickListener(this);
        bt_binhluan.setOnClickListener(this);
        bt_danhgia.setOnClickListener(this);
    }
}
