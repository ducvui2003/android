package com.example.truyenapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.adapter.TimKiemAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Model_TimKiem;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class TimKiem extends AppCompatActivity implements View.OnClickListener {

    EditText edt_search;
    AutoCompleteTextView autoCompleteTextView;
    Database db;
    ArrayAdapter<String> adapterItems;
    ArrayList<String> listtheloai;
    TextView tv_trong;
    ImageView img_giongnoi;
    ScrollView srv_danhsach;
    String email;

    public String textTheLoai;
    public String textSearch="";
    private RecyclerView rcv_timkiem;
    private TimKiemAdapter rcv_adapter;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timkiem);

        db=new Database(this);
        Anhxa();

        Intent intent=getIntent();
        email=intent.getStringExtra("email");

        listtheloai=db.getTheLoai();
        listtheloai.add(0,"Tất cả");

        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,listtheloai);
        autoCompleteTextView.setText(listtheloai.get(0));
        autoCompleteTextView.setAdapter(adapterItems);
        textTheLoai=listtheloai.get(0);

        setOnClickListener();
        Search();
    }

    public void editTextSearch(String textSearch){
        if(textSearch.equals("")){
            Toast.makeText(this, "Không có sản phẩm nào", Toast.LENGTH_SHORT).show();
            tv_trong.setVisibility(View.VISIBLE);
            srv_danhsach.setVisibility(View.GONE);
        }else{
            tv_trong.setVisibility(View.GONE);
            srv_danhsach.setVisibility(View.VISIBLE);
            String txt = removeAccent(textSearch);
            recyclerViewTruyen(txt);
        }

    }

    private void recyclerViewTruyen(String textSearch) {
        if(textTheLoai=="Tất cả"){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rcv_timkiem.setLayoutManager(linearLayoutManager);

            String lenhSQLite="SELECT truyen.id,thongke.tongluotxem,count(chapter.id),thongke.sosaotb,truyen.tentruyen,truyen.theloai,truyen.linkanh\n" +
                    "FROM truyen inner join thongke on truyen.id=thongke.idtruyen \n" +
                    "inner join chapter on truyen.id=chapter.idtruyen\n" +
                    "where key_search like '%"+textSearch.trim()+"%' COLLATE NOCASE\n" +
                    "group by truyen.id\n" +
                    "order by thongke.tongluotxem desc";
            ArrayList<Model_TimKiem> listtimkiem = db.getListTimKiem(lenhSQLite);
            if(listtimkiem.size() == 0){
                Toast.makeText(this, "Không có truyện cần tìm!!!", Toast.LENGTH_SHORT).show();
                tv_trong.setVisibility(View.VISIBLE);
                srv_danhsach.setVisibility(View.GONE);
            }else {
                rcv_adapter = new TimKiemAdapter(this,listtimkiem,email);
                rcv_timkiem.setAdapter(rcv_adapter);
            }
        }
        else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rcv_timkiem.setLayoutManager(linearLayoutManager);

            String lenhSQLite="SELECT truyen.id,thongke.tongluotxem,count(chapter.id),thongke.sosaotb,truyen.tentruyen,truyen.theloai,truyen.linkanh\n" +
                    "FROM truyen inner join thongke on truyen.id=thongke.idtruyen \n" +
                    "inner join chapter on truyen.id=chapter.idtruyen\n" +
                    "where key_search like '%"+textSearch.trim()+"%' COLLATE NOCASE and truyen.theloai='"+textTheLoai.trim()+"'\n" +
                    "group by truyen.id\n" +
                    "order by thongke.tongluotxem desc";
            ArrayList<Model_TimKiem> listtimkiem = db.getListTimKiem(lenhSQLite);
            if(listtimkiem.size() == 0){
                Toast.makeText(this, "Không có truyện cần tìm!!!", Toast.LENGTH_SHORT).show();
                tv_trong.setVisibility(View.VISIBLE);
                srv_danhsach.setVisibility(View.GONE);
            }else {
                rcv_adapter = new TimKiemAdapter(this,listtimkiem,email);
                rcv_timkiem.setAdapter(rcv_adapter);
            }
        }

    }

    public static String removeAccent(String s){
        s=s.toLowerCase();
        s=s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public void Search(){
        edt_search.setOnClickListener(v -> {
            textSearch = edt_search.getText().toString();
            editTextSearch(textSearch);
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                textTheLoai=item;
                editTextSearch(textSearch);
                if(item==""){
                    Toast.makeText(getApplicationContext(),"Thể loại: Tất cả",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Thể loại: "+item,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Anhxa() {
        edt_search=findViewById(R.id.edt_search);
        autoCompleteTextView=findViewById(R.id.auto_complete_txt);
        tv_trong=findViewById(R.id.tv_trong);
        rcv_timkiem=findViewById(R.id.rcv_timkiem);
        img_giongnoi=findViewById(R.id.img_giongnoi);
        srv_danhsach=findViewById(R.id.srv_danhsach);

    }

    private void setOnClickListener(){
        img_giongnoi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_giongnoi:
            {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast.makeText(this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                edt_search.setText(Objects.requireNonNull(result).get(0));
            }
        }
    }
}
