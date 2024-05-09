package com.example.truyenapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.view.fragment.RankViewFragment;
import com.example.truyenapp.view.fragment.RankVoteFragment;

public class FragmentAdapterCategory extends FragmentStateAdapter {
    RankVoteFragment rankVoteFragment = new RankVoteFragment();
    RankViewFragment rankViewFragment = new RankViewFragment();


    public FragmentAdapterCategory(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setCategory(Integer categoryID) {
        rankViewFragment.setCategoryId(categoryID);
        rankVoteFragment.setCategoryId(categoryID);
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
        return new TheLoaiNewFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
