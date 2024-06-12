package com.example.truyenapp.paging;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;

import kotlin.jvm.internal.PropertyReference0Impl;
import lombok.Getter;

@Getter
public class LoadingViewHolder extends RecyclerView.ViewHolder {
    private ProgressBar progressBar;

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        this.progressBar = itemView.findViewById(R.id.progress_bar);
    }
}
