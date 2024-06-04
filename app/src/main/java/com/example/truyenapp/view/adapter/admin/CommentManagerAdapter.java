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
import com.example.truyenapp.api.CommentAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.enums.CommentState;
import com.example.truyenapp.model.Comment;
import com.example.truyenapp.request.CommentChangeRequest;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.utils.DialogEvent;
import com.example.truyenapp.utils.DialogHelper;
import com.example.truyenapp.view.viewHolder.CommentManagerViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentManagerAdapter extends RecyclerView.Adapter<CommentManagerViewHolder> {
    private Context context;
    private List<Comment> list;

    public CommentManagerAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommentManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_comment_management, parent, false);
        return new CommentManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentManagerViewHolder holder, int position) {
        Comment comment = list.get(position);
        if (comment == null) {
            return;
        }
        holder.setData(comment);
        holder.handleOnCLick();
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

}
