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
import com.example.truyenapp.view.adapter.CommentAdapter;
import com.example.truyenapp.view.adapter.DocChapterAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadChapterActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcv, rcvComment;
    private DocChapterAdapter rcvAdapter;
    private CommentAdapter rcvCommentAdapter;
    public Integer idChapter, idComic;
    TextView chapterName, star;
    ImageView imgBackChapter, imgPre, imgNext;
    Button btnRate, btnComment;
    EditText edtComment;
    RatingBar rtb;
    private ChapterAPI chapterAPI;
    private ArrayList<String> listChapterName;
    private int position;
    private ArrayList<Integer> listChapterId;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);
        init();
        initIntent();
        setOnClickListener();
        getChapterContent(idChapter);
    }

    private void init() {
        rcv = findViewById(R.id.rcv_docchapter);
        rcvComment = findViewById(R.id.rcv_chapter_comment);
        chapterName = findViewById(R.id.tv_tenchapter);
        imgBackChapter = findViewById(R.id.img_backdoctruyen);
        imgNext = findViewById(R.id.img_next);
        imgPre = findViewById(R.id.img_pre);
        edtComment = findViewById(R.id.edt_binhluan);
        btnComment = findViewById(R.id.bt_binhluan);
        btnRate = findViewById(R.id.bt_danhgia);
        rtb = findViewById(R.id.rtb);
        star = findViewById(R.id.tv_sosaochapter);
        chapterAPI = RetrofitClient.getInstance(this).create(ChapterAPI.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.rcv.setLayoutManager(linearLayoutManager);
        this.rcv.setAdapter(rcvAdapter);
    }

    private void initIntent() {
        this.intent = getIntent();
        this.idChapter = intent.getIntExtra(BundleConstraint.ID_CHAPTER, 0);
        this.idComic = intent.getIntExtra(BundleConstraint.ID_COMIC, 0);
        this.position = intent.getIntExtra(BundleConstraint.POSITION, 0);
        this.listChapterName = intent.getStringArrayListExtra(BundleConstraint.LIST_CHAPTER_NAME);
        this.chapterName.setText(listChapterName.get(position));
        this.listChapterId = intent.getIntegerArrayListExtra(BundleConstraint.LIST_CHAPTER_ID);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_pre:
                if (!isPre()) return;
                Intent intent = new Intent(this, ReadChapterActivity.class);
                setupIntent(intent, position - 1);
                startActivity(intent);
                finish();
                break;
            case R.id.img_next:
                if (!isNext()) return;
                Intent intent1 = new Intent(this, ReadChapterActivity.class);
                setupIntent(intent1, position + 1);
                startActivity(intent1);
                break;
            case R.id.img_backdoctruyen:
                Intent intent2 = new Intent(this, DetailComicActivity.class);
                intent2.putExtra(BundleConstraint.ID_COMIC, idComic);
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
                        rcvAdapter = new DocChapterAdapter(chapterContentResponses, ReadChapterActivity.this);
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
        return position != listChapterName.size() - 1;
    }

    public boolean isPre() {
        return position != 0;
    }

    private void setupIntent(Intent intent, int position) {
        intent.putExtra(BundleConstraint.QUANTITY, listChapterName.size());
        intent.putExtra(BundleConstraint.ID_COMIC, idComic);
        intent.putExtra(BundleConstraint.POSITION, position);
        intent.putExtra(BundleConstraint.LIST_CHAPTER_NAME, listChapterName);
        intent.putIntegerArrayListExtra(BundleConstraint.LIST_CHAPTER_ID, listChapterId);
        intent.putExtra(BundleConstraint.ID_CHAPTER, listChapterId.get(position));
    }
}
