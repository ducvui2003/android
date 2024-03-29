package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.model.ContentOfChapter;

import java.util.List;

public class DocChapterAdapter extends RecyclerView.Adapter<DocChapterAdapter.DocChapterViewHolder> {

    private List<ContentOfChapter> list;
    private Context context;

    public DocChapterAdapter(List<ContentOfChapter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DocChapterAdapter.DocChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_docchapter,parent,false);
        return new DocChapterAdapter.DocChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocChapterAdapter.DocChapterViewHolder holder, int position) {
        ContentOfChapter contentOfChapter =list.get(position);
        if(contentOfChapter ==null){
            return;
        }

        Glide.with(this.context).load(contentOfChapter.getLinkImage()).into(holder.img_docchapter);
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class DocChapterViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_docchapter;

        public DocChapterViewHolder(@NonNull View itemView) {
            super(itemView);

            img_docchapter=itemView.findViewById(R.id.img_docchapter);
        }
    }
}
