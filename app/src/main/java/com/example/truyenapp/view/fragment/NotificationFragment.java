package com.example.truyenapp.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.view.adapter.NotificationAdapter;
import com.example.truyenapp.model.Notification;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    View view;

    public RecyclerView rcv;

    public NotificationAdapter rcv_adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Notification> notifications;

    public NotificationFragment() {
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
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        view = inflater.inflate(R.layout.thongbao, container, false);
        Anhxa();
        recyclerViewThongBao();
        return view;
    }

    public void recyclerViewThongBao() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        rcv_adapter = new NotificationAdapter(getActivity(), notifications);
        rcv.setAdapter(rcv_adapter);
        getAllNotificationOrderByDate();
    }

    public void getAllNotificationOrderByDate() {
        UserAPI response = RetrofitClient.getInstance(getContext()).create(UserAPI.class);
        response.getNotifications().enqueue(new Callback<APIResponse<ArrayList<Notification>>>() {
            @Override
            public void onResponse(Call<APIResponse<ArrayList<Notification>>> call, Response<APIResponse<ArrayList<Notification>>> response) {
                if (response.isSuccessful()) {
                    APIResponse<ArrayList<Notification>> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getResult() != null) {
                        notifications = apiResponse.getResult();
                    }
                    rcv_adapter.setData(notifications);
                } else {
                    Toast.makeText(getActivity(), "Trống", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<APIResponse<ArrayList<Notification>>> call, Throwable throwable) {
                Log.e("TAG", "Login failed: " + throwable.getMessage());
                // Show a toast message indicating that an error occurred
                Toast.makeText(getActivity(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void Anhxa() {
        rcv = view.findViewById(R.id.rcv_thongbao);
    }
}