package com.example.truyenapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.CTTruyen;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.LichSuDocTruyen;
import com.example.truyenapp.model.PLTruyen;
import com.example.truyenapp.model.TaiKhoan;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoiYTruyenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoiYTruyenFragment extends Fragment implements View.OnClickListener{

    TaiKhoan taiKhoan;
    String email;
    Database db;
    TextView tv_tentruyenmoi,tv_tentruyenluotxem,tv_tentruyendanhgia, tv_theloaimoi,tv_theloailuotxem,tv_theloaidanhgia,tv_ngaydang,tv_luotxem,tv_danhgia;
    ImageView img_truyenmoi,img_truyenluotxem,img_truyendanhgia;
    View view;
    CardView cv_truyenmoi,cv_truyenluotxem,cv_truyendanhgia;
    int idmoi,idluotxem,iddanhgia;
    String theloai;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GoiYTruyenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoiYTruyenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoiYTruyenFragment newInstance(String param1, String param2) {
        GoiYTruyenFragment fragment = new GoiYTruyenFragment();
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
        view= inflater.inflate(R.layout.fragment_goi_y_truyen, container, false);
        Anhxa();
        db=new Database(getActivity());
        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");
        taiKhoan=db.getTaiKhoan(email);

        setData();
        setOnClickListener();

        return view;
    }

    public void Anhxa(){
        tv_tentruyenmoi =view.findViewById(R.id.tv_tentruyenmoi);
        tv_tentruyenluotxem=view.findViewById(R.id.tv_tentruyenluotxem);
        tv_tentruyendanhgia=view.findViewById(R.id.tv_tentruyendanhgia);
        tv_theloaimoi=view.findViewById(R.id.tv_theloaimoi);
        tv_theloailuotxem=view.findViewById(R.id.tv_theloailuotxem);
        tv_theloaidanhgia=view.findViewById(R.id.tv_theloaidanhgia);
        tv_ngaydang=view.findViewById(R.id.tv_ngaydang);
        tv_luotxem=view.findViewById(R.id.tv_luotxem);
        tv_danhgia=view.findViewById(R.id.tv_danhgia);
        img_truyenmoi=view.findViewById(R.id.img_truyenmoi);
        img_truyenluotxem=view.findViewById(R.id.img_truyenluotxem);
        img_truyendanhgia=view.findViewById(R.id.img_truyendanhgia);
        cv_truyenmoi=view.findViewById(R.id.cv_truyenmoi);
        cv_truyenluotxem=view.findViewById(R.id.cv_truyenluotxem);
        cv_truyendanhgia=view.findViewById(R.id.cv_truyendanhgia);
    }

    public void setData(){
        ArrayList<LichSuDocTruyen> listtruyendadoc=db.getListTruyenDaDoc(taiKhoan.getId());
        ArrayList<Integer> listidtruyen=new ArrayList<>();
        if(listtruyendadoc.size()==0){
            for(int i=0;i<listtruyendadoc.size();i++){
                listidtruyen.add(db.getIdTruyenByIdChapter(listidtruyen.get(i)));
            }
        }


        ArrayList<String> listtheloai=db.getTheLoaiYeuThich(taiKhoan.getId());
        theloai=listtheloai.get(0);

        String lenhSQLite1="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' and truyen.theloai='"+theloai+"' order by chapter.ngaydang desc limit 1";

        String lenhSQLite2="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' and truyen.theloai='"+theloai+"' order by thongke.tongluotxem desc, chapter.ngaydang desc limit 1";

        String lenhSQLite3="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' and truyen.theloai='"+theloai+"' order by thongke.sosaotb desc, chapter.ngaydang desc limit 1";

        String lenhSQLite4="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' order by chapter.ngaydang desc limit 1";

        String lenhSQLite5="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' order by thongke.tongluotxem desc, chapter.ngaydang desc limit 1";

        String lenhSQLite6="select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' order by thongke.sosaotb desc, chapter.ngaydang desc limit 1";

        if(theloai!="theloai"){
            ArrayList<PLTruyen> truyen1=db.getListPLTruyen(lenhSQLite1);
            tv_tentruyenmoi.setText(truyen1.get(0).getTentruyen());
            tv_theloaimoi.setText(truyen1.get(0).getTheloai());
            tv_ngaydang.setText("Ngày đăng: "+truyen1.get(0).getNgaydang());
            Glide.with(this).load(truyen1.get(0).getLinkanh()).into(img_truyenmoi);
            idmoi=truyen1.get(0).getId();

            ArrayList<PLTruyen> truyen2=db.getListPLTruyen(lenhSQLite2);
            tv_tentruyenluotxem.setText(truyen2.get(0).getTentruyen());
            tv_theloailuotxem.setText(truyen2.get(0).getTheloai());
            tv_luotxem.setText("Lượt xem: "+truyen2.get(0).getLuotxem());
            Glide.with(this).load(truyen2.get(0).getLinkanh()).into(img_truyenluotxem);
            idluotxem=truyen2.get(0).getId();

            ArrayList<PLTruyen> truyen3=db.getListPLTruyen(lenhSQLite3);
            tv_tentruyendanhgia.setText(truyen3.get(0).getTentruyen());
            tv_theloaidanhgia.setText(truyen3.get(0).getTheloai());
            tv_danhgia.setText("Đánh giá: "+truyen3.get(0).getDanhgia());
            Glide.with(this).load(truyen3.get(0).getLinkanh()).into(img_truyendanhgia);
            iddanhgia=truyen3.get(0).getId();

        }else{
            ArrayList<PLTruyen> truyen4=db.getListPLTruyen(lenhSQLite4);
            tv_tentruyenmoi.setText(truyen4.get(0).getTentruyen());
            tv_theloaimoi.setText(truyen4.get(0).getTheloai());
            tv_ngaydang.setText("Ngày đăng: "+truyen4.get(0).getNgaydang());
            Glide.with(this).load(truyen4.get(0).getLinkanh()).into(img_truyenmoi);
            idmoi=truyen4.get(0).getId();

            ArrayList<PLTruyen> truyen5=db.getListPLTruyen(lenhSQLite5);
            tv_tentruyenluotxem.setText(truyen5.get(0).getTentruyen());
            tv_theloailuotxem.setText(truyen5.get(0).getTheloai());
            tv_luotxem.setText("Lượt xem: "+truyen5.get(0).getLuotxem());
            Glide.with(this).load(truyen5.get(0).getLinkanh()).into(img_truyenluotxem);
            idluotxem=truyen5.get(0).getId();

            ArrayList<PLTruyen> truyen6=db.getListPLTruyen(lenhSQLite6);
            tv_tentruyendanhgia.setText(truyen6.get(0).getTentruyen());
            tv_theloaidanhgia.setText(truyen6.get(0).getTheloai());
            tv_danhgia.setText("Đánh giá: "+truyen6.get(0).getDanhgia());
            Glide.with(this).load(truyen6.get(0).getLinkanh()).into(img_truyendanhgia);
            iddanhgia=truyen6.get(0).getId();
        }
    }

    private void setOnClickListener(){
        cv_truyendanhgia.setOnClickListener(this);
        cv_truyenluotxem.setOnClickListener(this);
        cv_truyenmoi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_truyenmoi:
                Intent dialog_box = new Intent(getActivity(), CTTruyen.class);
                dialog_box.putExtra("email",email);
                dialog_box.putExtra("id_truyen",idmoi);
                startActivity(dialog_box);
                break;
            case R.id.cv_truyenluotxem:
                Intent dialog_box1 = new Intent(getActivity(), CTTruyen.class);
                dialog_box1.putExtra("email",email);
                dialog_box1.putExtra("id_truyen",idluotxem);
                startActivity(dialog_box1);
                break;
            case R.id.cv_truyendanhgia:
                Intent dialog_box2 = new Intent(getActivity(), CTTruyen.class);
                dialog_box2.putExtra("email",email);
                dialog_box2.putExtra("id_truyen",iddanhgia);
                startActivity(dialog_box2);
                break;
        }
    }
}