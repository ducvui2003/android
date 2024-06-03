package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.model.Item;

import java.util.List;

public class KhoVatPhamAdapter extends RecyclerView.Adapter<KhoVatPhamAdapter.KhoVatPhamViewHolder>{
    private Context context;
    private List<Item> list;
    private Account account;
    private Database db;

    public KhoVatPhamAdapter(Context context, List<Item> list, Account account, Database db) {
        this.context = context;
        this.list = list;
        this.account = account;
        this.db = db;
    }

    @NonNull
    @Override
    public KhoVatPhamAdapter.KhoVatPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_khovatpham,parent,false);
        return new KhoVatPhamAdapter.KhoVatPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoVatPhamAdapter.KhoVatPhamViewHolder holder, int position) {
        Item item =list.get(position);
        if(item ==null){
            return;
        }

        Glide.with(this.context).load(item.getImage()).into(holder.img_khovatpham);
        holder.tv_tenvatpham.setText(item.getNameItem());
        holder.tv_diem.setText("Điểm: "+ item.getPoint());
        holder.bt_sudungvatpham.setOnClickListener(view -> {
            Boolean kt=db.checkLinkAnh(account, item.getImage());
            if(kt==false){
                Boolean updateLinkAnh=db.updateLinkAnh(account, item.getImage());
                if(updateLinkAnh==true){
                    Toast.makeText(this.context,"Sử dụng avatar thành công",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this.context,"Đã có lỗi xảy ra. Vui lòng thử lại sau",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this.context,"Hiện tại bạn đang sử dụng avater này",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class KhoVatPhamViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_khovatpham;
        private TextView tv_tenvatpham,tv_diem;
        private Button bt_sudungvatpham;

        public KhoVatPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            img_khovatpham=itemView.findViewById(R.id.img_khovatpham);
            tv_diem=itemView.findViewById(R.id.tv_store_item_score);
            tv_tenvatpham=itemView.findViewById(R.id.tv_store_item_name);
            bt_sudungvatpham=itemView.findViewById(R.id.bt_sudungvatpham);

        }
    }
}
