package com.example.truyenapp.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.truyenapp.api.BookAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.UserResponse;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.adapter.admin.BookManagementAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookManagement extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcv;
    private BookManagementAdapter adapter;
    ImageView addBookImg;
    Button addBtn, cancelBtn;
    EditText bookName, category, author, description, avatar;
    CardView addBookCard;
    private UserAPI userAPI;
    private BookAPI bookAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_management);
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        bookAPI = RetrofitClient.getInstance(this).create(BookAPI.class);

        JWTToken token = SharedPreferencesHelper.getObject(this, SystemConstant.JWT_TOKEN, JWTToken.class);
        if (AuthenticationManager.isAdmin(token, userAPI)) {
            Toast.makeText(this, "Bạn không có quyền truy cập", Toast.LENGTH_SHORT).show();
            finish();
        }

        init();
        setOnClickListener();
        recyclerView();

    }

//    private void getBooks() {
//        CompletableFuture<List<BookResponse>> future = new CompletableFuture<>();
//        bookAPI.getAllBooks().enqueue(new Callback<List<BookResponse>>() {
//            @Override
//            public void onResponse(Call<List<BookResponse>> call, Response<List<BookResponse>> response) {
//                if (response.isSuccessful()) {
//                    future.complete(response.body());
//                } else {
//                    Toast.makeText(BookManagement.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<BookResponse>> call, Throwable t) {
//                future.completeExceptionally(t);
//            }
//        });
//        future.thenAccept(bookResponse -> {
//            this.books = bookResponse;
//            Log.i("TAG", "get book run complete" + books.size());
//        }).exceptionally(throwable -> {
//            Log.e("TAG", "Error getting book info", throwable);
//            return null;
//        });
//
//    }

    private void recyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        bookAPI.getAllBooks().enqueue(new Callback<List<BookResponse>>() {
            @Override
            public void onResponse(Call<List<BookResponse>> call, Response<List<BookResponse>> response) {
                if (response.isSuccessful()) {
                    adapter = new BookManagementAdapter(getApplicationContext(), response.body());
                    rcv.setAdapter(adapter);
                } else {
                    Toast.makeText(BookManagement.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookResponse>> call, Throwable t) {
                Toast.makeText(BookManagement.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });

    }

    private void init() {
        rcv = findViewById(R.id.rcv_quanlytruyen);
        addBookImg = findViewById(R.id.addBookImg);
        cancelBtn = findViewById(R.id.bt_huy_newtruyen);
        addBtn = findViewById(R.id.bt_them_newtruyen);
        bookName = findViewById(R.id.edt_tentruyen_newtruyen);
        category = findViewById(R.id.edt_theloai_newtruyen);
        author = findViewById(R.id.edt_tg_newtruyen);
        description = findViewById(R.id.edt_mota_newtruyen);
        avatar = findViewById(R.id.edt_linkanh_newtruyen);
        addBookCard = findViewById(R.id.cv_themtruyen);
    }

    private void setOnClickListener() {
        addBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        addBookImg.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addBookImg:
                addBookCard.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_huy_newtruyen:
                addBookCard.setVisibility(View.GONE);
                break;
            case R.id.bt_them_newtruyen:
                addBook();
                break;
        }
    }

    private void addBook() {
        String tentruyen = bookName.getText().toString().trim();
        String tacgia = author.getText().toString();
        String theloai = category.getText().toString();
        String mota = description.getText().toString();
        String linkanh = avatar.getText().toString();

        if (!tentruyen.isEmpty() && !tacgia.isEmpty() && !theloai.isEmpty() && !mota.isEmpty() && !linkanh.isEmpty()) {
            if (checkTenTruyen(tentruyen) == 1) {
                Toast.makeText(this, "Tên truyện đã tồn tại trong hệ thống", Toast.LENGTH_SHORT).show();
            } else {
//                db.insertTruyen(tentruyen, tacgia, mota, theloai, linkanh, key_search);
                Toast.makeText(this, "Thêm truyện thành công", Toast.LENGTH_SHORT).show();
                recyclerView();
                addBookCard.setVisibility(View.GONE);
            }
        } else if (tentruyen.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên truyện", Toast.LENGTH_SHORT).show();
        } else if (tacgia.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tác giả", Toast.LENGTH_SHORT).show();
        } else if (mota.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mô tả", Toast.LENGTH_SHORT).show();
        } else if (theloai.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập thể loại", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập link ảnh", Toast.LENGTH_SHORT).show();
        }
    }


    public int checkTenTruyen(String tentruyen) {
//        ArrayList<String> listtentruyen = null;
//        for (int i = 0; i < listtentruyen.size(); i++) {
//            if (tentruyen.equals(removeAccent(listtentruyen.get(i)).trim())) {
//                return 1;
//            }
//        }
        return 0;
    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
