package com.example.truyenapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.ChapterAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.model.Evaluate;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.ChapterResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingsHistoryAdapter extends RecyclerView.Adapter<RatingsHistoryAdapter.ShowDanhGiaViewHolder> {
    private Context context;
    private List<Evaluate> list;
    private List<BookResponse> listBook;
    private ChapterAPI chapterAPI;

    public RatingsHistoryAdapter(Context context, List<Evaluate> list, List<BookResponse> listBook) {
        this.context = context;
        this.list = list;
        this.listBook = listBook;
        this.chapterAPI = RetrofitClient.getInstance(context).create(ChapterAPI.class);
    }

    public void setData(List<Evaluate> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowDanhGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_tong, parent, false);
        return new RatingsHistoryAdapter.ShowDanhGiaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShowDanhGiaViewHolder holder, int position) {

        Evaluate evaluate = list.get(position);
        if (evaluate == null) {
            return;
        }

        BookResponse book = listBook.get(position);
        getChapterInfo(evaluate.getIdChapter(), holder);




        Glide.with(this.context).load(book.getThumbnail()).into(holder.img_tong_truyen);
        holder.tv_tong_ngaydang.setText(formatEvaluateDate(evaluate.getEvaluateDate()));
        holder.tv_tong_pl.setText("Đánh giá: " + evaluate.getStar());
        holder.tv_tong_tentruyen.setText(book.getName());
    }

    private void getChapterInfo(int chapterId, ShowDanhGiaViewHolder holder) {
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
            assert date != null;
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ShowDanhGiaViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tong_tentruyen, tv_tong_tenchapter, tv_tong_pl, tv_tong_ngaydang;
        private ImageView img_tong_truyen;

        public ShowDanhGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tong_tentruyen = itemView.findViewById(R.id.tv_tong_tentruyen);
            tv_tong_tenchapter = itemView.findViewById(R.id.tv_tong_tenchapter);
            tv_tong_pl = itemView.findViewById(R.id.tv_tong_pl);
            tv_tong_ngaydang = itemView.findViewById(R.id.tv_tong_ngaydang);
            img_tong_truyen = itemView.findViewById(R.id.img_tong_truyen);
        }
    }
}
