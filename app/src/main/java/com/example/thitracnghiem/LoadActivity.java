package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadActivity extends AppCompatActivity {
    private ProgressBar progressBar_loading;
    private TextView tv_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Handler handler = new Handler();
        progressBar_loading = findViewById(R.id.progressBar_loading);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);
        progressBar_loading.setProgressTintList(colorStateList);
        progressBar_loading.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadActivity.this, TrangChuActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
        tv_loading=findViewById(R.id.tv_loading);
        Typeface typeface = getResources().getFont(R.font.medium);
        tv_loading.setTypeface(typeface);
    }
}