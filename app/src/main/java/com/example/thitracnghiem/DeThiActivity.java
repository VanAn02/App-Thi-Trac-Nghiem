//package com.example.thitracnghiem;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.example.thitracnghiem.Adapters.DeThiAdapter;
//import com.example.thitracnghiem.Models.DeThi;
//import com.example.thitracnghiem.SQLHelper.DatabaseHelper;
//import com.example.thitracnghiem.databinding.ActivityDeThiBinding;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DeThiActivity extends AppCompatActivity {
//    private DatabaseHelper databaseHelper;
//    private SQLiteDatabase database;
//    ActivityDeThiBinding binding;
//    ArrayList<DeThi> list;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityDeThiBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        Intent intent = getIntent();
//        int idmonhoc = intent.getIntExtra("idmonhoc", 0);
//        Log.e("idmonhoc", String.valueOf(idmonhoc));
//        Toast.makeText(DeThiActivity.this, "idmonhoc: " + idmonhoc, Toast.LENGTH_LONG).show();
//
//        binding.imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DeThiActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        databaseHelper = new DatabaseHelper(this);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        binding.dethiRecy.setLayoutManager(manager);
//        //list = databaseHelper.getDeThiList(idmonhoc);
//        Log.e("Iddddd",String.valueOf(list.size()));
//        DeThiAdapter adapter = new DeThiAdapter(this, list);
//        binding.dethiRecy.setAdapter(adapter);
//        binding.SoDe.setText(String.valueOf(list.size()));
//    }
//}
package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.thitracnghiem.Adapters.DeThiAdapter;
import com.example.thitracnghiem.Models.DeThi;
import com.example.thitracnghiem.SQLHelper.DatabaseHelper;
import com.example.thitracnghiem.databinding.ActivityDeThiBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeThiActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    ActivityDeThiBinding binding;
    ArrayList<DeThi> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeThiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String tenmonhoc=intent.getStringExtra("tenmon");
        int idmonhoc = intent.getIntExtra("idmonhoc", 0);
        Log.e("DeThiAcity",tenmonhoc);
        Toast.makeText(DeThiActivity.this, "ID Môn học: " + idmonhoc, Toast.LENGTH_LONG).show();

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeThiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        databaseHelper = new DatabaseHelper(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.dethiRecy.setLayoutManager(manager);

        //list =(List<DeThi>) databaseHelper.getDeThiListByIds(Collections.singletonList(idmonhoc));
        list =(ArrayList<DeThi>) databaseHelper.getDeThiList(idmonhoc);
        DeThiAdapter adapter = new DeThiAdapter(this, list);
        binding.dethiRecy.setAdapter(adapter);
        binding.SoDe.setText(String.valueOf(list.size()));
    }
}
