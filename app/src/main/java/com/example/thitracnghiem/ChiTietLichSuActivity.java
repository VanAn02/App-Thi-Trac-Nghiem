package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.thitracnghiem.Adapters.ChiTietLichSuAdapter;
import com.example.thitracnghiem.Models.LichSuLamBai;
import com.example.thitracnghiem.SQLHelper.DatabaseHelper;
import com.example.thitracnghiem.databinding.ActivityChiTietLichSuBinding;

import java.util.ArrayList;
import java.util.List;

public class ChiTietLichSuActivity extends AppCompatActivity {
    ActivityChiTietLichSuBinding binding;
    private DatabaseHelper databaseHelper;
    List<LichSuLamBai> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChiTietLichSuBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_chi_tiet_lich_su);
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        int idDeThi = intent.getIntExtra("iddethi", 0);
        list = (ArrayList<LichSuLamBai>) databaseHelper.getListByIdDeThi(idDeThi);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        binding.LichSuCauHoi.setLayoutManager(manager);
        ChiTietLichSuAdapter adapter = new ChiTietLichSuAdapter(this, (ArrayList<LichSuLamBai>) list);
        binding.LichSuCauHoi.setAdapter(adapter);
        binding.imageViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(ChiTietLichSuActivity.this, DanhSachDeThiDaLamActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}