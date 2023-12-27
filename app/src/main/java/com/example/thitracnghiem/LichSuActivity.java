package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thitracnghiem.Adapters.LichSuMonAdapter;
import com.example.thitracnghiem.Models.LichSu;
import com.example.thitracnghiem.Models.LichSuLamBai;
import com.example.thitracnghiem.Models.MonHoc;
import com.example.thitracnghiem.SQLHelper.DatabaseHelper;
import com.example.thitracnghiem.databinding.ActivityLichSuBinding;
import com.example.thitracnghiem.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class LichSuActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    ArrayList<MonHoc> list;

    ActivityLichSuBinding binding;
    private LichSuLamBai lichSuLamBai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLichSuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lichSuLamBai=new LichSuLamBai();
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getAllLichSuLamBai();
        list= (ArrayList<MonHoc>) databaseHelper.getListMonHoc();
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        binding.LichSuMon.setLayoutManager(manager);
        LichSuMonAdapter adapter=new LichSuMonAdapter(list,this);
        binding.LichSuMon.setAdapter(adapter);
        binding.imageViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LichSuActivity.this, TrangChuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}