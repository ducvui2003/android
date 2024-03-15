package com.example.truyenapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.TaiKhoan;

public class ThongTinTaiKhoan extends AppCompatActivity implements View.OnClickListener{
    ImageView img_tttk;
    TextView tv_tttk_id,tv_tttk_email,tv_tttk_diem,tv_tttk_trangthai;
    EditText edt_tttk_dienthoai,edt_tttk_ten;
    Button bt_chinhsua,bt_xacnhan,bt_huychinhsua;
    Database db;
    String email;
    TaiKhoan taiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintaikhoan);

        Anhxa();
        db=new Database(this);
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        taiKhoan=db.getTaiKhoan(email);

        In_Visible(0);

        setData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        bt_chinhsua.setOnClickListener(this);
        bt_huychinhsua.setOnClickListener(this);
        bt_xacnhan.setOnClickListener(this);
    }

    private void setData() {
        tv_tttk_id.setText(""+taiKhoan.getId());
        tv_tttk_email.setText(taiKhoan.getEmail());
        tv_tttk_diem.setText(""+taiKhoan.getDiemthuong());
        tv_tttk_trangthai.setText("Hoạt động");
        edt_tttk_dienthoai.setText(taiKhoan.getDienthoai());
        edt_tttk_ten.setText(taiKhoan.getHoten());
        String linkanh=taiKhoan.getLinkanh();
        if(linkanh!=null){
            Glide.with(this).load(linkanh).into(img_tttk);
        }else {
            img_tttk.setImageResource(R.drawable.logo);
        }

    }

    private void Anhxa() {
        img_tttk=findViewById(R.id.img_tttk);
        tv_tttk_diem=findViewById(R.id.tv_tttk_diem);
        tv_tttk_email=findViewById(R.id.tv_tttk_email);
        tv_tttk_id=findViewById(R.id.tv_tttk_id);
        tv_tttk_trangthai=findViewById(R.id.tv_tttk_trangthai);
        edt_tttk_dienthoai=findViewById(R.id.edt_tttk_dienthoai);
        edt_tttk_ten=findViewById(R.id.edt_tttk_ten);
        bt_chinhsua=findViewById(R.id.bt_chinhsua);
        bt_huychinhsua=findViewById(R.id.bt_huychinhsua);
        bt_xacnhan=findViewById(R.id.bt_xacnhan);
    }

    private void In_Visible(int i){
        if(i==1){
            edt_tttk_ten.setEnabled(true);
            edt_tttk_dienthoai.setEnabled(true);
            bt_xacnhan.setVisibility(View.VISIBLE);
            bt_huychinhsua.setVisibility(View.VISIBLE);
            bt_chinhsua.setVisibility(View.GONE);
        }else {
            edt_tttk_ten.setEnabled(false);
            edt_tttk_dienthoai.setEnabled(false);
            bt_xacnhan.setVisibility(View.GONE);
            bt_huychinhsua.setVisibility(View.GONE);
            bt_chinhsua.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_chinhsua:
                In_Visible(1);
                break;
            case R.id.bt_huychinhsua:
                In_Visible(0);
                break;
            case R.id.bt_xacnhan:
                String ten=edt_tttk_ten.getText().toString();
                String dienthoai=edt_tttk_dienthoai.getText().toString();
                if(ten.isEmpty()){
                    Toast.makeText(this,"Họ tên đang trống!",Toast.LENGTH_SHORT).show();
                }else if(dienthoai.isEmpty()){
                    Toast.makeText(this,"Điện thoại đang trống!",Toast.LENGTH_SHORT).show();
                }else {
                    db.updateThongTin(ten,dienthoai,taiKhoan.getId());
                    Toast.makeText(this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    In_Visible(0);
                }
                break;
        }
    }
}
