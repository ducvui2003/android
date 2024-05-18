package com.example.truyenapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.BookAPI;
import com.example.truyenapp.api.ChapterAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.ChapterResponse;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.adapter.admin.QLChapterAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Chapter;
import com.example.truyenapp.model.Story;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailManagement extends AppCompatActivity implements View.OnClickListener {

    ImageView thumbnail, addChapterImg;
    TextView bookId;
    EditText bookAuthor, bookDescription, bookCategory, edt_linkanh, bookStatus, bookName, edt_tenchapter_newchapter;
    Button editBtn, addBtn, cancelAddChapterBtn, saveBtn, cancelEditBtn;
    Database db;
    Story story;
    int id;
    CardView addChapterCard;
    private RecyclerView rcv;
    private QLChapterAdapter adapter;
    private BookAPI bookAPI;
    private UserAPI userAPI;
    private ChapterAPI chapterAPI;
    private BookResponse bookResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_management);
        connectAPI();

        JWTToken token = SharedPreferencesHelper.getObject(this, SystemConstant.JWT_TOKEN, JWTToken.class);
        if (AuthenticationManager.isAdmin(token, userAPI)) {
            Toast.makeText(this, "Bạn không có quyền truy cập", Toast.LENGTH_SHORT).show();
            finish();
        }

        init();
        Intent intent = getIntent();
        id = intent.getIntExtra("bookId", 1);
        setEnable(false);
        setOnClickListener();
        getBook();
    }

    private void connectAPI() {
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        bookAPI = RetrofitClient.getInstance(this).create(BookAPI.class);
        chapterAPI = RetrofitClient.getInstance(this).create(ChapterAPI.class);
    }

    private void getBook() {
        bookAPI.getBook(id).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {
                    bookResponse = response.body();
                    setData();
                    recyclerViewQLChapter();
                } else {
                    Toast.makeText(BookDetailManagement.this, "Failed to get book", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Toast.makeText(BookDetailManagement.this, "Failed to get book", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void recyclerViewQLChapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        chapterAPI.getChaptersByBook(id).enqueue(new Callback<List<ChapterResponse>>() {
            @Override
            public void onResponse(Call<List<ChapterResponse>> call, Response<List<ChapterResponse>> response) {
                if (response.isSuccessful()) {
                    List<ChapterResponse> list = response.body();
                    adapter = new QLChapterAdapter(BookDetailManagement.this, list);
                    rcv.setAdapter(adapter);
                } else {
                    Toast.makeText(BookDetailManagement.this, "Failed to get chapters", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ChapterResponse>> call, Throwable t) {
                Toast.makeText(BookDetailManagement.this, "Failed to get chapters", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        Glide.with(this).load(bookResponse.getThumbnail()).into(thumbnail);
        bookName.setText(bookResponse.getName());
        bookAuthor.setText(bookResponse.getAuthor());
        bookDescription.setText(bookResponse.getDescription());
        bookId.setText(String.valueOf(bookResponse.getId()));
        edt_linkanh.setText(bookResponse.getThumbnail());
        bookStatus.setText(bookResponse.getStatus().equals(SystemConstant.STATUS_FULL) ? "Hoàn thành" : "Đang cập nhật");

        StringBuilder categories = new StringBuilder();
        for (String category : bookResponse.getCategoryNames()) {
            categories.append(category).append(", ");
        }
        categories.deleteCharAt(categories.length() - 2);
        bookCategory.setText(categories.toString());
    }

    private void setOnClickListener() {
        addChapterImg.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        cancelAddChapterBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
        cancelEditBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

    }

    private void init() {
        addChapterImg = findViewById(R.id.img_newchapter);
        thumbnail = findViewById(R.id.img_qlt);
        editBtn = findViewById(R.id.bt_chinhsuatruyen);
        cancelAddChapterBtn = findViewById(R.id.bt_huy_newchapter);
        addBtn = findViewById(R.id.bt_them_newchapter);
        bookId = findViewById(R.id.tv_qlt_id);
        bookAuthor = findViewById(R.id.edt_qlt_tacgia);
        bookDescription = findViewById(R.id.edt_qlt_mota);
        bookCategory = findViewById(R.id.edt_qlt_theloai);
        edt_linkanh = findViewById(R.id.edt_qlt_linkanh);
        bookStatus = findViewById(R.id.edt_qlt_trangthai);
        bookName = findViewById(R.id.edt_qlt_tentruyen);
        addChapterCard = findViewById(R.id.cv_themchapter);
        saveBtn = findViewById(R.id.bt_xacnhantruyen);
        cancelEditBtn = findViewById(R.id.bt_huychinhsuatruyen);
        edt_tenchapter_newchapter = findViewById(R.id.edt_tenchapter_newchapter);
        rcv = findViewById(R.id.rcv_quanlychapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_newchapter:
                addChapterCard.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_chinhsuatruyen:
                setEnable(true);
                break;
            case R.id.bt_huychinhsuatruyen:
                setEnable(false);
                break;
            case R.id.bt_xacnhantruyen:
                String tentruyen = bookName.getText().toString();
                String tacgia = bookAuthor.getText().toString();
                String mota = bookDescription.getText().toString();
                String theloai = bookCategory.getText().toString();
                String linkanh = edt_linkanh.getText().toString();
                String trangthai = bookStatus.getText().toString();
                int tt = Integer.parseInt(trangthai);
                String key_search = removeAccent(tentruyen).trim();
                if (!tentruyen.isEmpty() && !tacgia.isEmpty() && !mota.isEmpty() && !theloai.isEmpty() && !linkanh.isEmpty() && !trangthai.isEmpty()) {
                    if (tt == 1 || tt == 0) {
                        db.updateTruyen(story.getId(), tentruyen, tacgia, mota, theloai, linkanh, tt, key_search);
                        Toast.makeText(this, "Đã cập nhật thông tin truyện", Toast.LENGTH_SHORT).show();
                        reload();
                    } else {
                        Toast.makeText(this, "Trạng thái = 0 hoặc = 1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_them_newchapter:
                String tenchapter = edt_tenchapter_newchapter.getText().toString().trim();
                String kttenchapter = removeAccent(tenchapter);
                if (checkTenChapter(kttenchapter) == 1) {
                    Toast.makeText(this, "Tên chapter đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    db.insertChapter(tenchapter, id);
                    Toast.makeText(this, "Thêm chapter thành công", Toast.LENGTH_SHORT).show();
                    recyclerViewQLChapter();
                    addChapterCard.setVisibility(View.GONE);
                }
                break;
            case R.id.bt_huy_newchapter:
                addChapterCard.setVisibility(View.GONE);
                break;
        }
    }

    private void setEnable(boolean isEnable) {
        if (isEnable) {
            edt_linkanh.setEnabled(true);
            bookStatus.setEnabled(true);
            bookName.setEnabled(true);
            bookDescription.setEnabled(true);
            bookCategory.setEnabled(true);
            bookAuthor.setEnabled(true);
            editBtn.setVisibility(View.GONE);
            cancelEditBtn.setVisibility(View.VISIBLE);
            saveBtn.setVisibility(View.VISIBLE);
        } else {
            edt_linkanh.setEnabled(false);
            bookStatus.setEnabled(false);
            bookName.setEnabled(false);
            bookDescription.setEnabled(false);
            bookCategory.setEnabled(false);
            bookAuthor.setEnabled(false);
            editBtn.setVisibility(View.VISIBLE);
            cancelEditBtn.setVisibility(View.GONE);
            saveBtn.setVisibility(View.GONE);
        }
    }

    public static String removeAccent(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public int checkTenChapter(String tentruyen) {
        ArrayList<String> listtenchapter = db.listTenChapter();
        for (int i = 0; i < listtenchapter.size(); i++) {
            if (tentruyen.equals(removeAccent(listtenchapter.get(i)).trim())) {
                return 1;
            }
        }
        return 0;
    }
}
