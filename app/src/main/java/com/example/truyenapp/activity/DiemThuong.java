package com.example.truyenapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.TaiKhoan;

public class DiemThuong extends AppCompatActivity implements View.OnClickListener{

    LinearLayout ll_cuahang,ll_lichsu;
    Button bt_diemdanh;
    Database db;
    String email;
    TaiKhoan taiKhoan;
    TextView tv_diemtichluy,tv_songaydd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diemthuong);
        db=new Database(this);
        Anhxa();

        Intent i = getIntent();
        email=i.getStringExtra("email");

        taiKhoan = db.getTaiKhoan(email);

        setData();
        setOnClickListener();
    };

    private void Anhxa(){
        ll_cuahang=findViewById(R.id.ll_cuahang);
        ll_lichsu=findViewById(R.id.ll_lichsu);
        bt_diemdanh=findViewById(R.id.bt_diemdanh);
        tv_diemtichluy=findViewById(R.id.tv_diemtichluy);
        tv_songaydd=findViewById(R.id.tv_songaydd);
    }

    private void setOnClickListener(){
        ll_cuahang.setOnClickListener(this);
        bt_diemdanh.setOnClickListener(this);
        ll_lichsu.setOnClickListener(this);
    }

    private void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }

    private void setData(){
        if(db.getThuHienTai(taiKhoan)!=0){
            tv_songaydd.setText( db.getThuHienTai(taiKhoan)+" ngày liên tiếp");
        }else{
            tv_songaydd.setText( db.getThu(taiKhoan)+" ngày liên tiếp");
        }
        tv_diemtichluy.setText(""+taiKhoan.getDiemthuong());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_cuahang:
                Intent intent = new Intent(this, CuaHang.class);
                intent.putExtra("email",email);
                startActivity(intent);
                break;
            case R.id.bt_diemdanh:
            {
                    Boolean checkDiemDanh = db.checkDiemDanh(taiKhoan);
                    if (checkDiemDanh == false) {
                        int thu = db.getThu(taiKhoan);
                        if (thu == 2) {
                            Boolean diemdanh = db.updateDiemThuong(taiKhoan, 10);
                            Boolean capnhat = db.insertDiemThuong(taiKhoan.getId(), 10, thu + 1);
                            if (diemdanh == true && capnhat == true) {
                                Toast.makeText(this, "Điểm danh thành công! +10 điểm", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Xảy ra lỗi, Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (thu == 6) {
                            Boolean diemdanh = db.updateDiemThuong(taiKhoan, 15);
                            Boolean capnhat = db.insertDiemThuong(taiKhoan.getId(), 15, thu + 1);
                            if (diemdanh == true && capnhat == true) {
                                Toast.makeText(this, "Điểm danh thành công! +15 điểm", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Xảy ra lỗi, Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (thu == 7) {
                            Boolean diemdanh = db.updateDiemThuong(taiKhoan, 5);
                            Boolean capnhat = db.insertDiemThuong(taiKhoan.getId(), 5, 1);
                            if (diemdanh == true && capnhat == true) {
                                Toast.makeText(this, "Điểm danh thành công! +5 điểm", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Xảy ra lỗi, Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Boolean diemdanh = db.updateDiemThuong(taiKhoan, 5);
                            Boolean capnhat = db.insertDiemThuong(taiKhoan.getId(), 5, thu + 1);
                            if (diemdanh == true && capnhat == true) {
                                Toast.makeText(this, "Điểm danh thành công! +5 điểm", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Xảy ra lỗi, Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        reload();
                    } else {
                        Toast.makeText(this, "Hôm nay bạn đã điểm danh, chờ đến ngày mai nhé!", Toast.LENGTH_SHORT).show();
                    }
                break;
            }
            case R.id.ll_lichsu:
                Intent intent1 = new Intent(this, LichSuNhanDiem.class);
                intent1.putExtra("email",email);
                startActivity(intent1);
                break;
        }
    }
}
