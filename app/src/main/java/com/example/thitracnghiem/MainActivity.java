package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.thitracnghiem.Adapters.MonHocAdapter;
import com.example.thitracnghiem.Models.MonHoc;
import com.example.thitracnghiem.SQLHelper.DatabaseHelper;
import com.example.thitracnghiem.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private CardView item_category;
    ActivityMainBinding binding;
    ArrayList<MonHoc> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.AddMonHoc();
        databaseHelper.AddDeThi();
        databaseHelper.CauHoi();
        list= (ArrayList<MonHoc>) databaseHelper.getListMonHoc();
        GridLayoutManager manager=new GridLayoutManager(this,2);
        binding.monhoc.setLayoutManager(manager);
        MonHocAdapter adapter=new MonHocAdapter(this,list);
        binding.monhoc.setAdapter(adapter);
        databaseHelper.getAllCauHoiTracNghiemList();
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intback =new Intent(MainActivity.this,TrangChuActivity.class);
                startActivity(intback);
                finish();
            }
        });
    }
    private void Click(){
        item_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DeThiActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}