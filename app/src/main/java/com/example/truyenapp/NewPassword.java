package com.example.truyenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyenapp.view.activity.HomeActivity;
import com.example.truyenapp.view.activity.Signin;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.SendMailTask;
import com.example.truyenapp.model.Account;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewPassword extends AppCompatActivity implements View.OnClickListener{
    EditText edt_maopt, edt_mkm, edt_nlmkm;
    Button bt_xnmkm,bt_glmxn;
    ImageView imgv_logo;
    TextView tv_emailxn;
    Account account;

    Database db;

    public int ranNum;
    public String maxn;
    public String email;
    public int kt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matkhaumoi);

        Anhxa();
        db=new Database(this);
        setOnClickListener();

        Intent i = getIntent();
        email = i.getStringExtra("emailxn");
        maxn=""+i.getIntExtra("maxn",0);
        tv_emailxn.setText(email);
        kt=i.getIntExtra("kt",1);
        account =db.getTaiKhoan(email);

    }

    private void setOnClickListener(){
        bt_xnmkm.setOnClickListener(this);
        bt_glmxn.setOnClickListener(this);
        imgv_logo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.bt_xnmkm: {
                String matkhau=edt_mkm.getText().toString();
                if (edt_mkm.getText().length() != 0 && edt_maopt.getText().length() != 0 && edt_nlmkm.getText().length() != 0) {
                    if(!edt_maopt.getText().toString().equals(maxn)){
                        Toast.makeText(this,"Mã xác nhận không chính xác. Vui lòng nhập lại!",Toast.LENGTH_SHORT).show();
                    }
                    else if(vailidatePass(edt_mkm.getText().toString())==false){
                        Toast.makeText(this,"Mật khẩu không hợp lệ (ít nhất 8 ký tự phải bao gồm chữ in hoa, chữ số và ký tự đặc biết)",Toast.LENGTH_SHORT).show();
                    }else if(!edt_nlmkm.getText().toString().equals(edt_mkm.getText().toString())){
                        Toast.makeText(this,"Mật khẩu không trùng nhau",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean updateMK=db.updateMK(email,matkhau);
                        if(updateMK==false){
                            Toast.makeText(this,"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Intent dialog_box = new Intent(this, Signin.class);
                            dialog_box.putExtra("email", tv_emailxn.getText().toString());
                            dialog_box.putExtra("pass", edt_mkm.getText().toString());
                            Toast.makeText(this,"Lấy lại mật khẩu thành công!",Toast.LENGTH_SHORT).show();
                            startActivity(dialog_box);
                        }
                    }
                } else {
                    if(edt_maopt.getText().length()==0){
                        Toast.makeText(this,"Vui lòng nhập mã OTP",Toast.LENGTH_SHORT).show();
                    }
                    else if(edt_mkm.getText().length()==0){
                        Toast.makeText(this,"Vui lòng nhập mật khẩu mới",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this,"Vui lòng nhập nhập lại mật khẩu mới",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case R.id.imgv_logo:
                Intent dialog_box1 = new Intent(this, HomeActivity.class);
                startActivity(dialog_box1);
                finish();
                break;
            case R.id.bt_glmxn:
                if(kt==3){
                    db.updateTrangThai(account.getId(),3);
                    Toast.makeText(this,"Tài khoản bị tạm khóa. Vui Lòng thử lại sau",Toast.LENGTH_SHORT).show();
                    maxn="bikhoa";
                }else{
                    Random Rand = new Random();
                    ranNum = Rand.nextInt((999999 - 100000) + 1) + 100000;
                    List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));
                    new SendMailTask(this).execute("argoncomic@gmail.com",
                            "trong02456789", toEmailList, "Quên mật khẩu", "Mã code: " + ranNum);
                    kt+=1;
                    maxn=""+ranNum;
                    Toast.makeText(this,"Mã OTP đã được gửi lại! (còn lại: "+(3-kt)+" lần)",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void Anhxa(){
        imgv_logo=findViewById(R.id.imgv_logo);
        bt_glmxn=findViewById(R.id.bt_glmxn);
        bt_xnmkm=findViewById(R.id.bt_xnmkm);
        edt_maopt=findViewById(R.id.edt_maopt);
        edt_mkm=findViewById(R.id.edt_mkm);
        edt_nlmkm=findViewById(R.id.edt_nlmkm);
        tv_emailxn=findViewById(R.id.tv_emailxn);
    }

    public static boolean vailidatePass(String pass) {
        String expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}