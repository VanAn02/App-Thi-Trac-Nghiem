package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {
    CardView youtube,instagram,facebook,telegram;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        youtube.setOnClickListener(v->{
            String url = "https://www.youtube.com/c/GITADasturchilarAkademiyasi";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            finish();
        });
        instagram.setOnClickListener(v->{
            String url = "https://www.instagram.com/gita.uzofficial/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            finish();
        });
        facebook.setOnClickListener(v->{
            String url = "https://www.facebook.com/profile.php?id=100077062961253";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            finish();
        });
        telegram.setOnClickListener(v->{
            String url = "https://t.me/stc_android";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            finish();
        });
        img_back.setOnClickListener(v->{
            Intent intent=new Intent(AboutActivity.this,TrangChuActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void initView(){
        youtube=findViewById(R.id.youtube);
        instagram=findViewById(R.id.instagram);
        facebook=findViewById(R.id.facebook);
        telegram=findViewById(R.id.cardView3);
        img_back=findViewById(R.id.img_back);
    }
}