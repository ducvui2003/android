package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Comment;
import com.example.truyenapp.model.Account;

import java.util.List;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanViewHolder>{
    private Context context;
    private List<Comment> list;
    private Database db;
    private Account account;

    public BinhLuanAdapter(Context context, List<Comment> list, Database db, Account account) {
        this.context = context;
        this.list = list;
        this.db = db;
        this.account = account;
    }

    @NonNull
    @Override
    public BinhLuanAdapter.BinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_binhluan,parent,false);
        return new BinhLuanAdapter.BinhLuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanAdapter.BinhLuanViewHolder holder, int position) {
        Comment binhLuan=list.get(position);
        if(binhLuan==null){
            return;
        }

        Glide.with(this.context).load(account.getLinkImage()).into(holder.img_avatar);
        String email=db.getEmail(binhLuan.getIdAccount());
        holder.tv_taikhoan_bl.setText(email);
        holder.tv_nd_bl.setText(binhLuan.getContent());
        holder.tv_ngaybinhluan.setText(binhLuan.getPostingDay());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class BinhLuanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_taikhoan_bl,tv_nd_bl,tv_ngaybinhluan,tv_;
        private ImageView img_avatar;

        public BinhLuanViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nd_bl=itemView.findViewById(R.id.tv_nd_bl);
            tv_taikhoan_bl=itemView.findViewById(R.id.tv_taikhoan_bl);
            tv_ngaybinhluan=itemView.findViewById(R.id.tv_ngaybinhluan);
            img_avatar=itemView.findViewById(R.id.image_item_comment_avatar);
        }
    }
}
