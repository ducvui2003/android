package com.example.truyenapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truyenapp.adapter.FragmentAdapterTuSach;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TuSachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TuSachFragment extends Fragment {

    View view;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapterTuSach adapterTuSach;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TuSachFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TuSachFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TuSachFragment newInstance(String param1, String param2) {
        TuSachFragment fragment = new TuSachFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_tu_sach, container, false);
        Anhxa();

        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        adapterTuSach=new FragmentAdapterTuSach(fragmentManager,getLifecycle());
        pager2.setAdapter(adapterTuSach);

        tabLayout.addTab(tabLayout.newTab().setText("Lịch sử đọc"));
        tabLayout.addTab(tabLayout.newTab().setText("Gợi ý truyện"));



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return view;
    }

    private void Anhxa(){
        tabLayout=view.findViewById(R.id.tab_layout_tusach);
        pager2=view.findViewById(R.id.view_pager2_tusach);
    }

}