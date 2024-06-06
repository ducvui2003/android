package com.example.truyenapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.view.fragment.StoreFragment;
import com.example.truyenapp.view.fragment.InventoryFragment;

public class FragmentAdapterStore extends FragmentStateAdapter {
    public FragmentAdapterStore(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new StoreFragment();
            case 1:
                return new InventoryFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
