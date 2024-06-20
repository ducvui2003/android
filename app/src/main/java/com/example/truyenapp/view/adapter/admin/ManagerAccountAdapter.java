package com.example.truyenapp.view.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.admin.ShowInformationOfAccount;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.response.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerAccountAdapter extends RecyclerView.Adapter<ManagerAccountAdapter.ManagerAccountViewHolder>{
    private Context context;
    private List<Account> list;
    private UserAPI userAPI;
    public ManagerAccountAdapter(Context context, List<Account> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ManagerAccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qltaikhoan,parent,false);
        userAPI = RetrofitClient.getInstance(context).create(UserAPI.class);
        return new ManagerAccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerAccountViewHolder holder, int position) {
        Account account = list.get(position);
//        if(account ==null){
//            return;
//        }
        holder.id.setText(String.valueOf(account.getId()));
        int status= account.getStatus();
        if(status == 0){
            holder.btnAppear.setVisibility(View.GONE);
            holder.btnHide.setVisibility(View.VISIBLE);
            holder.status.setText("Hoạt động");
        }else {
            holder.btnAppear.setVisibility(View.VISIBLE);
            holder.btnHide.setVisibility(View.GONE);
            holder.linearLayoutManagerAccount.setBackgroundColor(Color.GRAY);
            holder.status.setText("Bị khóa");
        }
        holder.email.setText(account.getEmail());

        holder.btnHide.setOnClickListener(view -> {
            userAPI.block(account.getId()).enqueue(new Callback<APIResponse<Void>>() {
                @Override
                public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                    if (response.isSuccessful()) {
                        holder.btnHide.setVisibility(View.GONE);
                        holder.btnAppear.setVisibility(View.VISIBLE);
                        holder.linearLayoutManagerAccount.setBackgroundColor(Color.GRAY);
                        holder.status.setText("Bị khóa");
                    }
                }
                @Override
                public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });

        });

        holder.btnAppear.setOnClickListener(view -> {
            userAPI.unblock(account.getId()).enqueue(new Callback<APIResponse<Void>>() {
                @Override
                public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                    if (response.isSuccessful()) {
                        holder.btnAppear.setVisibility(View.GONE);
                        holder.btnHide.setVisibility(View.VISIBLE);
                        holder.linearLayoutManagerAccount.setBackgroundColor(Color.rgb(255, 87, 34));
                        holder.status.setText("Hoạt động");
                    }
                }
                @Override
                public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });

        });
        holder.linearLayoutManagerAccount.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowInformationOfAccount.class);
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

    public class ManagerAccountViewHolder extends RecyclerView.ViewHolder{
        private TextView id, email, status;
        private Button btnHide, btnAppear;
        private LinearLayout linearLayoutManagerAccount;

        public ManagerAccountViewHolder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.tv_idqltaikhoan);
            email =itemView.findViewById(R.id.tv_Email);
            status =itemView.findViewById(R.id.tv_trangthaiqltaikhoan);
            btnHide =itemView.findViewById(R.id.bt_anqltaikhoan);
            btnAppear =itemView.findViewById(R.id.bt_hienqltaikhoan);
            linearLayoutManagerAccount =itemView.findViewById(R.id.ll_rcv_qltaikhoan);
        }
    }
}