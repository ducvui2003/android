package com.example.truyenapp.view.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.view.fragment.ChapterFragment;
import com.example.truyenapp.view.fragment.ChiTietFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public  FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager,lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new ChapterFragment();
        }
        return new ChiTietFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
