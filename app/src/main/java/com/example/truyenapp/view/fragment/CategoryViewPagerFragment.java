package com.example.truyenapp.view.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.LatestDateFragment;

public class CategoryViewPagerFragment extends FragmentStateAdapter {
    RankVoteFragment rankVoteFragment = new RankVoteFragment();
    RankViewFragment rankViewFragment = new RankViewFragment();
    LatestDateFragment latestDateFragment = new LatestDateFragment();


    public CategoryViewPagerFragment(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return rankVoteFragment;
            case 2:
                return rankViewFragment;
        }
        return latestDateFragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
