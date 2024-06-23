
package com.example.truyenapp.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementAccount extends AppCompatActivity {
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
        setContentView(R.layout.activity_management_account);

        init();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        recyclerViewQLTaiKhoan();
        getAccount();
    }

    private void recyclerViewQLTaiKhoan() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
    }

    private void init() {
        rcv = findViewById(R.id.rcv_manage_account);
        btnCancel = findViewById(R.id.btn_cancel_add_account);
        btnAdd = findViewById(R.id.btn_add_account);
        edtEmail = findViewById(R.id.edt_email_new_account);
        edtPassword = findViewById(R.id.edt_password);
        name = findViewById(R.id.edt_name_new_account);
        edtPhone = findViewById(R.id.edt_phone_new_account);
        cardViewAddAcount = findViewById(R.id.cv_add_new_account);
    }

    public void getAccount() {
        Context context = this;
        userAPI.findAll().enqueue(new Callback<APIResponse<List<Account>>>() {
            @Override

            public void onResponse(Call<APIResponse<List<Account>>> call, Response<APIResponse<List<Account>>> response) {
                if (response.isSuccessful()) {
                    List<Account> listAccount = new ArrayList<>(response.body().getResult());
                    listAccount.removeIf(account -> account.getRole().equalsIgnoreCase("ADMIN"));
                    adapter = new ManagerAccountAdapter(context, listAccount);
                    rcv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Account>>> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
