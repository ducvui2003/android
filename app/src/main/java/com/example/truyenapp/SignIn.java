package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.view.activity.HomeActivity;
import com.example.truyenapp.view.activity.Authenticate;
import com.example.truyenapp.database.Database;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    EditText edt_dk_email, edt_dk_pass,edt_dk_nlpass;
    Button bt_dk;
    ImageView imgv_logo;
    RadioButton rb_check;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);

//        Anhxa();
//        setOnClickListener();

        db=new Database(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.change_fragment_sign_in:
                Intent dialog_box = new Intent(this, Authenticate.class);
                startActivity(dialog_box);
                finish();
                break;
            case R.id.imgv_logo:
                Intent dialog_box1 = new Intent(this, HomeActivity.class);
                startActivity(dialog_box1);
                finish();
                break;
            case R.id.button_sign_up:
            {
                String email=edt_dk_email.getText().toString();
                email=removeAccent(email);
                String pass=edt_dk_pass.getText().toString();
                String nlpass=edt_dk_nlpass.getText().toString();

                if(email.length()!=0 && pass.length()!=0 && nlpass.length()!=0){
                    if(validateEmail(email)==false){
                        Toast.makeText(this,"Email không hợp lệ!",Toast.LENGTH_SHORT).show();
                    }
                    else if(vailidatePass(pass)==false){
                        Toast.makeText(this,"Mật khẩu không hợp lệ (ít nhất 8 ký tự phải bao gồm chữ in hoa, chữ số và ký tự đặc biết)",Toast.LENGTH_SHORT).show();
                    }
                    else if(!nlpass.equals(pass)){
                        Toast.makeText(this,"Mật khẩu không trùng nhau",Toast.LENGTH_SHORT).show();
                    }
                    else if(!rb_check.isChecked()){
                        Toast.makeText(this,"Vui lòng đồng ý với các điều khoản!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Boolean checkEmail=db.ckeckEmail(email);
                        if(checkEmail==false && !email.equals("argoncomic@gmail.com")){
                                Boolean insert = db.insertTaikhoan(email,pass);
                                if(insert==true){
                                    Toast.makeText(this,"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(this, Authenticate.class);
                                    intent.putExtra("email",email);
                                    intent.putExtra("pass",edt_dk_pass.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(this,"Đăng ký thất bại!",Toast.LENGTH_SHORT).show();
                                }
                        }else {
                            Toast.makeText(this, "Email đã tồn tại !", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else{
                    if(edt_dk_email.getText().length()==0){
                        Toast.makeText(this,"Vui lòng nhập email",Toast.LENGTH_SHORT).show();
                    }
                    else if(edt_dk_pass.getText().length()==0){
                        Toast.makeText(this,"Vui lòng nhập mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this,"Vui lòng nhập nhập lại mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
        }
    }

    private void setOnClickListener(){
        textView.setOnClickListener(this);
        imgv_logo.setOnClickListener(this);
        bt_dk.setOnClickListener(this);
    }

//    private void Anhxa(){
//        textView = findViewById(R.id.tv_cotk);
//        edt_dk_email=findViewById(R.id.edt_dk_email);
//        edt_dk_pass=findViewById(R.id.edt_dk_pass);
//        edt_dk_nlpass=findViewById(R.id.edt_dk_nlpass);
//        bt_dk=findViewById(R.id.bt_dk);
//        imgv_logo=findViewById(R.id.imgv_logo);
////        rb_check=findViewById(R.id.rb_check);
//
//    }

    public static boolean vailidatePass(String pass) {
        String expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
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
