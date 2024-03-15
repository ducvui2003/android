package com.example.truyenapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.truyenapp.fragment.GoiYTruyenFragment;
import com.example.truyenapp.fragment.LichSuDocTruyenFragment;

public class FragmentAdapterTuSach extends FragmentStateAdapter {

    public  FragmentAdapterTuSach(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager,lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new GoiYTruyenFragment();
        }
        return new LichSuDocTruyenFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
