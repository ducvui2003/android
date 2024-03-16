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
import com.example.truyenapp.view.activity.CuaHang;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.model.Item;

import java.util.List;

public class CuaHangAdapter extends RecyclerView.Adapter<CuaHangAdapter.CuaHangViewHolder>{
    private Context context;
    private List<Item> list;
    private Account account;
    private Database db;
    private CuaHang cuaHang;

    public CuaHangAdapter(Context context, List<Item> list, Account account, Database db, CuaHang cuaHang) {
        this.context = context;
        this.list = list;
        this.account = account;
        this.db=db;
        this.cuaHang = cuaHang;
    }

    @NonNull
    @Override
    public CuaHangAdapter.CuaHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_vatpham,parent,false);
        return new CuaHangAdapter.CuaHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuaHangAdapter.CuaHangViewHolder holder, int position) {
        Item item =list.get(position);
        if(item ==null){
            return;
        }


        Glide.with(this.context).load(item.getLinkImage()).into(holder.img_vatpham);
        holder.tv_tenvatpham.setText(item.getNameItem());
        holder.tv_diem.setText("Điểm: "+ item.getPoint());
        holder.bt_doivatpham.setOnClickListener(view -> {
            int kt=db.checkVatPham(item.getId(), account);
            if(kt==1){
                Toast.makeText(this.context,"Vật phẩm đã có trong kho",Toast.LENGTH_SHORT).show();
            }
            else{
                int kt1=db.checkDoiVatPham(item.getId(), account);
                if(kt1==1){
                    Boolean doithuong=db.insertDoiThuong(item.getId(), account.getId());
                    if(doithuong==true){
                        db.updateDiemThuong(account,-(item.getPoint()));
                        cuaHang.reload();
                        Toast.makeText(this.context,"Đổi vật phẩm thành công",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this.context,"Xảy ra lỗi. Vui lòng thử lại sau!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this.context,"Không đủ điểm để đổi. Vui lòng tích thêm điểm",Toast.LENGTH_SHORT).show();
                }
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

    public class CuaHangViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_vatpham;
        private TextView tv_tenvatpham,tv_diem;
        private Button bt_doivatpham;

        public CuaHangViewHolder(@NonNull View itemView) {
            super(itemView);
            img_vatpham=itemView.findViewById(R.id.img_vatpham);
            tv_diem=itemView.findViewById(R.id.tv_diem);
            tv_tenvatpham=itemView.findViewById(R.id.tv_tenvatpham);
            bt_doivatpham=itemView.findViewById(R.id.bt_doivatpham);
        }
    }
}
