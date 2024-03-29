package com.example.truyenapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truyenapp.R;
import com.example.truyenapp.view.adapter.LuotXemApdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.ClassifyStory;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BXHLuotXemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BXHLuotXemFragment extends Fragment {

    View view;
    Database db;
    private RecyclerView rcv;
    private LuotXemApdapter rcv_adapter;
    String email;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BXHLuotXemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BXHLuotXemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BXHLuotXemFragment newInstance(String param1, String param2) {
        BXHLuotXemFragment fragment = new BXHLuotXemFragment();
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
        view= inflater.inflate(R.layout.fragment_b_x_h_luot_xem, container, false);
        db=new Database(getActivity());
        Anhxa();

        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        String lenhSqlite_theloai="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' order by thongke.tongluotxem desc, chapter.ngaydang desc";
        ArrayList<ClassifyStory> truyens=db.getListPLTruyen(lenhSqlite_theloai);
        rcv_adapter=new LuotXemApdapter(getActivity(),truyens,email);
        rcv.setAdapter(rcv_adapter);


        return view;
    }
    private void Anhxa(){
        rcv=view.findViewById(R.id.rcv_xh_view);
    }
}