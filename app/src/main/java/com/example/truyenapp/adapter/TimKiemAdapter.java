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
import com.example.truyenapp.model.Model_TimKiem;

import java.util.List;

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.TimKiemViewHolder>{
    private Context context;
    private List<Model_TimKiem> list;
    private String email;

    public TimKiemAdapter(Context context, List<Model_TimKiem> list,String email) {
        this.context = context;
        this.list = list;
        this.email=email;
    }

    @NonNull
    @Override
    public TimKiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_timkiem,parent,false);
        return new TimKiemAdapter.TimKiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimKiemViewHolder holder, int position) {
        Model_TimKiem truyen=list.get(position);
        if(truyen==null){
            return;
        }


        Glide.with(this.context).load(truyen.getLinkanh()).into(holder.img_timkiem);

        holder.tv_timkiem_tentruyen.setText(truyen.getTentruyen());
        holder.tv_timkiem_lx.setText("Lượt xem: "+truyen.getLuotxem());
        holder.tv_timkiem_ch.setText("Chapter: "+truyen.getChapter());
        holder.tv_timkiem_dg.setText("Đánh giá: "+truyen.getDanhgia());
        holder.tv_timkiem_theloai.setText(truyen.getTheloai());
        holder.ll_rcv_timkiem.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra("email",email);
            intent.putExtra("id_truyen",truyen.getId());
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

    public class TimKiemViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_timkiem_tentruyen,tv_timkiem_lx,tv_timkiem_dg,tv_timkiem_ch, tv_timkiem_theloai;
        private LinearLayout ll_rcv_timkiem;
        private ImageView img_timkiem;

        public TimKiemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_timkiem_tentruyen=itemView.findViewById(R.id.tv_timkiem_tentruyen);
            tv_timkiem_dg=itemView.findViewById(R.id.tv_timkiem_dg);
            tv_timkiem_lx=itemView.findViewById(R.id.tv_timkiem_lx);
            tv_timkiem_ch=itemView.findViewById(R.id.tv_timkiem_ch);
            img_timkiem=itemView.findViewById(R.id.img_timkiem);
            ll_rcv_timkiem=itemView.findViewById(R.id.ll_rcv_timkiem);
            tv_timkiem_theloai=itemView.findViewById(R.id.tv_timkiem_theloai);
        }
    }
}
