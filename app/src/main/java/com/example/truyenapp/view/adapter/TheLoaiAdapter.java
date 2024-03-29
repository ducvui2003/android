package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.CTTruyen;
import com.example.truyenapp.R;
import com.example.truyenapp.model.ClassifyStory;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder> {
    private Context context;
    private List<ClassifyStory> list;
    private String email;

    public TheLoaiAdapter(Context context, List<ClassifyStory> list, String email) {
        this.context = context;
        this.list = list;
        this.email=email;
    }

    @NonNull
    @Override
    public TheLoaiAdapter.TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_theloainew,parent,false);
        return new TheLoaiAdapter.TheLoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.TheLoaiViewHolder holder, int position) {
        ClassifyStory truyen=list.get(position);
        if(truyen==null){
            return;
        }


        Glide.with(this.context).load(truyen.getLinkImage()).into(holder.img_theloai);
        holder.tv_tentruyen.setText(truyen.getNameStory());
        holder.tv_pl.setText("Ngày đăng: "+truyen.getPostingDate());
        holder.ll_rcv_theloai.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra("id_truyen",truyen.getId());
            intent.putExtra("email",email);
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

    public class TheLoaiViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_tentruyen,tv_pl;
        private LinearLayout ll_rcv_theloai;
        private ImageView img_theloai;


        public TheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tentruyen=itemView.findViewById(R.id.tv_theloai_tentruyen);
            tv_pl=itemView.findViewById(R.id.tv_theloai_pl);
            ll_rcv_theloai=itemView.findViewById(R.id.ll_rcv_theloai);
            img_theloai=itemView.findViewById(R.id.img_theloai);
        }
    }
}
