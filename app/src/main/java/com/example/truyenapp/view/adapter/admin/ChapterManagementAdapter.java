package com.example.truyenapp.view.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.admin.ShowThongTinChapter;
import com.example.truyenapp.R;
import com.example.truyenapp.response.ChapterResponse;

import java.util.List;

public class ChapterManagementAdapter extends RecyclerView.Adapter<ChapterManagementAdapter.QLChapterViewHolder> {
    private Context context;
    private List<ChapterResponse> list;

    public ChapterManagementAdapter(Context context, List<ChapterResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QLChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_management_item, parent, false);
        return new ChapterManagementAdapter.QLChapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QLChapterViewHolder holder, int position) {
        ChapterResponse chapter = list.get(position);
        if (chapter == null) {
            return;
        }

        holder.tv_idqlthongke.setText("" + chapter.getId());
        holder.tv_qltktentruyen.setText(chapter.getName());
        holder.ll_rcv_qlthongke.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowThongTinChapter.class);
            intent.putExtra("id_chapter", chapter.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class QLChapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_idqlthongke, tv_qltktentruyen;
        private LinearLayout ll_rcv_qlthongke;

        public QLChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idqlthongke = itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen = itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qlthongke = itemView.findViewById(R.id.ll_rcv_qlthongke);
        }
    }
}
