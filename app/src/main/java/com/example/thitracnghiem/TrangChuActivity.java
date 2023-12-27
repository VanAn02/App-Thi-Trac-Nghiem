package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thitracnghiem.SQLHelper.DatabaseHelper;

public class TrangChuActivity extends AppCompatActivity {
    TextView userNameHome;
    private DatabaseHelper databaseHelper;
    CardView cvStartQuiz, cvRule, cvHistory, cvLogout, cvAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        initView();
        cvHistory.setOnClickListener(v -> {
            startActivity(new Intent(TrangChuActivity.this, LichSuActivity.class));
        });
        cvAbout.setOnClickListener(v -> {
            startActivity(new Intent(TrangChuActivity.this, AboutActivity.class));
        });
        cvStartQuiz.setOnClickListener(view -> {
            startActivity(new Intent(TrangChuActivity.this, MainActivity.class));

        });
        cvLogout.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            View view = LayoutInflater.from(this).inflate(R.layout.activty_exit,findViewById(R.id.container),false);
            dialog.setContentView(view);
            view.findViewById(R.id.button_exit_no).setOnClickListener(view1->{
                dialog.cancel();
                dialog.dismiss();
            });
            view.findViewById(R.id.button_exit_yes).setOnClickListener(view2->{
                finish();
            });
            dialog.show();

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        });
        cvRule.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            View view = LayoutInflater.from(this).inflate(R.layout.activty_restart,findViewById(R.id.container),false);
            dialog.setContentView(view);
            view.findViewById(R.id.button_exit_no).setOnClickListener(view1->{
                dialog.cancel();
                dialog.dismiss();
            });
            view.findViewById(R.id.button_exit_yes).setOnClickListener(view2->{
                Toast.makeText(TrangChuActivity.this,"Lam moi thanh cong",Toast.LENGTH_LONG).show();
                finish();
            });
            dialog.show();

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        });
    }

    public void initView() {
        cvStartQuiz = findViewById(R.id.cvStartQuiz);
        cvRule = findViewById(R.id.cvRule);
        cvAbout = findViewById(R.id.cvEditPassword);
        cvHistory = findViewById(R.id.cvHistory);
        cvLogout = findViewById(R.id.cvLogout);
        userNameHome = (TextView) findViewById(R.id.tvUsernameHome);
    }


}
