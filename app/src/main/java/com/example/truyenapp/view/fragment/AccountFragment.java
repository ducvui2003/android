package com.example.truyenapp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.AuthAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.request.LogoutRequest;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.activity.DoiMatKhau;
import com.example.truyenapp.view.activity.HomeActivity;
import com.example.truyenapp.view.activity.ShowBinhLuan;
import com.example.truyenapp.view.activity.ShowDanhGia;
import com.example.truyenapp.view.activity.Signin;
import com.example.truyenapp.view.activity.ThongTinTaiKhoan;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment implements View.OnClickListener {

    TextView tv_tk_email, tv_tk_lv, tv_tongngaydiemdanh, tv_tk_diem, tv_tk_sotruyen, tv_tk_sobinhluan,
            tv_tk_sodanhgia, tv_binhluancuatoi, tv_danhgiacuatoi;
    TextView tv_doimatkhau, logoutBtn, tv_tttk;
    ImageView img_tk_avatar;
    Database db;
    String email;
    Account account;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AuthAPI authAPI;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);

        // create API connection
        authAPI = RetrofitClient.getInstance(getContext()).create(AuthAPI.class);

        Anhxa();
        db = new Database(getActivity());
        Intent intent = getActivity().getIntent();
        email = intent.getStringExtra("email");
        account = db.getTaiKhoan(email);

        setData();
        setOnClickListener();

        return view;
    }

    private void Anhxa() {
        tv_tk_email = view.findViewById(R.id.tv_tk_email);
        tv_tk_lv = view.findViewById(R.id.tv_tk_lv);
        tv_tongngaydiemdanh = view.findViewById(R.id.tv_tongngaydiemdanh);
        tv_tk_diem = view.findViewById(R.id.tv_tk_diem);
        tv_tk_sotruyen = view.findViewById(R.id.tv_tk_sotruyen);
        tv_tk_sobinhluan = view.findViewById(R.id.tv_tk_sobinhluan);
        tv_tk_sodanhgia = view.findViewById(R.id.tv_tk_sodanhgia);
        img_tk_avatar = view.findViewById(R.id.img_tk_avatar);
        tv_binhluancuatoi = view.findViewById(R.id.tv_binhluancuatoi);
        tv_danhgiacuatoi = view.findViewById(R.id.tv_danhgiacuatoi);
        tv_doimatkhau = view.findViewById(R.id.tv_doimatkhau);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        tv_tttk = view.findViewById(R.id.tv_tttk);
    }

    private void setData() {
        tv_tk_email.setText(account.getEmail());
        tv_tk_diem.setText("" + account.getRewardPoint());
        if (account.getLinkImage() == null) {
            img_tk_avatar.setImageResource(R.drawable.logo);
        } else {
            Glide.with(getActivity()).load(account.getLinkImage()).into(img_tk_avatar);
        }
        int tongngaydiemdanh = db.getTongDiemDanh(account.getId());
        tv_tongngaydiemdanh.setText("" + tongngaydiemdanh + " ngày");
        tv_tk_lv.setText("Lv." + (tongngaydiemdanh / 20));
        tv_tk_sobinhluan.setText("" + db.getTongBinhLuan(account.getId()));
        tv_tk_sotruyen.setText("" + db.getTongTruyenDaDoc(account.getId()));
        tv_tk_sodanhgia.setText("" + db.getTongDanhGia(account.getId()));

    }

    private void setOnClickListener() {
        tv_danhgiacuatoi.setOnClickListener(this);
        tv_binhluancuatoi.setOnClickListener(this);
        tv_doimatkhau.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        tv_tttk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_binhluancuatoi:
                Intent dialog_box = new Intent(getActivity(), ShowBinhLuan.class);
                dialog_box.putExtra("email", email);
                startActivity(dialog_box);
                break;
            case R.id.tv_danhgiacuatoi:
                Intent dialog_box1 = new Intent(getActivity(), ShowDanhGia.class);
                dialog_box1.putExtra("email", email);
                startActivity(dialog_box1);
                break;
            case R.id.tv_doimatkhau:
                Intent dialog_box2 = new Intent(getActivity(), DoiMatKhau.class);
                dialog_box2.putExtra("email", email);
                startActivity(dialog_box2);
                break;
            case R.id.logoutBtn:
                logoutHandle();
                break;
            case R.id.tv_tttk:
                Intent dialog_box3 = new Intent(getActivity(), ThongTinTaiKhoan.class);
                dialog_box3.putExtra("email", email);
                startActivity(dialog_box3);
                break;
        }
    }

    private void logoutHandle() {
        final Context context = getContext();
        String token = SharedPreferencesHelper.getObject(context, SystemConstant.JWT_TOKEN, String.class);
        authAPI.logout(new LogoutRequest(token)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                SharedPreferencesHelper.deletePreference(context, SystemConstant.JWT_TOKEN);
                Toast.makeText(context, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.e("TAG", "Logout failed: " + throwable.getMessage());
                Toast.makeText(context, "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}