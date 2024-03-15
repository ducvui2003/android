package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.truyenapp.adapter.BinhLuanTruyenAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.BinhLuan;
import com.example.truyenapp.model.TaiKhoan;
import com.example.truyenapp.model.ThongKe;
import com.example.truyenapp.model.Truyen;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChiTietFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChiTietFragment extends Fragment {

    Database db;
    Truyen truyen;
    View view;
    TextView tv_danhgia,tv_tongluotxem,tv_tongbinhluan,tv_mota;
    ThongKe thongKe;
    TaiKhoan taiKhoan;
    String email;
    private RecyclerView rcv_binhluan;
    private BinhLuanTruyenAdapter rcv_adapter;
    int id_truyen;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChiTietFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChiTietFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChiTietFragment newInstance(String param1, String param2) {
        ChiTietFragment fragment = new ChiTietFragment();
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
        view= inflater.inflate(R.layout.fragment_chi_tiet, container, false);

        db=new Database(getActivity());
        Anhxa();

        Intent intent=getActivity().getIntent();
        id_truyen=intent.getIntExtra("id_truyen",0);
        email=intent.getStringExtra("email");

        String lenhSQLite="select * from truyen where id="+id_truyen;
        ArrayList<Truyen> listtruyen=db.getTruyen(lenhSQLite);
        truyen=listtruyen.get(0);
        tv_mota.setText(truyen.getMota());

//        thongKe=db.getThongKe(id_truyen);
//        tv_danhgia.setText(""+thongKe.getSosaotb());
//
//        tv_tongluotxem.setText(""+thongKe.getTongluotxem());
        tv_danhgia.setText(""+db.TBDanhGiaTruyen(id_truyen));
        tv_tongluotxem.setText(""+db.TongLuotXemTruyen(id_truyen));

        int TongBinhLuan=db.getTongBinhLuanTruyen(id_truyen);
        tv_tongbinhluan.setText(""+TongBinhLuan);

        recyclerViewBinhLuan();

        return view;
    }

    private void Anhxa(){

        tv_danhgia= view.findViewById(R.id.tv_danhgia);
        tv_tongluotxem=view.findViewById(R.id.tv_tongluotxem);
        tv_tongbinhluan=view.findViewById(R.id.tv_tongbinhluan);
        tv_mota=view.findViewById(R.id.tv_motatruyen);
        rcv_binhluan=view.findViewById(R.id.rcv_binhluan);

    }

    private void recyclerViewBinhLuan(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv_binhluan.setLayoutManager(linearLayoutManager);
        ArrayList<BinhLuan> listBinhLuan=db.getBinhLuanTruyen(id_truyen);
        rcv_adapter=new BinhLuanTruyenAdapter(getActivity(),listBinhLuan,db);
        rcv_binhluan.setAdapter(rcv_adapter);
    }
}