package com.example.truyenapp.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.SearchAPI;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.RankViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankViewFragment extends Fragment {

    Database db;
    private RecyclerView rcv;
    private RankViewAdapter adapter;
    List<ClassifyStory> truyens = new ArrayList<>();

    public static RankViewFragment newInstance(String param1, String param2) {
        RankViewFragment fragment = new RankViewFragment();
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
        return inflater.inflate(R.layout.fragment_rank_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new Database(getActivity());
        rcv = view.findViewById(R.id.rcv_rank_vote);
        adapter = new RankViewAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        String lenhSqlite_theloai = "select truyen.id, thongke.tongluotxem, thongke.sosaotb, truyen.tentruyen, chapter.ngaydang, truyen.theloai theloai, truyen.linkanh from truyen inner join chapter on truyen.id=chapter.idtruyen inner join thongke on truyen.id=thongke.idtruyen where chapter.tenchapter='Chapter 1' order by thongke.tongluotxem desc, chapter.ngaydang desc";
        db = new Database(getActivity());
        getRankViewList();
        adapter.setData(truyens);
        rcv.setAdapter(adapter);
    }

    //    call api lấy dữ liệu danh sách truyện theo lượt xem
    public List<ClassifyStory> getRankViewList() {
        SearchAPI response = RetrofitClient.getInstance().create(SearchAPI.class);
        response.rank("view").enqueue(new Callback<APIResponse<DataListResponse<BookResponse>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<BookResponse>>> call, Response<APIResponse<DataListResponse<BookResponse>>> response) {
                APIResponse<DataListResponse<BookResponse>> data = response.body();
                for (BookResponse bookResponse : data.getResult().getData()
                ) {
                    ClassifyStory classifyStory = new ClassifyStory(bookResponse.getId(), bookResponse.getName(), bookResponse.getCategory(), bookResponse.getThumbnail());
                    truyens.add(new ClassifyStory(bookResponse.getId(), bookResponse.getTentruyen(), bookResponse.getTheloai(), bookResponse.getLinkanh(), bookResponse.getTongluotxem(), bookResponse.getSosaotb()));
                }
                Log.d("TAG", "onResponse: " + data);
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<BookResponse>>> call, Throwable throwable) {

            }
        });
        return new ArrayList<>();
    }
}