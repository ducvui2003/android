package com.example.truyenapp.view.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.admin.CommentDetailManagerActivity;
import com.example.truyenapp.R;
import com.example.truyenapp.model.Comment;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

public class CommentManagerAdapter extends RecyclerView.Adapter<CommentManagerAdapter.ManagerCommentViewHolder> {
    private Context context;
    private ArrayList<Comment> list;

    public CommentManagerAdapter(Context context, ArrayList<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ManagerCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_comment_management, parent, false);
        return new ManagerCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerCommentViewHolder holder, int position) {
        Comment comment = list.get(position);
        if (comment == null) {
            return;
        }

        holder.id.setText("" + comment.getId());
        holder.name.setText("");
//        holder.chapter.setText(binhLuan.getIdChapter());
        int state = comment.getState();
        holder.btnAction.setTextColor(Color.WHITE);
        if (state != 0) {
            holder.state.setText("Hoạt động");
            holder.btnAction.setText("Khóa");
            holder.btnAction.setBackgroundColor(Color.RED);
        } else {
            holder.state.setText("Bị khóa");
            holder.btnAction.setText("Khóa");
            holder.btnAction.setBackgroundColor(Color.RED);
        }
        holder.container.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), CommentDetailManagerActivity.class);
            intent.putExtra("id_binhluan", comment.getId());
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

    public class ManagerCommentViewHolder extends RecyclerView.ViewHolder {
        private TextView id, state, name, chapter;
        private Button btnAction;
        private FlexboxLayout container;

        public ManagerCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_comment_manager_id);
            state = itemView.findViewById(R.id.tv_comment_manager_state);
            name = itemView.findViewById(R.id.tv_comment_manager_name);
            chapter = itemView.findViewById(R.id.tv_comment_manager_chapter);
            btnAction = itemView.findViewById(R.id.btn_comment_manager_action);
            container = itemView.findViewById(R.id.ll_rcv_qlbinhluan);
        }
    }
}
