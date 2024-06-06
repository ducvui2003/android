package com.example.truyenapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.utils.Format;
import com.example.truyenapp.view.viewHolder.InventoryViewHolder;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryViewHolder> {
    private Context context;
    private List<Item> list;

    public InventoryAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_inventory, parent, false);
        return new InventoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Item item = list.get(position);
        if (item == null) {
            return;
        }
        holder.setData(item);
//            Boolean kt=db.checkLinkAnh(account, item.getImage());
//            if(kt==false){
//                Boolean updateLinkAnh=db.updateLinkAnh(account, item.getImage());
//                if(updateLinkAnh==true){
//                    Toast.makeText(this.context,"Sử dụng avatar thành công",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(this.context,"Đã có lỗi xảy ra. Vui lòng thử lại sau",Toast.LENGTH_SHORT).show();
//                }
//            }else {
//                Toast.makeText(this.context,"Hiện tại bạn đang sử dụng avater này",Toast.LENGTH_SHORT).show();
//            }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
