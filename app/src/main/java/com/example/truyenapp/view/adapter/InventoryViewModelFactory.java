package com.example.truyenapp.view.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class InventoryViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    public InventoryViewModelFactory(Context context) {
        this.context = context.getApplicationContext(); // Use application context to avoid memory leaks
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InventoryViewModel.class)) {
            return (T) new InventoryViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
