package com.example.truyenapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.view.fragment.RankViewFragment;
import com.example.truyenapp.view.fragment.RankVoteFragment;

public class RankViewPager extends FragmentStateAdapter {

    public RankViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new RankVoteFragment();
            case 1:
                return new RankViewFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
