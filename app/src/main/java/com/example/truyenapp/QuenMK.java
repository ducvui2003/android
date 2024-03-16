package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.SendMailTask;
import com.example.truyenapp.view.activity.HomeActivity;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class QuenMK extends AppCompatActivity implements View.OnClickListener{

    Button bt_qmk;
    EditText edt_emailxn;
    ImageView imgv_logo;
    Database db;

    public int ranNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmk);


        Anhxa();
        setOnClickListener();

        db=new Database(this);
    }

    private void setOnClickListener(){
        bt_qmk.setOnClickListener(this);
        imgv_logo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.bt_qmk: {
                String email=edt_emailxn.getText().toString();

                if (email.length() != 0) {
                    if(validateEmail(email)==false){
                        Toast.makeText(this,"Email không hợp lệ!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean checkEmail=db.ckeckEmail(email);
                        if(checkEmail==true ){
                            int kt=db.checkTrangThai(email);
                            if(kt==1){
                                Random Rand = new Random();
                                ranNum = Rand.nextInt((999999 - 100000) + 1) + 100000;
                                //ranNum = ThreadLocalRandom.current().nextInt(100000,999999);
                                List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));
                                new SendMailTask(this).execute("argoncomic@gmail.com",
                                        "trong02456789", toEmailList, "Quên mật khẩu", "Mã code: " + ranNum);
                                Toast.makeText(this, "Đã gửi", Toast.LENGTH_SHORT).show();
                                Intent dialog_box = new Intent(this, MKMoi.class);
                                dialog_box.putExtra("maxn",ranNum);
                                dialog_box.putExtra("emailxn", edt_emailxn.getText().toString());
                                dialog_box.putExtra("kt",1);
                                startActivity(dialog_box);
                            }else if(kt==0){
                                Toast.makeText(this,"Tài khoản bị khóa",Toast.LENGTH_SHORT).show();
                            }else {Toast.makeText(this,"Tài khoản đang bị tạm khóa chức năng này",Toast.LENGTH_SHORT).show();}
                        }else{
                            Toast.makeText(this,"Email không tồn tại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Vui lòng nhập Email",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.imgv_logo:
                Intent dialog_box1 = new Intent(this, HomeActivity.class);
                startActivity(dialog_box1);
                finish();
                break;
        }
    }

    private void Anhxa(){
        bt_qmk=findViewById(R.id.bt_qmk);
        edt_emailxn=findViewById(R.id.edt_emailxn);
        imgv_logo=findViewById(R.id.imgv_logo);
    }

    private boolean validateEmail(String email){
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }else {
            return false;
        }
    }

    public static String removeAccent(String s){
        s=s.toLowerCase();
        s=s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
