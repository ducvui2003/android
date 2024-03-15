package com.example.truyenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Test extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView=findViewById(R.id.tv_test);

        String a="ĐỆ NHẤT DANH SÁCH\n" +
                "VÕ LUYỆN ĐỈNH PHONG\n" +
                "THÔNG U ĐẠI THÁNH\n" +
                "TA LÀ CHÍ TÔN\n" +
                "THƯƠNG NGUYÊN ĐỒ\n" +
                "THẦN HỒN VÕ ĐẾ\n" +
                "LĂNG THIÊN THẦN ĐẾ\n" +
                "TU LA KIẾM TÔN\n" +
                "KHOA KỸ ĐẠI TIÊN TÔNG\n" +
                "VÕ THẦN CHÚA TỂ\n" +
                "TA LÀ ĐẠI THẦN TIÊN\n" +
                "ĐƯỜNG DẦN TẠI DỊ GIỚI\n" +
                "THANH NINH CHI HẠ \n" +
                "BẠN HỌC CỦA TÔI LÀ LÍNH ĐÁNH THUÊ\n" +
                "TÌNH YÊU TUỔI TRẺ\n" +
                "HỌC VIỆN CAO THỦ\n" +
                "SỰ TRẢ THÙ CỦA NỮ VƯƠNG\n" +
                "DANH MÔN CHÍ ÁI\n" +
                "THUẦN TÌNH NHA ĐẦU HOẢ LẠT LẠT\n" +
                "THÔNG LINH PHI";
        textView.setText(ChuyenDoiChu(a));
        String f=ChuyenDoiChu((a));
        System.out.println(f);
    }

    public static String ChuyenDoiChu(String s) {

        s=s.toLowerCase();
        s=s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

}