package com.example.truyenapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.truyenapp.R;
import com.example.truyenapp.activity.CuaHang;
import com.example.truyenapp.adapter.CuaHangAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.TaiKhoan;
import com.example.truyenapp.model.VatPham;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuaHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuaHangFragment extends Fragment {

    View view;
    Database db;
    TaiKhoan taiKhoan;
    public RecyclerView rcv;
    public CuaHangAdapter rcv_adapter;
    String email;
    Button bt_doivatpham;
    CuaHang cuaHang;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CuaHangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuaHangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuaHangFragment newInstance(String param1, String param2) {
        CuaHangFragment fragment = new CuaHangFragment();
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
        view=inflater.inflate(R.layout.fragment_cua_hang, container, false);
        Anhxa();
        db=new Database(getActivity());

        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");
        taiKhoan=db.getTaiKhoan(email);
        cuaHang=new CuaHang();

        recyclerViewCuaHang();
        return view;
    }

    public void recyclerViewCuaHang() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        String lenhSqlite_vatpham="select * from vatpham where id not in (select idvatpham from doithuong where idtaikhoan="+taiKhoan.getId()+")";
        ArrayList<VatPham> vatPham=db.getVatPham(lenhSqlite_vatpham);
        rcv_adapter=new CuaHangAdapter(getActivity(),vatPham,taiKhoan,db, cuaHang);
        rcv.setAdapter(rcv_adapter);
    }

    private void reload(){
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0,0);
        startActivity(intent);
    }

    public void Anhxa(){
        rcv=view.findViewById(R.id.rcv_cuahang);
    }
}