package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.model.Story;
import com.example.truyenapp.view.adapter.admin.QLTruyenAdapter;
import com.example.truyenapp.database.Database;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class QuanLyTruyen extends AppCompatActivity implements View.OnClickListener{
    Database db;
    private RecyclerView rcv;
    private QLTruyenAdapter adapter;
    ImageView img_newtruyen;
    Button bt_them,bt_huy;
    EditText edt_tentruyen,edt_theloai,edt_tacgia,edt_mota,edt_linkanh;
    CardView cv_themtruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlytruyen);

        Anhxa();
        db=new Database(this);

        setOnClickListener();
        recyclerViewQLTruyen();
    }

    private void recyclerViewQLTruyen(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        ArrayList<Story> list=db.getListTruyen();
        adapter=new QLTruyenAdapter(this,list,db);
        rcv.setAdapter(adapter);
    }

    private void Anhxa() {
        rcv=findViewById(R.id.rcv_quanlytruyen);
        img_newtruyen=findViewById(R.id.img_newtruyen);
        bt_huy=findViewById(R.id.bt_huy_newtruyen);
        bt_them=findViewById(R.id.bt_them_newtruyen);
        edt_tentruyen=findViewById(R.id.edt_tentruyen_newtruyen);
        edt_theloai=findViewById(R.id.edt_theloai_newtruyen);
        edt_tacgia=findViewById(R.id.edt_tg_newtruyen);
        edt_mota=findViewById(R.id.edt_mota_newtruyen);
        edt_linkanh=findViewById(R.id.edt_linkanh_newtruyen);
        cv_themtruyen=findViewById(R.id.cv_themtruyen);
    }
    private void setOnClickListener(){
        bt_them.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
        img_newtruyen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_newtruyen:
                cv_themtruyen.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_huy_newtruyen:
                cv_themtruyen.setVisibility(View.GONE);
                break;
            case R.id.bt_them_newtruyen:
                String tentruyen=edt_tentruyen.getText().toString().trim();
                String key_search=removeAccent(tentruyen).trim();
                String tacgia=edt_tacgia.getText().toString();
                String theloai=edt_theloai.getText().toString();
                String mota=edt_mota.getText().toString();
                String linkanh=edt_linkanh.getText().toString();

                if(!tentruyen.isEmpty() && !tacgia.isEmpty() && !theloai.isEmpty() && !mota.isEmpty() && !linkanh.isEmpty()){
                    if(checkTenTruyen(tentruyen)==1){
                        Toast.makeText(this,"Tên truyện đã tồn tại trong hệ thống",Toast.LENGTH_SHORT).show();
                    }else {
                        db.insertTruyen(tentruyen,tacgia,mota,theloai,linkanh,key_search);
                        Toast.makeText(this,"Thêm truyện thành công",Toast.LENGTH_SHORT).show();
                        recyclerViewQLTruyen();
                        cv_themtruyen.setVisibility(View.GONE);
                    }
                }else if(tentruyen.isEmpty()){
                    Toast.makeText(this,"Vui lòng nhập tên truyện",Toast.LENGTH_SHORT).show();
                }else if(tacgia.isEmpty()){
                    Toast.makeText(this,"Vui lòng nhập tác giả",Toast.LENGTH_SHORT).show();
                }else if(mota.isEmpty()){
                    Toast.makeText(this,"Vui lòng nhập mô tả",Toast.LENGTH_SHORT).show();
                }else if(theloai.isEmpty()){
                    Toast.makeText(this,"Vui lòng nhập thể loại",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Vui lòng nhập link ảnh",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public static String removeAccent(String s){
        s=s.toLowerCase();
        s=s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public int checkTenTruyen(String tentruyen){
        ArrayList<String> listtentruyen=db.listTenTruyen();
        for(int i=0;i<listtentruyen.size();i++){
            if(tentruyen.equals(removeAccent(listtentruyen.get(i)).trim())){
                return 1;
            }
        }
        return 0;
    }

    private void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }
}
