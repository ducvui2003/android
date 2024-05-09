package com.example.truyenapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.Category;
import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.AttendanceResponse;
import com.example.truyenapp.response.UserResponse;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.activity.RankActivity;
import com.example.truyenapp.view.activity.SearchActivity;
import com.example.truyenapp.admin.CommentManagerActivity;
import com.example.truyenapp.admin.QuanLyTaiKhoan;
import com.example.truyenapp.admin.QuanLyThongKe;
import com.example.truyenapp.admin.QuanLyTruyen;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.model.Story;
import com.example.truyenapp.view.activity.DiemThuong;
import com.example.truyenapp.view.activity.Signin;
import com.example.truyenapp.view.adapter.TruyenAdapter;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    View headerLayout, view;
    Button loginBtn;
    //    Button logoutBtn;
    Menu menu;
    MenuItem mn_it_chucnangquantri;
    TextView tv_TimKemHome, tv_xephang, tv_theloai, tv_emailhome, tv_diemthuong, tv_diemdanh;
    String email;
    private UserResponse userResponse;
    private RecyclerView rv, rv2, rv3;
    private TruyenAdapter _rv, rv_2, rv_3;
    private UserAPI userAPI;
    Database db;
    Account account;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAPI = RetrofitClient.getInstance(getContext()).create(UserAPI.class);

    }

    /**
     * This method is used to fetch user information
     * author: Hoang
     */

    private void getUserInfo() {
        // Call the getUserInfo method from the UserAPI interface
        JWTToken jwtToken = SharedPreferencesHelper.getObject(getContext(), SystemConstant.JWT_TOKEN, JWTToken.class);
        if (jwtToken == null) {
            return;
        }
        userAPI.getUserInfo(jwtToken.getToken()).enqueue(new Callback<UserResponse>() {
            // This method is called when the server response is received
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Get the UserResponse object from the response
                UserResponse user = response.body();
                // Check if the user object is not null
                if (user != null) {
                    // Assign the user object to the userResponse variable
                    userResponse = user;
                    // Set the email of the user in the TextView tv_emailhome
                    tv_emailhome.setText(user.getEmail());
                    email = user.getEmail();
                    activeAdmin();
                }
            }

            // This method is called when the request could not be executed due to cancellation, a connectivity problem or a timeout
            @Override
            public void onFailure(Call<UserResponse> call, Throwable throwable) {
                // Log the error message
                Log.e("TAG", "Login failed: " + throwable.getMessage());
                // Show a toast message indicating that an error occurred
                Toast.makeText(getActivity(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        db = new Database(getActivity());
        getUserInfo();
        init();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

        rv.setLayoutManager(linearLayoutManager);
        rv2.setLayoutManager(linearLayoutManager2);
        rv3.setLayoutManager(linearLayoutManager3);

        String lenhSqlite_truyenmoi = "SELECT *" +
                "  FROM truyen \n" +
                "  where id in (select truyen.id from truyen inner join chapter on truyen.id=chapter.idtruyen where chapter.tenchapter='Chapter 1' order by chapter.ngaydang desc limit 5)";
        ArrayList<Story> truyenmoi = db.getTruyen(lenhSqlite_truyenmoi);
        _rv = new TruyenAdapter(truyenmoi, getActivity(), email);

        String lenhSqlite_toptruyen = "SELECT *\n" +
                "  FROM truyen \n" +
                "  where id in (select truyen.id from truyen inner join thongke on truyen.id=thongke.idtruyen order by thongke.tongluotxem desc limit 5)";
        ArrayList<Story> toptruyen = db.getTruyen(lenhSqlite_toptruyen);
        rv_2 = new TruyenAdapter(toptruyen, getActivity(), email);

        String lenhSqlite_truyenfull = "SELECT *\n" +
                "  FROM truyen \n" +
                "  where trangthai=1 limit 5";
        ArrayList<Story> truyenfull = db.getTruyen(lenhSqlite_truyenfull);
        rv_3 = new TruyenAdapter(truyenfull, getActivity(), email);

        rv.setAdapter(_rv);
        rv2.setAdapter(rv_2);
        rv3.setAdapter(rv_3);

        ActionBar();
        ActionViewFlipper();
        setOnClickListener();

        return view;
    }

    private void init() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.vf);
        navigationView = (NavigationView) view.findViewById(R.id.nvv);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drlo);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drlo);
        headerLayout = navigationView.inflateHeaderView(R.layout.header);
        loginBtn = (Button) headerLayout.findViewById(R.id.home_login_btn);


        tv_TimKemHome = (TextView) view.findViewById(R.id.tv_TimKiemHome);
        tv_xephang = (TextView) view.findViewById(R.id.tv_xephang);
        tv_theloai = (TextView) view.findViewById(R.id.tv_theloai);
        tv_diemthuong = view.findViewById(R.id.tv_diemthuong);
        tv_diemdanh = view.findViewById(R.id.tv_diemdanh);
        rv = view.findViewById(R.id.rv);
        rv2 = view.findViewById(R.id.rv2);
        rv3 = view.findViewById(R.id.rv3);
        menu = navigationView.getMenu();
        mn_it_chucnangquantri = menu.findItem(R.id.it_chucnangquantri);
        tv_emailhome = headerLayout.findViewById(R.id.tv_emailhome);

    }

    public void activeAdmin() {
        if (tv_emailhome.getText().length() != 0) {
            if (userResponse.getRole().equals(SystemConstant.ROLE_ADMIN)) {
                mn_it_chucnangquantri.setVisible(true);
            } else {
                mn_it_chucnangquantri.setVisible(false);
            }
            tv_emailhome.setVisibility(view.VISIBLE);
            loginBtn.setVisibility(view.GONE);
        } else {
            mn_it_chucnangquantri.setVisible(false);
            tv_emailhome.setVisibility(view.GONE);
            loginBtn.setVisibility(view.VISIBLE);
        }
    }

    private void setOnClickListener() {
        loginBtn.setOnClickListener(this);
        tv_TimKemHome.setOnClickListener(this);
        tv_xephang.setOnClickListener(this);
        tv_theloai.setOnClickListener(this);
        tv_diemthuong.setOnClickListener(this);
        tv_diemdanh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_login_btn:
                Intent dialog_box = new Intent(getActivity(), Signin.class);
                startActivity(dialog_box);
                getActivity().finish();
                break;
            case R.id.tv_TimKiemHome:
                Intent dialog_box1 = new Intent(getActivity(), SearchActivity.class);
                dialog_box1.putExtra("email", email);
                startActivity(dialog_box1);
                break;
            case R.id.tv_xephang:
                Intent dialog_box2 = new Intent(getActivity(), RankActivity.class);//
                dialog_box2.putExtra("email", email);
                startActivity(dialog_box2);
                break;
            case R.id.tv_theloai:
                Intent dialog_box3 = new Intent(getActivity(), Category.class);
                dialog_box3.putExtra("email", email);
                startActivity(dialog_box3);
                break;
            case R.id.tv_diemthuong:
                if (email != null) {
                    Intent dialog_box4 = new Intent(getActivity(), DiemThuong.class);
                    dialog_box4.putExtra("email", email);
                    startActivity(dialog_box4);
                } else {
                    Toast.makeText(getActivity(), "Vui lòng đăng nhập để sử dụng chức năng này!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_diemdanh: {
//                Check đăng nhập trước khi điểm danh
                boolean isLoggedIn = AuthenticationManager.isLoggedIn(SharedPreferencesHelper.getObject(getActivity().getApplicationContext(), SystemConstant.JWT_TOKEN, JWTToken.class));
                if (isLoggedIn) {
                    Toast.makeText(getActivity(), "Vui lòng đăng nhập để sử dụng chức năng này!", Toast.LENGTH_SHORT).show();
                    return;
                }
                attendanceAPI();
                break;
            }
//            case R.id.bt_dxhome: {
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                Toast.makeText(getActivity().getApplicationContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//                getActivity().finish();
//            }
        }
    }

    private void ActionViewFlipper() {
        ArrayList<String> arrGTSP = new ArrayList<>();
        arrGTSP.add("https://m.media-amazon.com/images/M/MV5BMjYxZjFkNTEtZjYzNC00MTdhLThiM2ItYmRiOWJiYTJkYzMxXkEyXkFqcGdeQXVyNDQxNjcxNQ@@._V1_FMjpg_UX1000_.jpg");
        arrGTSP.add("https://www.mangageko.com/media/manga_covers/36284.jpg");
        arrGTSP.add("https://upload.wikimedia.org/wikipedia/en/5/52/Y%C5%8Dkoso_Jitsuryoku_Shij%C5%8D_Shugi_no_Ky%C5%8Dshitsu_e%2C_Volume_1.jpg");
        arrGTSP.add("https://upload.wikimedia.org/wikipedia/vi/a/a7/That_Time_I_Got_Reincarnated_as_a_Slime_anime_official_poster.jpg");
        arrGTSP.add("https://i7.xem-truyen.com/manga/14/14743/uhazq28.thumb_500x.jpg");

        for (int i = 0; i < arrGTSP.size(); i++) {
            ImageView imageView = new ImageView(((AppCompatActivity) getActivity()).getApplicationContext());
            Picasso.with(((AppCompatActivity) getActivity()).getApplicationContext()).load(arrGTSP.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation anim_slide_in = AnimationUtils.loadAnimation(((AppCompatActivity) getActivity()).getApplicationContext(), R.anim.slide_in_right);
        Animation anim_slide_out = AnimationUtils.loadAnimation(((AppCompatActivity) getActivity()).getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(anim_slide_in);
        viewFlipper.setOutAnimation(anim_slide_out);
    }

    private void ActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.nav_d_op, R.string.nav_d_cl);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.isDrawerOpen(GravityCompat.START);
        } else {
            ((AppCompatActivity) getActivity()).onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.it_quanlytaikhoan:
                Intent dialog_box = new Intent(getActivity(), QuanLyTaiKhoan.class);
                dialog_box.putExtra("email", account.getEmail());
                startActivity(dialog_box);
                break;
            case R.id.it_quanlytruyen:
                Intent dialog_box1 = new Intent(getActivity(), QuanLyTruyen.class);
                dialog_box1.putExtra("email", account.getEmail());
                startActivity(dialog_box1);
                break;
            case R.id.it_quanlybinhluan:
                Intent dialog_box2 = new Intent(getActivity(), CommentManagerActivity.class);
                dialog_box2.putExtra("email", account.getEmail());
                startActivity(dialog_box2);
                break;
            case R.id.it_quanlythongke:
                Intent dialog_box3 = new Intent(getActivity(), QuanLyThongKe.class);
                dialog_box3.putExtra("email", account.getEmail());
                startActivity(dialog_box3);
                break;
            case R.id.it_xephang:
                Intent dialog_box4 = new Intent(getActivity(), RankActivity.class);
                startActivity(dialog_box4);
                break;
            case R.id.it_theloai:
                Intent dialog_box5 = new Intent(getActivity(), Category.class);
                startActivity(dialog_box5);
                break;
        }
        return true;
    }

    public void attendanceAPI() {
        userAPI.attendance().enqueue(new Callback<APIResponse<AttendanceResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<AttendanceResponse>> call, Response<APIResponse<AttendanceResponse>> response) {
                APIResponse<AttendanceResponse> apiResponse = response.body();
                if (apiResponse.getCode() == 200) {
                    AttendanceResponse attendanceResponse = apiResponse.getResult();
                    Toast.makeText(getActivity(), "Điểm danh thành công! +" + attendanceResponse.getScore() + " điểm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Hôm nay bạn đã điểm danh, chờ đến ngày mai nhé!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<AttendanceResponse>> call, Throwable throwable) {
                Log.e("TAG", "Attendance failed: " + throwable.getMessage());
                Toast.makeText(getActivity(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}