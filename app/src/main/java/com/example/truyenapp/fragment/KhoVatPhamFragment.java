package com.example.truyenapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truyenapp.R;
import com.example.truyenapp.adapter.KhoVatPhamAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.TaiKhoan;
import com.example.truyenapp.model.VatPham;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoVatPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoVatPhamFragment extends Fragment {

    View view;
    Database db;
    TaiKhoan taiKhoan;
    public RecyclerView rcv;
    public KhoVatPhamAdapter rcv_adapter;
    String email;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KhoVatPhamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KhoVatPhamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KhoVatPhamFragment newInstance(String param1, String param2) {
        KhoVatPhamFragment fragment = new KhoVatPhamFragment();
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
        view=inflater.inflate(R.layout.fragment_kho_vat_pham, container, false);

        Anhxa();
        db=new Database(getActivity());

        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");
        taiKhoan=db.getTaiKhoan(email);

        recyclerViewCuaHang();

        return view;
    }

    public void recyclerViewCuaHang() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        String lenhSqlite_vatpham="select * from vatpham where id in (select idvatpham from doithuong where idtaikhoan="+taiKhoan.getId()+")";
        ArrayList<VatPham> vatPham=db.getVatPham(lenhSqlite_vatpham);
        rcv_adapter=new KhoVatPhamAdapter(getActivity(),vatPham,taiKhoan,db);
        rcv.setAdapter(rcv_adapter);
    }

    public void Anhxa(){
        rcv=view.findViewById(R.id.rcv_khovatpham);
    }

}