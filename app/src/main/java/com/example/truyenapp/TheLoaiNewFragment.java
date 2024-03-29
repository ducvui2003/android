package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.truyenapp.model.Story;
import com.example.truyenapp.view.adapter.TheLoaiAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.view.fragment.BXHVoteFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BXHVoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class TheLoaiNewFragment extends Fragment{

    View view;
    Category category;
    Database db;
    Story story;
    public RecyclerView rcv;
    public TheLoaiAdapter rcv_adapter;
    String email;

    public String _theloai;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TheLoaiNewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BXHVoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TheLoaiNewFragment newInstance(String param1, String param2) {
        TheLoaiNewFragment fragment = new TheLoaiNewFragment();
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
        view= inflater.inflate(R.layout.fragment_theloai_new, container, false);

        db=new Database(getActivity());
        Anhxa();
        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");

        category = (Category) getActivity();
        hienThiTheoTheLoai();

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

    public void Anhxa(){
        rcv=view.findViewById(R.id.rcv_theloai_new);
    }

    public void hienThiTheoTheLoai(){
        _theloai= category.autoCompleteTextView.getText().toString();
        recyclerViewTruyen();
        category.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                _theloai=item;
                recyclerViewTruyen();
                Toast.makeText(getActivity().getApplicationContext(),"Thể loại: "+item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}