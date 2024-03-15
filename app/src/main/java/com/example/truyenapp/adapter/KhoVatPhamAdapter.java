package com.example.truyenapp.adapter;

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
import com.example.truyenapp.model.TaiKhoan;
import com.example.truyenapp.model.VatPham;

import java.util.List;

public class KhoVatPhamAdapter extends RecyclerView.Adapter<KhoVatPhamAdapter.KhoVatPhamViewHolder>{
    private Context context;
    private List<VatPham> list;
    private TaiKhoan taiKhoan;
    private Database db;

    public KhoVatPhamAdapter(Context context, List<VatPham> list, TaiKhoan taiKhoan, Database db) {
        this.context = context;
        this.list = list;
        this.taiKhoan = taiKhoan;
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
        VatPham vatPham=list.get(position);
        if(vatPham==null){
            return;
        }

        Glide.with(this.context).load(vatPham.getLinkanh()).into(holder.img_khovatpham);
        holder.tv_tenvatpham.setText(vatPham.getTenvatpham());
        holder.tv_diem.setText("Điểm: "+vatPham.getDiem());
        holder.bt_sudungvatpham.setOnClickListener(view -> {
            Boolean kt=db.checkLinkAnh(taiKhoan,vatPham.getLinkanh());
            if(kt==false){
                Boolean updateLinkAnh=db.updateLinkAnh(taiKhoan,vatPham.getLinkanh());
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
            tv_diem=itemView.findViewById(R.id.tv_diem);
            tv_tenvatpham=itemView.findViewById(R.id.tv_tenvatpham);
            bt_sudungvatpham=itemView.findViewById(R.id.bt_sudungvatpham);

        }
    }
}
