package com.example.truyenapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.view.adapter.InventoryViewModel;
import com.example.truyenapp.view.adapter.InventoryViewModelFactory;
import com.example.truyenapp.view.adapter.StoreAdapter;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class StoreFragment extends Fragment {

    View view;
    public RecyclerView rcv;
    public StoreAdapter adapter;
    List<Item> itemList = new ArrayList<>();
    @Getter
    private InventoryViewModel inventoryViewModel;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InventoryViewModelFactory factory = new InventoryViewModelFactory(requireContext());
        inventoryViewModel = new ViewModelProvider(requireActivity(), factory).get(InventoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rcv_linear, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        observeViewModel();
    }


    public void init() {
        rcv = view.findViewById(R.id.rcv_comic_card);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        adapter = new StoreAdapter(getActivity(), itemList, this);
        rcv.setAdapter(adapter);
    }

    public void observeViewModel() {
        inventoryViewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                if (items != null) {
                    itemList.clear();
                    itemList.addAll(items);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}