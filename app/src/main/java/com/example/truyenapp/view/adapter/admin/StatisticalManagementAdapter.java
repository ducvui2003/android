package com.example.truyenapp.view.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.admin.StatisticalInformation;
import com.example.truyenapp.R;
import com.example.truyenapp.response.BookResponse;

import java.util.List;

public class StatisticalManagementAdapter extends RecyclerView.Adapter<StatisticalManagementAdapter.QLThongKeViewHolder> {
    private Context context;
    private List<BookResponse> list;

    public StatisticalManagementAdapter(Context context, List<BookResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QLThongKeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_book_management, parent, false);
        return new StatisticalManagementAdapter.QLThongKeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QLThongKeViewHolder holder, int position) {
        BookResponse bookResponse = list.get(position);
        if (bookResponse == null) {
            return;
        }

        holder.tv_idqlthongke.setText("" + bookResponse.getId());
        holder.tv_qltktentruyen.setText(bookResponse.getName());
        holder.ll_rcv_qlthongke.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), StatisticalInformation.class);
            intent.putExtra("id_thongke", bookResponse.getId());
            intent.putExtra("name", bookResponse.getName());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class QLThongKeViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_idqlthongke, tv_qltktentruyen;
        private LinearLayout ll_rcv_qlthongke;

        public QLThongKeViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_idqlthongke = itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen = itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qlthongke = itemView.findViewById(R.id.ll_rcv_qlthongke);
        }
    }
}
