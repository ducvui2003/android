package com.example.truyenapp.view.activity;

import android.annotation.SuppressLint;
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
import com.example.truyenapp.api.CommentAPI;
import com.example.truyenapp.api.RatingAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.paging.PagingScrollListener;
import com.example.truyenapp.request.CommentCreationRequestDTO;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.ChapterContentRespone;
import com.example.truyenapp.response.CommentResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.response.RatingResponse;
import com.example.truyenapp.view.adapter.CommentAdapter;
import com.example.truyenapp.view.adapter.DocChapterAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadChapterActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcv, rcvComment;
    private DocChapterAdapter rcvAdapter;
    private BinhLuanAdapter rcvCommentAdapter;
    public Integer idChapter, idComic;
    TextView chapterName, star;
    ImageView imgBackChapter, imgPre, imgNext;
    Button btnRate, btnComment;
    EditText editTextComment;
    RatingBar rtb;
    private ChapterAPI chapterAPI;
    private ArrayList<String> listChapterName;
    private int position;
    private ArrayList<Integer> listChapterId;
    private Intent intent;
    //    Comment
    private CommentAPI commentAPI;
    private CommentAdapter commentAdapter;
    private List<CommentResponse> comments;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int totalPage;
    private int currentPage = 1;
    private final int PAGE_SIZE = 5;
    private LinearLayoutManager linearLayoutManager;

    // Rating
    private RatingAPI ratingAPI;
    private int ratingId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);
        init();
        initIntent();
        initComment();
        setOnClickListener();
        getChapterContent(idChapter);
        getRatingByChapterId();
        getComment();
    }

    private void init() {
        rcv = findViewById(R.id.rcv_read_chapter);
        rcvComment = findViewById(R.id.rcv_read_chapter_comment);
        chapterName = findViewById(R.id.tv_rea_chapter_name);
        imgBackChapter = findViewById(R.id.image_read_chapter_back);
        imgNext = findViewById(R.id.image_read_chapter_next);
        imgPre = findViewById(R.id.image_read_chapter_prev);
        editTextComment = findViewById(R.id.edit_read_chapter_comment);
        btnComment = findViewById(R.id.button_read_chapter_comment);
        btnRate = findViewById(R.id.button_rate);
        rtb = findViewById(R.id.rating_bar);
        star = findViewById(R.id.tv_read_chapter_star);
        chapterAPI = RetrofitClient.getInstance(this).create(ChapterAPI.class);
        connectAPI();
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

    private void initComment() {
        this.commentAPI = RetrofitClient.getInstance(this).create(CommentAPI.class);
        this.rcvComment = findViewById(R.id.rcv_read_chapter_comment);
        this.linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.comments = new ArrayList<>();
        this.commentAdapter = new CommentAdapter(this, this.comments);
        this.rcvComment.setLayoutManager(linearLayoutManager);
        this.rcvComment.setAdapter(commentAdapter);
        rcv.addOnScrollListener(new PagingScrollListener(this.linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage += 1;
                getComment();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_read_chapter_prev:
                if (!isPre()) return;
                Intent intent = new Intent(this, ReadChapterActivity.class);
                setupIntent(intent, position - 1);
                startActivity(intent);
                finish();
                break;
            case R.id.image_read_chapter_next:
                if (!isNext()) return;
                Intent intent1 = new Intent(this, ReadChapterActivity.class);
                setupIntent(intent1, position + 1);
                startActivity(intent1);
                break;
            case R.id.image_read_chapter_back:
                Intent intent2 = new Intent(this, DetailComicActivity.class);
                intent2.putExtra(BundleConstraint.ID_COMIC, idComic);
                startActivity(intent2);
                finish();
                break;
            case R.id.button_read_chapter_comment:
                comment();
                break;
            case R.id.button_rate:
                if (ratingId == 0) {
                    createRating();
                } else {
                    updateRating();
                }
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
        ratingAPI = RetrofitClient.getInstance(this).create(RatingAPI.class);
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

    //Comment
    private void setFirstData(List<CommentResponse> list) {
        this.comments.addAll(list);
        commentAdapter.setData(this.comments);
        if (currentPage < totalPage) {
            commentAdapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadNextPage(List<CommentResponse> list) {
        commentAdapter.removeFooterLoading();
        this.comments.addAll(list);
        commentAdapter.notifyDataSetChanged();
        this.isLoading = false;
        if (currentPage < totalPage) {
            commentAdapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
    }

    public void getComment() {
        commentAPI.getComments("BY_CHAPTER", idChapter, currentPage, PAGE_SIZE).enqueue(new Callback<APIResponse<DataListResponse<CommentResponse>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<CommentResponse>>> call, Response<APIResponse<DataListResponse<CommentResponse>>> response) {
                APIResponse<DataListResponse<CommentResponse>> data = response.body();
                if (data == null || data.getResult() == null || data.getResult().getData() == null) {
                    return;
                }
                if (data.getCode() == 400)
                    return;
                List<CommentResponse> listTemp = data.getResult().getData();
                currentPage = data.getResult().getCurrentPage();
                totalPage = data.getResult().getTotalPages();
                if (currentPage == 1) {
                    setFirstData(listTemp);
                } else {
                    loadNextPage(listTemp);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<CommentResponse>>> call, Throwable t) {
                Log.d("Comment", "onFailure: " + t.getMessage());
            }
        });
    }

    public void comment() {
        String text = editTextComment.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(this, "Vui lòng không để trống comment", Toast.LENGTH_SHORT).show();
        } else {
            CommentCreationRequestDTO requestDTO = CommentCreationRequestDTO.builder().chapterId(idChapter).content(text).build();
            commentAPI.create(requestDTO).enqueue(new Callback<APIResponse<CommentResponse>>() {
                @Override
                public void onResponse(Call<APIResponse<CommentResponse>> call, Response<APIResponse<CommentResponse>> response) {
                    if (response.isSuccessful()) {
                        APIResponse<CommentResponse> apiResponse = response.body();
                        if (apiResponse != null) {
                            CommentResponse commentResponse = apiResponse.getResult();
                            comments.add(0, commentResponse);
                            commentAdapter.notifyDataSetChanged();
                        }
                    }
                    editTextComment.setText("");
                }

                @Override
                public void onFailure(Call<APIResponse<CommentResponse>> call, Throwable throwable) {

                }
            });
        }

    }

    private void createRating() {
        float rating = getUserRating();
        if (rating == 0) {
            Toast.makeText(this, "Vui lòng chọn số sao muốn đánh giá", Toast.LENGTH_SHORT).show();
            return;
        }
        RatingResponse ratingResponse = getRatingValue(ratingId, idChapter, getCurrentDateTime());
        if (ratingResponse != null) {
            ratingAPI.createRating(ratingResponse).enqueue(new Callback<APIResponse<Void>>() {
                @Override
                public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                    runOnUiThread(() -> {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Thêm đánh giá thành công", Toast.LENGTH_SHORT).show();
                            setRatingAfterChanged();
                        } else {
                            int statusCode = response.code();
                            if (statusCode == 400) {
                                Toast.makeText(getApplicationContext(), "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                    runOnUiThread(() -> {
                        Log.d("Rating", "onFailure: " + t.getMessage());
                        Toast.makeText(getApplicationContext(), "Đánh giá thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            });
        }
    }

    private void updateRating() {
        float rating = getUserRating();
        if (rating == 0) {
            Toast.makeText(this, "Vui lòng chọn số sao muốn đánh giá", Toast.LENGTH_SHORT).show();
            return;
        }
        RatingResponse ratingResponse = getRatingValue(ratingId, idChapter, getCurrentDateTime());
        if (ratingResponse != null) {
            ratingAPI.updateRating(ratingResponse).enqueue(new Callback<APIResponse<Void>>() {
                @Override
                public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Cập nhật đánh giá thành công", Toast.LENGTH_SHORT).show();
                        setRatingAfterChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Cập nhật đánh giá thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                    Log.d("Rating", "onFailure: " + t.getMessage());
                }
            });
        }
    }

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    private RatingResponse getRatingValue(int evaluateId, int chapterId, LocalDateTime evaluateDate) {
        float rating = getUserRating();
        Date createAt = Date.from(evaluateDate.atZone(ZoneId.systemDefault()).toInstant());
        if (rating == 0) {
            Toast.makeText(this, "Vui lòng chọn số sao muốn đánh giá", Toast.LENGTH_SHORT).show();
            return null;
        }
        return new RatingResponse(evaluateId, chapterId, 0, rating, createAt, false);
    }

    private float getUserRating() {
        return rtb.getRating();
    }

    @SuppressLint("SetTextI18n")
    private void setRatingAfterChanged(){
        float ratingChanged = getUserRating();
        rtb.setRating(ratingChanged);
        star.setText(String.valueOf(ratingChanged));
        btnRate.setText("Cập nhật");
    }

    private void getRatingByChapterId() {
        // Call the getRatingByChapterId method from the RatingAPI interface
        ratingAPI.getRatingByChapterId(idChapter).enqueue(new Callback<APIResponse<RatingResponse>>() {
            // This method is called when the server response is received
            @Override
            public void onResponse(Call<APIResponse<RatingResponse>> call, Response<APIResponse<RatingResponse>> response) {
                // Get the APIResponse object from the response
                APIResponse<RatingResponse> apiResponse = response.body();
                // Check if the APIResponse object is not null
                if (apiResponse != null) {
                    // Get the RatingResponse object from the APIResponse object
                    RatingResponse rating = apiResponse.getResult();
                    // Check if the RatingResponse object is not null
                    if (rating != null) {
                        // set ratingId
                        ratingId = rating.getId();
                        // Set the text of the button to "Update"
                        btnRate.setText("Cập nhât");
                        // Set the rating value to the RatingBar
                        rtb.setRating(rating.getStar());
                        // Set the rating value to the TextView
                        star.setText(String.valueOf(rating.getStar()));
                    } else {
                        // If no rating is available, reset the RatingBar and TextView
                        ratingId = 0;
                        rtb.setRating(0);
                        star.setText("0");
                    }
                }
            }

            // This method is called when the request could not be executed due to cancellation, a connectivity problem, or a timeout
            @Override
            public void onFailure(Call<APIResponse<RatingResponse>> call, Throwable throwable) {
                // Log the error message
                Log.e("TAG", "Get rating by chapter id failed: " + throwable.getMessage());
                // Show a toast message indicating that an error occurred
                runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

}