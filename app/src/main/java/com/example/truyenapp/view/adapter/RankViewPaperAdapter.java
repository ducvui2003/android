package com.example.truyenapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.view.fragment.RankViewFragment;
import com.example.truyenapp.view.fragment.RankVoteFragment;

public class RankViewPaperAdapter extends FragmentStateAdapter {

    public RankViewPaperAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new RankViewFragment();
            case 1:
                return new RankVoteFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
