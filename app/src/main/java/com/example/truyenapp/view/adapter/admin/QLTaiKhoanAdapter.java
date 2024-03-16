package com.example.truyenapp.view.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.admin.ShowThongTinTaiKhoan;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;

import java.util.ArrayList;

public class QLTaiKhoanAdapter extends RecyclerView.Adapter<QLTaiKhoanAdapter.QLTaiKhoanViewHolder>{
    private Context context;
    private ArrayList<Account> list;
    private Database db;

    public QLTaiKhoanAdapter(Context context, ArrayList<Account> list, Database db) {
        this.context = context;
        this.list = list;
        this.db=db;
    }

    @NonNull
    @Override
    public QLTaiKhoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qltaikhoan,parent,false);
        return new QLTaiKhoanAdapter.QLTaiKhoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLTaiKhoanViewHolder holder, int position) {
        Account account =list.get(position);
        if(account ==null){
            return;
        }

        holder.tv_id.setText(""+ account.getId());
        int trangthai= account.getAccoutType();
        if(trangthai!=2){
            holder.bt_hien.setVisibility(View.GONE);
            holder.bt_an.setVisibility(View.VISIBLE);
            holder.tv_trangthai.setText("Hoạt động");
        }else {
            holder.bt_hien.setVisibility(View.VISIBLE);
            holder.bt_an.setVisibility(View.GONE);
            holder.tv_trangthai.setText("Bị khóa");
        }
        holder.tv_email.setText(account.getEmail());
        holder.bt_an.setOnClickListener(view -> {
            db.updateTrangThai(account.getId(),2);
            holder.bt_hien.setVisibility(View.VISIBLE);
            holder.bt_an.setVisibility(View.GONE);
            holder.tv_trangthai.setText("Bị khóa");
        });
        holder.bt_hien.setOnClickListener(view -> {
            db.updateTrangThai(account.getId(),0);
            holder.bt_hien.setVisibility(View.GONE);
            holder.bt_an.setVisibility(View.VISIBLE);
            holder.tv_trangthai.setText("Hoạt động");
        });
        holder.ll_rcv_qltaikhoan.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinTaiKhoan.class);
            intent.putExtra("email", account.getEmail());
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

    public class QLTaiKhoanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id,tv_email,tv_trangthai;
        private Button bt_an,bt_hien;
        private LinearLayout ll_rcv_qltaikhoan;

        public QLTaiKhoanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id=itemView.findViewById(R.id.tv_idqltaikhoan);
            tv_email=itemView.findViewById(R.id.tv_emailqltaikhoan);
            tv_trangthai=itemView.findViewById(R.id.tv_trangthaiqltaikhoan);
            bt_an=itemView.findViewById(R.id.bt_anqltaikhoan);
            bt_hien=itemView.findViewById(R.id.bt_hienqltaikhoan);
            ll_rcv_qltaikhoan=itemView.findViewById(R.id.ll_rcv_qltaikhoan);
        }
    }
}
