package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.example.thitracnghiem.SQLHelper.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
//    private SQLiteDatabase database;
//    private static final String TABLE_MONHOC = "monhoc";
//    private static final String COLUMN_MONHOC_ID = "id";
//    private static final String COLUMN_MONHOC_TEN = "tenMonHoc";
//    private static final String COLUMN_MONHOC_HINHANH = "hinhAnh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        databaseHelper = new DatabaseHelper(this);
        //databaseHelper.AddMonHoc();
        //databaseHelper.getListMonHoc();
//        int idMonHoc = 1;
//        int SoDeThi=3;
//        databaseHelper.AddDeThi(idMonHoc,SoDeThi);
//        databaseHelper.getDeThiList(idMonHoc);
//        Log.e("LISTDETHI","LISTDETHI");
        int idDeThi = 1;
        String noiDungCauHoi = "Đâu là quốc gia có diện tích lớn nhất thế giới?";
        String phuongAnA = "Nga";
        String phuongAnB = "Canada";
        String phuongAnC = "Trung Quốc";
        String phuongAnD = "Mỹ";
        String dapAnDung = "Nga";

        databaseHelper.AddCauHoiTracNghiem(idDeThi, noiDungCauHoi, phuongAnA, phuongAnB, phuongAnC, phuongAnD, dapAnDung);
        //databaseHelper.getAllCauHoiTracNghiem();
//        Log.e("CLoese","CLoese");
    }

}