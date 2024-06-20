
package com.example.truyenapp.admin;

import android.content.Context;
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
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.view.adapter.admin.ManagerAccountAdapter;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerAccount extends AppCompatActivity implements View.OnClickListener {
    private Account account;
    private String email;
    private RecyclerView rcv;
    private ManagerAccountAdapter adapter;
    private ImageView imgNewAccount;
    private Button btnAdd, btnCancel;
    private EditText edtEmail, edtPassword, name, edtPhone;
    private CardView cardViewAddAcount;
    private UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlytaikhoan);

        init();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        setOnClickListener();
        recyclerViewQLTaiKhoan();
        getAccount();
    }

    private void recyclerViewQLTaiKhoan() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
    }

    private void init() {
        rcv = findViewById(R.id.rcvManagerAccount);
        btnCancel = findViewById(R.id.btnCancelNewAccount);
        btnAdd = findViewById(R.id.btnAddNewAccount);
        edtEmail = findViewById(R.id.edtEmailNewAccount);
        edtPassword = findViewById(R.id.edtPasswordNewAccount);
        name = findViewById(R.id.edtNameOfNewAccount);
        edtPhone = findViewById(R.id.edtPhoneOfNewAccount);
        cardViewAddAcount = findViewById(R.id.cvAddAccount);
    }

    private void setOnClickListener() {
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancelNewAccount:
                cardViewAddAcount.setVisibility(View.GONE);
                break;
            case R.id.btnAddNewAccount:
                String email = edtEmail.getText().toString().trim();
                String matkhau = edtPassword.getText().toString();
                String ten = name.getText().toString().trim();
                String dienThoai = edtPhone.getText().toString();
        }
    }

    public void getAccount() {
        Context context = this;
        userAPI.findAll().enqueue(new Callback<APIResponse<List<Account>>>() {
            @Override

            public void onResponse(Call<APIResponse<List<Account>>> call, Response<APIResponse<List<Account>>> response) {
                if (response.isSuccessful()) {
                    List<Account> list = response.body().getResult();
                    adapter = new ManagerAccountAdapter(context, list);
                    rcv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Account>>> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public static String removeAccent(String s) {
//        s = s.toLowerCase();
//        s = s.replaceAll("đ", "d");
//        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
//        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//        return pattern.matcher(temp).replaceAll("");
//    }
}
