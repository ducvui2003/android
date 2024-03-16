package com.example.truyenapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.truyenapp.R;

public class IntroduceActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

//        Delay 2s -> next to homepage
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent =new Intent(IntroduceActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}