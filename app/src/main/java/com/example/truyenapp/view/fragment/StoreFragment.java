package com.example.truyenapp.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.truyenapp.R;
import com.example.truyenapp.view.activity.StoreActivity;
import com.example.truyenapp.view.adapter.CuaHangAdapter;

public class StoreFragment extends Fragment {

    View view;
    public RecyclerView rcv;
    public CuaHangAdapter rcv_adapter;
    Button bt_doivatpham;
    StoreActivity storeActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_comic_card_list, container, false);
        init();

        storeActivity = new StoreActivity();

        recyclerViewCuaHang();
        return view;
    }

    public void recyclerViewCuaHang() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        rcv.setAdapter(rcv_adapter);
    }


    public void init() {
        rcv = view.findViewById(R.id.rcv_cuahang);
        rcv_adapter = new CuaHangAdapter(getActivity(), null, null, null, storeActivity);
        rcv.setAdapter(rcv_adapter);
    }
}