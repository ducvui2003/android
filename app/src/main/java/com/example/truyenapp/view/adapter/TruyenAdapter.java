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
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.Comic;
import com.example.truyenapp.view.activity.DetailComicActivity;
import com.example.truyenapp.R;

import java.util.List;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.TruyenViewHolder>{
    private Context context;
    private List<Comic> list;
    private String email;

    public TruyenAdapter(Context context, String email) {
        this.context = context;
        this.email = email;
    }

    public TruyenAdapter(List<Comic> list, Context context, String email)
    {
        this.list=list;
        this.context=context;
        this.email=email;
    }
    public void setData(List<Comic> comicList) {
        this.list = comicList;
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TruyenAdapter.TruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
        return new TruyenAdapter.TruyenViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TruyenAdapter.TruyenViewHolder holder, int position) {
        Comic comic =list.get(position);
        if(comic ==null){
            return;
        }

        Glide.with(this.context).load(comic.getLinkImage()).into(holder.imgtruyen);
        holder.tv_tentruyen.setText(comic.getNameStory());
        holder.ll_rcv.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), DetailComicActivity.class);
            intent.putExtra(BundleConstraint.ID_COMIC, comic.getId());
//            intent.putExtra("email",email);
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

    public class TruyenViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgtruyen;
        private TextView tv_tentruyen;
        private LinearLayout ll_rcv;
        public TruyenViewHolder(@NonNull View itemView) {
            super(itemView);

            imgtruyen=itemView.findViewById(R.id.img_truyen);
            tv_tentruyen=itemView.findViewById(R.id.tv_title);
            ll_rcv=itemView.findViewById(R.id.ll_rcv);
        }
    }
}
