package com.example.truyenapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapterBXH extends FragmentStateAdapter {

    public  FragmentAdapterBXH(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager,lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new BXHLuotXemFragment();
        }
        return new BXHVoteFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
