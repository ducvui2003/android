package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoiMatKhau extends AppCompatActivity implements View.OnClickListener {

    Database db;
    Account account;
    String email;
    EditText edt_dmk_mkht,edt_dmk_mkm,edt_dmk_nlmk;
    Button bt_xndmk,bt_huy;
    HomeActivity home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doimatkhau);

        db=new Database(this);
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        account =db.getTaiKhoan(email);

        Anhxa();
        setOnClickListener();
    }

    private void Anhxa() {
        edt_dmk_mkht=findViewById(R.id.edt_dmk_mkht);
        edt_dmk_mkm=findViewById(R.id.edt_dmk_mkm);
        edt_dmk_nlmk=findViewById(R.id.edt_dmk_nlmk);
        bt_huy=findViewById(R.id.bt_huy);
        bt_xndmk=findViewById(R.id.bt_xndmk);
    }

    private void setOnClickListener(){
        bt_xndmk.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_huy:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_xndmk:
                String mkht=edt_dmk_mkht.getText().toString();
                String mkm=edt_dmk_mkm.getText().toString();
                String nlmk=edt_dmk_nlmk.getText().toString();

                if(mkht.length()!=0 && mkm.length()!=0 && nlmk.length()!=0){
                    Boolean kt=db.checkEmailMatkhau(email,edt_dmk_mkht.getText().toString());
                    if(kt==true){
                        if(vailidatePass(mkm)==false){
                            Toast.makeText(this,"Mật khẩu không hợp lệ (ít nhất 8 ký tự phải bao gồm chữ in hoa, chữ số và ký tự đặc biết)",Toast.LENGTH_SHORT).show();
                        }
                        else if(!nlmk.equals(mkm)){
                            Toast.makeText(this,"Mật khẩu không trùng nhau",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            db.updateMK(account.getEmail(),edt_dmk_mkm.getText().toString());
                            Toast.makeText(this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(this, HomeActivity.class);
                            intent1.putExtra("email",email);
                            startActivity(intent1);
                            finish();
                        }
                    }else {
                        Toast.makeText(this,"Mật khẩu hiện tại không chính xác",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if(mkht.length()==0){
                        Toast.makeText(this,"Vui lòng nhập mật khẩu hiện tại",Toast.LENGTH_SHORT).show();
                    }
                    else if(mkm.length()==0){
                        Toast.makeText(this,"Vui lòng nhập mật khẩu mới",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this,"Vui lòng nhập nhập lại mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    public static boolean vailidatePass(String pass) {
        String expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}
