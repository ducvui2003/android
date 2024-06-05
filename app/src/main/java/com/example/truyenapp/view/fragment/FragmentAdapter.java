package com.example.truyenapp.view.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    private int idComic;
    public  FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager,lifecycle);
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new ChapterFragment(idComic);
        }
        return new DetailFragment(idComic);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
