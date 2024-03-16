package com.example.truyenapp.view.adapter.admin;

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
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Chapter;

import java.util.ArrayList;

public class QLChapterAdapter extends RecyclerView.Adapter<QLChapterAdapter.QLChapterViewHolder>{
    private Context context;
    private ArrayList<Chapter> list;
    private Database db;

    public QLChapterAdapter(Context context, ArrayList<Chapter> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public QLChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke,parent,false);
        return new QLChapterAdapter.QLChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLChapterViewHolder holder, int position) {
        Chapter chapter=list.get(position);
        if(chapter==null){
            return;
        }

        holder.tv_idqlthongke.setText(""+chapter.getId());
        holder.tv_qltktentruyen.setText(chapter.getTenchapter());
        holder.ll_rcv_qlthongke.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinChapter.class);
            intent.putExtra("id_chapter",chapter.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class QLChapterViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_idqlthongke,tv_qltktentruyen;
        private LinearLayout ll_rcv_qlthongke;

        public QLChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idqlthongke=itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen=itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qlthongke=itemView.findViewById(R.id.ll_rcv_qlthongke);
        }
    }
}
