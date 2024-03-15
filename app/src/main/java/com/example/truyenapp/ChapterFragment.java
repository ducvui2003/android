package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.adapter.ChapterAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Chapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChapterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChapterFragment extends Fragment {

    Database db;
    Chapter chapter;
    String email;
    View view;
    TextView tv_chapter,tv_ngaydang,tv_luotxem;
    private RecyclerView rcv;
    private ChapterAdapter rcv_adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChapterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChapterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChapterFragment newInstance(String param1, String param2) {
        ChapterFragment fragment = new ChapterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ListView listView;
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
        view= inflater.inflate(R.layout.fragment_chapter, container, false);

        db=new Database(getActivity());
        Anhxa();


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");

        int id_truyen=intent.getIntExtra("id_truyen",0);
        String lenhSqlite_chapter="select * from chapter where idtruyen="+id_truyen;
        ArrayList<Chapter> chapters=db.getChapter(lenhSqlite_chapter);
        rcv_adapter=new ChapterAdapter(getActivity(),chapters,email);
        rcv.setAdapter(rcv_adapter);

        return view;
    }
    private void Anhxa(){

        tv_chapter=view.findViewById(R.id.tv_chapter);
        tv_ngaydang=view.findViewById(R.id.tv_ngaydang);
        tv_luotxem=view.findViewById(R.id.tv_luotxem);
        rcv=view.findViewById(R.id.rcv_chapter);

    }
}