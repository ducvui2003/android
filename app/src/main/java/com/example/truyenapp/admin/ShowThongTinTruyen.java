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
import com.example.truyenapp.adapter.AdapterAdmin.QLChapterAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Chapter;
import com.example.truyenapp.model.Truyen;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ShowThongTinTruyen extends AppCompatActivity implements View.OnClickListener {

    ImageView img_truyen,img_new;
    TextView tv_id;
    EditText edt_tacgia,edt_mota,edt_theloai,edt_linkanh,edt_trangthai,edt_tentruyen,edt_tenchapter_newchapter;
    Button bt_chinhsua,bt_them,bt_huy,bt_xacnhantruyen,bt_huychinhsuatruyen;
    Database db;
    Truyen truyen;
    int id;
    CardView cv_themchapter;
    private RecyclerView rcv;
    private QLChapterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showthongtintruyen);

        Anhxa();
        db=new Database(this);
        Intent intent=getIntent();
        id=intent.getIntExtra("id_truyen",1);
        truyen=db.getTruyenById(id);

        setEnable(0);
        setData();
        recyclerViewQLChapter();
        setOnClickListener();
    }

    private void recyclerViewQLChapter() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        String lenhSQlite="select * from chapter where idtruyen="+id;
        ArrayList<Chapter> list=db.getChapter(lenhSQlite);
        adapter=new QLChapterAdapter(this,list,db);
        rcv.setAdapter(adapter);
    }

    private void setData(){
        Glide.with(this).load(truyen.getLinkhanh()).into(img_truyen);
        edt_tentruyen.setText(truyen.getTentruyen());
        edt_tacgia.setText(truyen.getTacgia());
        edt_mota.setText(truyen.getMota());
        edt_theloai.setText(truyen.getTheloai());
        edt_trangthai.setText(""+truyen.getTrangthai());
        tv_id.setText(""+truyen.getId());
        edt_linkanh.setText(truyen.getLinkhanh());
    }

    private void setOnClickListener(){
        img_new.setOnClickListener(this);
        bt_them.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
        bt_chinhsua.setOnClickListener(this);
        bt_huychinhsuatruyen.setOnClickListener(this);
        bt_xacnhantruyen.setOnClickListener(this);

    }

    private void Anhxa() {
        img_new=findViewById(R.id.img_newchapter);
        img_truyen=findViewById(R.id.img_qlt);
        bt_chinhsua=findViewById(R.id.bt_chinhsuatruyen);
        bt_huy=findViewById(R.id.bt_huy_newchapter);
        bt_them=findViewById(R.id.bt_them_newchapter);
        tv_id=findViewById(R.id.tv_qlt_id);
        edt_tacgia=findViewById(R.id.edt_qlt_tacgia);
        edt_mota=findViewById(R.id.edt_qlt_mota);
        edt_theloai=findViewById(R.id.edt_qlt_theloai);
        edt_linkanh=findViewById(R.id.edt_qlt_linkanh);
        edt_trangthai=findViewById(R.id.edt_qlt_trangthai);
        edt_tentruyen=findViewById(R.id.edt_qlt_tentruyen);
        cv_themchapter=findViewById(R.id.cv_themchapter);
        bt_xacnhantruyen=findViewById(R.id.bt_xacnhantruyen);
        bt_huychinhsuatruyen=findViewById(R.id.bt_huychinhsuatruyen);
        edt_tenchapter_newchapter=findViewById(R.id.edt_tenchapter_newchapter);
        rcv=findViewById(R.id.rcv_quanlychapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_newchapter:
                cv_themchapter.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_chinhsuatruyen:
                setEnable(1);
                break;
            case R.id.bt_huychinhsuatruyen:
                setEnable(0);
                break;
            case R.id.bt_xacnhantruyen:
                String tentruyen=edt_tentruyen.getText().toString();
                String tacgia=edt_tacgia.getText().toString();
                String mota=edt_mota.getText().toString();
                String theloai=edt_theloai.getText().toString();
                String linkanh=edt_linkanh.getText().toString();
                String trangthai=edt_trangthai.getText().toString();
                int tt=Integer.parseInt(trangthai);
                String key_search=removeAccent(tentruyen).trim();
                if(!tentruyen.isEmpty() && !tacgia.isEmpty() && !mota.isEmpty() && !theloai.isEmpty() && !linkanh.isEmpty() && !trangthai.isEmpty()){
                    if(tt==1 || tt==0){
                        db.updateTruyen(truyen.getId(),tentruyen,tacgia,mota,theloai,linkanh,tt,key_search);
                        Toast.makeText(this,"Đã cập nhật thông tin truyện",Toast.LENGTH_SHORT).show();
                        reload();
                    }else {
                        Toast.makeText(this,"Trạng thái = 0 hoặc = 1",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"Vui lòng nhập đầy đủ các trường",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_them_newchapter:
                String tenchapter=edt_tenchapter_newchapter.getText().toString().trim();
                String kttenchapter=removeAccent(tenchapter);
                if (checkTenChapter(kttenchapter)==1){
                    Toast.makeText(this,"Tên chapter đã tồn tại",Toast.LENGTH_SHORT).show();
                }else {
                    db.insertChapter(tenchapter,id);
                    Toast.makeText(this,"Thêm chapter thành công",Toast.LENGTH_SHORT).show();
                    recyclerViewQLChapter();
                    cv_themchapter.setVisibility(View.GONE);
                }
                break;
            case R.id.bt_huy_newchapter:
                cv_themchapter.setVisibility(View.GONE);
                break;
        }
    }
    private void setEnable(int i){
        if(i==1){
            edt_linkanh.setEnabled(true);
            edt_trangthai.setEnabled(true);
            edt_tentruyen.setEnabled(true);
            edt_mota.setEnabled(true);
            edt_theloai.setEnabled(true);
            edt_tacgia.setEnabled(true);
            bt_chinhsua.setVisibility(View.GONE);
            bt_huychinhsuatruyen.setVisibility(View.VISIBLE);
            bt_xacnhantruyen.setVisibility(View.VISIBLE);
        }else {
            edt_linkanh.setEnabled(false);
            edt_trangthai.setEnabled(false);
            edt_tentruyen.setEnabled(false);
            edt_mota.setEnabled(false);
            edt_theloai.setEnabled(false);
            edt_tacgia.setEnabled(false);
            bt_chinhsua.setVisibility(View.VISIBLE);
            bt_huychinhsuatruyen.setVisibility(View.GONE);
            bt_xacnhantruyen.setVisibility(View.GONE);
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

    public int checkTenChapter(String tentruyen){
        ArrayList<String> listtenchapter=db.listTenChapter();
        for(int i=0;i<listtenchapter.size();i++){
            if(tentruyen.equals(removeAccent(listtenchapter.get(i)).trim())){
                return 1;
            }
        }
        return 0;
    }
}
