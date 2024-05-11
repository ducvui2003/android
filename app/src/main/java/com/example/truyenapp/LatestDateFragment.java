package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.model.Story;
import com.example.truyenapp.view.activity.CategoryActivity;
import com.example.truyenapp.view.adapter.TheLoaiAdapter;

import java.util.ArrayList;

public class LatestDateFragment extends Fragment{

    View view;
    CategoryActivity categoryActivity;
    Database db;
    Story story;
    public RecyclerView rcv;
    public TheLoaiAdapter rcv_adapter;
    String email;

    public String _theloai;


    public LatestDateFragment() {
        // Required empty public constructor
    }

    public static LatestDateFragment newInstance(String param1, String param2) {
        LatestDateFragment fragment = new LatestDateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_theloai_new, container, false);

        db=new Database(getActivity());
        init();
        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");

        categoryActivity = (CategoryActivity) getActivity();
//        hienThiTheoTheLoai();

        return view;
    }

    public void recyclerViewTruyen() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        //select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1'
        String lenhSqlite_theloai="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' and truyen.theloai='"+_theloai+"' order by chapter.ngaydang desc";
        String lenhSqlite_theloai1="select * from truyen where theloai='"+_theloai+"'";
        ArrayList<ClassifyStory> truyens=db.getListPLTruyen(lenhSqlite_theloai);
        rcv_adapter=new TheLoaiAdapter(getActivity(),truyens,email);
        rcv.setAdapter(rcv_adapter);
    }

    public void init(){
        rcv=view.findViewById(R.id.rcv_theloai_new);

    }

//    public void hienThiTheoTheLoai(){
//        _theloai= categoryActivity.autoCompleteTextView.getText().toString();
//        recyclerViewTruyen();
//        categoryActivity.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item=adapterView.getItemAtPosition(i).toString();
//                _theloai=item;
//                recyclerViewTruyen();
//                Toast.makeText(getActivity().getApplicationContext(),"Thể loại: "+item,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    public void getBookLatestDate() {
//        SearchAPI searchAPI = RetrofitClient.getInstance(getContext()).create(SearchAPI.class);
//        searchAPI.search
//    }


}