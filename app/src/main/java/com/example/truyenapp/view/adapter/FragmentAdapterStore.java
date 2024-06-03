package com.example.truyenapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.view.fragment.StoreFragment;
import com.example.truyenapp.view.fragment.RedeemRewardFragment;
import com.example.truyenapp.view.fragment.RedeemRewardHistoryFragment;

public class FragmentAdapterStore extends FragmentStateAdapter {
    public FragmentAdapterStore(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new RedeemRewardFragment();
            case 2:
                return new RedeemRewardHistoryFragment();
        }
        return new StoreFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
