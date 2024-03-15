package com.example.truyenapp.adapter;

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
import com.example.truyenapp.model.PLTruyen;

import java.util.List;

public class LuotXemApdapter extends RecyclerView.Adapter<LuotXemApdapter.LuotXemViewHolder>{
    private Context context;
    private List<PLTruyen> list;
    private String email;

    public LuotXemApdapter(Context context, List<PLTruyen> list,String email) {
        this.context = context;
        this.list = list;
        this.email=email;
    }

    @NonNull
    @Override
    public LuotXemApdapter.LuotXemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_theloainew,parent,false);
        return new LuotXemApdapter.LuotXemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LuotXemApdapter.LuotXemViewHolder holder, int position) {
        PLTruyen truyen=list.get(position);
        if(truyen==null){
            return;
        }


        Glide.with(this.context).load(truyen.getLinkanh()).into(holder.img_theloai);
        holder.tv_tentruyen.setText(truyen.getTentruyen());
        holder.tv_pl.setText("Tổng lượt xem: "+truyen.getLuotxem());
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

    public class LuotXemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tentruyen,tv_pl;
        private LinearLayout ll_rcv_theloai;
        private ImageView img_theloai;

        public LuotXemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tentruyen=itemView.findViewById(R.id.tv_theloai_tentruyen);
            tv_pl=itemView.findViewById(R.id.tv_theloai_pl);
            ll_rcv_theloai=itemView.findViewById(R.id.ll_rcv_theloai);
            img_theloai=itemView.findViewById(R.id.img_theloai);
        }
    }
}
