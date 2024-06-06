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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.ChapterAPI;
import com.example.truyenapp.api.RatingAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.ChapterContentRespone;
import com.example.truyenapp.response.RatingResponse;
import com.example.truyenapp.response.UserResponse;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.adapter.BinhLuanAdapter;
import com.example.truyenapp.view.adapter.DocChapterAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadChapter extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcv, rcvComment;
    private DocChapterAdapter rcvAdapter;
    private BinhLuanAdapter rcvCommentAdapter;
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

    private UserAPI userAPI;
    private RatingAPI ratingAPI;
    private UserResponse userResponse;
    private int userId;
    private RatingResponse ratingResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docchapter);
        init();
        initIntent();
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
        chapterAPI = RetrofitClient.getInstance(this).create(ChapterAPI.class);
        connectAPI();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.rcv.setLayoutManager(linearLayoutManager);
        this.rcv.setAdapter(rcvAdapter);
    }

    private void initIntent() {
        this.intent = getIntent();
        this.idChapter = intent.getIntExtra(BundleConstraint.ID_CHAPTER, 0);
        this.idComic = intent.getIntExtra(BundleConstraint.ID_COMMIC, 0);
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
                Intent intent = new Intent(this, ReadChapter.class);
                setupIntent(intent, position - 1);
                startActivity(intent);
                finish();
                break;
            case R.id.img_next:
                if (!isNext()) return;
                Intent intent1 = new Intent(this, ReadChapter.class);
                setupIntent(intent1, position + 1);
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
                createRating();
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

    private void connectAPI() {
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        ratingAPI = RetrofitClient.getInstance(this).create(RatingAPI.class);
    }

    private void createRating() {
        RatingResponse ratingResponse = getRatingValue();

        ratingAPI.createRating(ratingResponse).enqueue(new Callback<APIResponse<Void>>() {
            @Override
            public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ReadChapter.this, "Thêm đánh giá thành công", Toast.LENGTH_SHORT).show();
                } else {
                    int statusCode = response.code();
                    if(statusCode == 400) {
                        Toast.makeText(ReadChapter.this, "Bạn đã đánh giá rồi", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ReadChapter.this, "Thêm đánh giá thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                Log.d("Rating", "onFailure: " + t.getMessage());
            }
        });
    }

    private RatingResponse getRatingValue() {
        getUserInfo();
        float rating = rtb.getRating();
        if(rating == 0) {
            Toast.makeText(this, "Vui lòng chọn số sao muốn đánh giá ", Toast.LENGTH_SHORT).show();
        }
        return new RatingResponse(0, idChapter, userId, rating, new Date());
    }


    private void getUserInfo() {
        // Call the getUserInfo method from the UserAPI interface
        JWTToken jwtToken = SharedPreferencesHelper.getObject(this, SystemConstant.JWT_TOKEN, JWTToken.class);
        if (jwtToken == null) {
            return;
        }
        userAPI.getUserInfo(jwtToken.getToken()).enqueue(new Callback<UserResponse>() {
            // This method is called when the server response is received
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Get the UserResponse object from the response
                UserResponse user = response.body();
                // Check if the user object is not null
                if (user != null) {
                    // Assign the user object to the userResponse variable
                    userResponse = user;
                    // Set the email of the user in the TextView tv_emailhome
                    userId = user.getId();
                }
            }

            // This method is called when the request could not be executed due to cancellation, a connectivity problem or a timeout
            @Override
            public void onFailure(Call<UserResponse> call, Throwable throwable) {
                // Log the error message
                Log.e("TAG", "Login failed: " + throwable.getMessage());
                // Show a toast message indicating that an error occurred
//                Toast.makeText(getActivity(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
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
        return position != listChapterName.size() - 1;
    }

    public boolean isPre() {
        return position != 0;
    }

    private void setupIntent(Intent intent, int position) {
        intent.putExtra(BundleConstraint.QUANTITY, listChapterName.size());
        intent.putExtra(BundleConstraint.ID_COMMIC, idComic);
        intent.putExtra(BundleConstraint.POSITION, position);
        intent.putExtra(BundleConstraint.LIST_CHAPTER_NAME, listChapterName);
        intent.putIntegerArrayListExtra(BundleConstraint.LIST_CHAPTER_ID, listChapterId);
        intent.putExtra(BundleConstraint.ID_CHAPTER, listChapterId.get(position));
    }
}
