package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.thitracnghiem.Adapters.LichSuDeAdapter;
import com.example.thitracnghiem.Models.DeThi;
import com.example.thitracnghiem.SQLHelper.DatabaseHelper;
import com.example.thitracnghiem.databinding.ActivityDanhSachDeThiDaLamBinding;

import java.util.ArrayList;

public class DanhSachDeThiDaLamActivity extends AppCompatActivity {
    ActivityDanhSachDeThiDaLamBinding binding;
    private DatabaseHelper databaseHelper;
    ArrayList<DeThi> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDanhSachDeThiDaLamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_danh_sach_de_thi_da_lam);
        Intent intent = getIntent();
        String tenmonhoc=intent.getStringExtra("tenmon");
        int idmonhoc = intent.getIntExtra("idmonhoc", 0);
        Log.e("DeThiAcity",tenmonhoc);
        Toast.makeText(DanhSachDeThiDaLamActivity.this, "ID Môn học: " + idmonhoc, Toast.LENGTH_LONG).show();
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getAllLichSuLamBai();
        list =(ArrayList<DeThi>) databaseHelper.getDeThiList(idmonhoc);

        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        binding.DeThi.setLayoutManager(manager);
        LichSuDeAdapter adapter = new LichSuDeAdapter(this, list);
        binding.DeThi.setAdapter(adapter);
        binding.imageViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DanhSachDeThiDaLamActivity.this, LichSuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}