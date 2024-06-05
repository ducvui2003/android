package com.example.truyenapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.ChapterAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.ChapterContentRespone;
import com.example.truyenapp.view.adapter.BinhLuanAdapter;
import com.example.truyenapp.view.adapter.DocChapterAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadChapter extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcv, rcvComment;
    private DocChapterAdapter rcvAdapter;
    private BinhLuanAdapter rcvCommentAdapter;
    public int idChapter, idComic;
    TextView chapterName, star;
    ImageView imgBackChapter, imgPre, imgNext;
    Button btnRate, btnComment;
    EditText edtComment;
    RatingBar rtb;
    private ChapterAPI chapterAPI;
    private ArrayList<String> listChapterName;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docchapter);
        init();
        chapterAPI = RetrofitClient.getInstance(this).create(ChapterAPI.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        idChapter = intent.getIntExtra(BundleConstraint.ID_CHAPTER, 0);
        idComic = intent.getIntExtra(BundleConstraint.ID_COMMIC, 0);
        rcv.setAdapter(rcvAdapter);
        position = intent.getIntExtra("position", 0);
        listChapterName = intent.getStringArrayListExtra("listChapterName");
        chapterName.setText(listChapterName.get(position));
        setOnClickListener();
        getChapterContent(idChapter);
    }

    private void init() {
        rcv = findViewById(R.id.rcv_docchapter);
        rcvComment = findViewById(R.id.rcv_binhluan);
        chapterName = findViewById(R.id.tv_tenchapter);
        imgBackChapter = findViewById(R.id.img_backdoctruyen);
        imgNext = findViewById(R.id.img_next);
        imgPre = findViewById(R.id.img_pre);
        edtComment = findViewById(R.id.edt_binhluan);
        btnComment = findViewById(R.id.bt_binhluan);
        btnRate = findViewById(R.id.bt_danhgia);
        rtb = findViewById(R.id.rtb);
        star = findViewById(R.id.tv_sosaochapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_pre:
                if (!isPre()) return;
                Intent intent = new Intent(this, ReadChapter.class);
                setupIntent(intent);
                intent.putExtra("position", getIntent().getIntExtra("position", 0) -1);
                startActivity(intent);
                finish();
                break;
            case R.id.img_next:
                if (!isNext()) return;
                Intent intent1 = new Intent(this, ReadChapter.class);
                setupIntent(intent1);
                intent1.putExtra("position", getIntent().getIntExtra("position", 0) + 1);
                startActivity(intent1);
                break;
            case R.id.img_backdoctruyen:
                Intent intent2 = new Intent(this, DetailComicActivity.class);
                intent2.putExtra(BundleConstraint.ID_COMMIC, idComic);
                startActivity(intent2);
                finish();
                break;
            case R.id.bt_binhluan:

                break;
            case R.id.bt_danhgia:

                break;
        }
    }

    private void setOnClickListener() {
        imgBackChapter.setOnClickListener(this);
        imgPre.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        btnComment.setOnClickListener(this);
        btnRate.setOnClickListener(this);
    }

    public void getChapterContent(int idChapter) {
        chapterAPI.getChapterContent(idChapter).enqueue(new Callback<APIResponse<List<ChapterContentRespone>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<ChapterContentRespone>>> call, Response<APIResponse<List<ChapterContentRespone>>> response) {
                if (response.isSuccessful()) {
                    APIResponse<List<ChapterContentRespone>> apiResponse = response.body();
                    if (apiResponse != null) {
                        List<ChapterContentRespone> chapterContentResponses = apiResponse.getResult();
                        rcvAdapter = new DocChapterAdapter(chapterContentResponses, ReadChapter.this);
                        rcv.setAdapter(rcvAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<ChapterContentRespone>>> call, Throwable t) {
                Log.d("DocChapter", "onFailure: " + t.getMessage());
            }
        });
    }

    public boolean isNext() {
        if (position == listChapterName.size() - 1) return false;
        return true;
    }

    public boolean isPre() {
        if (position == 0) return false;
        return true;
    }

    private void setupIntent(Intent intent){
        intent.putExtra(BundleConstraint.ID_CHAPTER, idChapter);
        intent.putExtra(BundleConstraint.ID_COMMIC, idComic);
        intent.putExtra("listChapterName", listChapterName);
    }
}
