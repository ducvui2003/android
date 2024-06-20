package com.example.truyenapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.ChapterAPI;
import com.example.truyenapp.api.RatingAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.model.Evaluate;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.ChapterResponse;
import com.example.truyenapp.response.RatingResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingsHistoryAdapter extends RecyclerView.Adapter<RatingsHistoryAdapter.RatingHistoryViewHolder> {
    private Context context;
    private List<Evaluate> list;
    private List<BookResponse> listBook;
    private ChapterAPI chapterAPI;
    private RatingAPI ratingAPI;

    public RatingsHistoryAdapter(Context context, List<Evaluate> list, List<BookResponse> listBook) {
        this.context = context;
        this.list = list;
        this.listBook = listBook;
        connectAPI();
    }

    public void setData(List<Evaluate> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RatingHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_tong, parent, false);
        return new RatingHistoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RatingHistoryViewHolder holder, int position) {
        Evaluate evaluate = list.get(position);
        if (evaluate == null) {
            return;
        }

        holder.btn_edit.setOnClickListener(v -> {
            holder.rateComponent.setVisibility(View.VISIBLE);
            holder.btn_cancel.setVisibility(View.VISIBLE);
            holder.btn_rate.setText("Cập nhật");
            holder.tv_sosaochapter.setText("Số sao: " + evaluate.getStar());
            holder.rtb.setRating(evaluate.getStar());
            setRatingAfterChanged(holder);
        });
        holder.btn_cancel.setOnClickListener(v -> holder.rateComponent.setVisibility(View.GONE));

        holder.rtb.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> getUserRating(holder));

        BookResponse book = listBook.get(position);
        getChapterInfo(evaluate.getIdChapter(), holder);

        Glide.with(context).load(book.getThumbnail()).into(holder.img_tong_truyen);
        holder.tv_tong_ngaydang.setText(formatEvaluateDate(evaluate.getEvaluateDate()));
        holder.tv_tong_pl.setText("Đánh giá: " + evaluate.getStar());
        holder.tv_tong_tentruyen.setText(book.getName());
    }

    private void getChapterInfo(int chapterId, RatingHistoryViewHolder holder) {
        chapterAPI.getChapter(chapterId).enqueue(new Callback<ChapterResponse>() {
            @Override
            public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {
                if (response.isSuccessful()) {
                    ChapterResponse chapter = response.body();
                    if (chapter != null) {
                        holder.tv_tong_tenchapter.setText(chapter.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<ChapterResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    private static String formatEvaluateDate(String evaluateDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
        try {
            Date date = inputFormat.parse(evaluateDate);
            return date != null ? outputFormat.format(date) : null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class RatingHistoryViewHolder extends RecyclerView.ViewHolder {
        private RatingBar rtb;
        private TextView tv_tong_tentruyen, tv_tong_tenchapter, tv_tong_pl, tv_tong_ngaydang, tv_sosaochapter;
        private ImageView img_tong_truyen;
        private Button btn_edit, btn_cancel, btn_rate;
        private View rateComponent;


        public RatingHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tong_tentruyen = itemView.findViewById(R.id.tv_tong_tentruyen);
            tv_tong_tenchapter = itemView.findViewById(R.id.tv_tong_tenchapter);
            tv_tong_pl = itemView.findViewById(R.id.tv_tong_pl);
            tv_tong_ngaydang = itemView.findViewById(R.id.tv_tong_ngaydang);
            img_tong_truyen = itemView.findViewById(R.id.img_tong_truyen);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            tv_sosaochapter = itemView.findViewById(R.id.tv_read_chapter_star);
            rtb = itemView.findViewById(R.id.rating_bar);
            btn_rate = itemView.findViewById(R.id.button_rate);
            rateComponent = itemView.findViewById(R.id.rate_component);

            btn_rate.setOnClickListener(v -> updateRating(this, getBindingAdapterPosition()));
        }
    }

    private void connectAPI() {
        chapterAPI = RetrofitClient.getInstance(context).create(ChapterAPI.class);
        ratingAPI = RetrofitClient.getInstance(context).create(RatingAPI.class);
    }

    private void updateRating(@NonNull RatingHistoryViewHolder holder, int position) {
        Evaluate evaluate = list.get(position);
        if (evaluate == null) {
            return;
        }
        float rating = getUserRating(holder);
        if (rating == 0) {
            Toast.makeText(context, "Vui lòng chọn số sao muốn đánh giá", Toast.LENGTH_SHORT).show();
            return; // Không gửi yêu cầu nếu người dùng chưa chọn sao
        }
        RatingResponse ratingResponse = getRatingValue(holder, evaluate.getId(), evaluate.getIdChapter(), evaluate.getIdAccount(), getCurrentDateTime());
        if (ratingResponse != null) {
            ratingAPI.updateRating(ratingResponse).enqueue(new Callback<APIResponse<Void>>() {
                @Override
                public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Cập nhật đánh giá thành công", Toast.LENGTH_SHORT).show();
                        setRatingAfterChanged(holder);
                        holder.rateComponent.setVisibility(View.GONE); // Đóng rateComponent
                    } else {
                        int statusCode = response.code();
                        if (statusCode == 400) {
                            Toast.makeText(context, "Bạn đã đánh giá rồi", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Cập nhật đánh giá thất bại", Toast.LENGTH_SHORT).show();
                        }
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

    private RatingResponse getRatingValue(@NonNull RatingHistoryViewHolder holder, int ratingId, int chapterId, int userId, LocalDateTime evaluateDate) {
        float rating = getUserRating(holder);
        Date createAt = Date.from(evaluateDate.atZone(ZoneId.systemDefault()).toInstant());
        if (rating == 0) {
            Toast.makeText(context, "Vui lòng chọn số sao muốn đánh giá", Toast.LENGTH_SHORT).show();
            return null;
        }
        return new RatingResponse(ratingId, chapterId, userId, rating, createAt, false);
    }

    private float getUserRating(RatingHistoryViewHolder holder) {
        return holder.rtb.getRating();
    }

    @SuppressLint("SetTextI18n")
    private void setRatingAfterChanged(RatingHistoryViewHolder holder){
        float ratingChanged = getUserRating(holder);
        holder.rtb.setRating(ratingChanged);
        holder.tv_sosaochapter.setText(String.valueOf(ratingChanged));
        holder.tv_tong_pl.setText("Đánh giá: " + ratingChanged);
    }
}
