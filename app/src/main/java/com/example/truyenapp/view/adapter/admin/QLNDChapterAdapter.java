package com.example.truyenapp.view.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.ContentOfChapter;

import java.util.ArrayList;

public class QLNDChapterAdapter extends RecyclerView.Adapter<QLNDChapterAdapter.QLNDChapterViewHolder>{
    private Context context;
    private ArrayList<ContentOfChapter> list;
    private Database db;

    public QLNDChapterAdapter(Context context, ArrayList<ContentOfChapter> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public QLNDChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke,parent,false);
        return new QLNDChapterAdapter.QLNDChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLNDChapterViewHolder holder, int position) {
        ContentOfChapter contentOfChapter =list.get(position);
        if(contentOfChapter ==null){
            return;
        }

        holder.tv_idqlthongke.setText(""+ contentOfChapter.getId());
        holder.tv_qltktentruyen.setText(contentOfChapter.getLinkImage());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class QLNDChapterViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_idqlthongke,tv_qltktentruyen;

        public QLNDChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idqlthongke=itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen=itemView.findViewById(R.id.tv_qltktentruyen);
        }
    }
}
