package com.example.truyenapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenapp.DangKy;
import com.example.truyenapp.Home;
import com.example.truyenapp.QuenMK;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    TextView textView, textView2;
    ImageView logo;
    EditText emailField, passwordField;
    TextView emailError;
    Button signInBtn;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        db = new Database(this);

        Anhxa();
        setOnClickListener();

        Intent i = getIntent();

        String email = i.getStringExtra("email");
        String pass = i.getStringExtra("pass");

        emailField.setText(email);
        passwordField.setText(pass);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_chuatk:
                Intent dialog_box1 = new Intent(SignIn.this, DangKy.class);
                startActivity(dialog_box1);
                break;
            case R.id.tv_quenmk:
                Intent dialog_box2 = new Intent(SignIn.this, QuenMK.class);
                startActivity(dialog_box2);
                break;
            case R.id.bt_dn: {
                handleSignIn();
                break;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void handleSignIn() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.length() != 0 && password.length() != 0) {
            Boolean emailExits = db.checkEmailMatkhau(email, password);
            if (emailExits) {
                int kt = db.checkTrangThai(email);
                if (kt != 0) {
                    Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent dialog_box = new Intent(SignIn.this, Home.class);
                    dialog_box.putExtra("email", email);
                    startActivity(dialog_box);
                    finish();
                } else {
                    Toast.makeText(this, "Tài khoản bị khóa", Toast.LENGTH_SHORT).show();
                }
            } else {
                emailError.setText("Email sai!");
                emailError.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Email hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (email.length() == 0) {
                Toast.makeText(this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vui lòng nhập Mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setOnClickListener() {
        logo.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
    }

    private void Anhxa() {
        logo = (ImageView) findViewById(R.id.img_logodn);
        textView = (TextView) findViewById(R.id.tv_chuatk);
        textView2 = (TextView) findViewById(R.id.tv_quenmk);
        emailField = findViewById(R.id.edt_dn_email);
        passwordField = findViewById(R.id.edt_dn_pass);
        signInBtn = findViewById(R.id.bt_dn);
        emailError = (TextView) findViewById(R.id.emailError);
    }

    public static String removeAccent(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}