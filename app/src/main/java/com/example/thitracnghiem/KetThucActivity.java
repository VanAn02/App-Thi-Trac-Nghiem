package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.thitracnghiem.databinding.ActivityKetThucBinding;
import com.example.thitracnghiem.databinding.ActivityMainBinding;

public class KetThucActivity extends AppCompatActivity {
    ActivityKetThucBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityKetThucBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int TongSoCauHoi = getIntent().getIntExtra("tongsocaudalam", 0);
        int TongSoCauDung = getIntent().getIntExtra("dapandung", 0);
        int Sai = TongSoCauHoi - TongSoCauDung;
        int idDeThi=getIntent().getIntExtra("idDeThi",0);
        Log.e("tongsocaudalam",String.valueOf(TongSoCauDung));
        Log.e("tongsocaudalam",String.valueOf(TongSoCauHoi));
        Log.e("tongsocaudalam",String.valueOf(Sai));

        binding.tongsocau.setText(String.valueOf(TongSoCauHoi));
        binding.tongsocaudung.setText(String.valueOf(TongSoCauDung));
        binding.tongsocausai.setText(String.valueOf(Sai));
        binding.btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(KetThucActivity.this,TrangChuActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(KetThucActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(KetThucActivity.this,ChiTietLichSuActivity.class);
                intent.putExtra("iddethi",idDeThi);
                startActivity(intent);
                finish();
            }
        });
    }
}