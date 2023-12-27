package com.example.thitracnghiem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thitracnghiem.Models.CauHoiTracNghiem;
import com.example.thitracnghiem.Models.LichSuLamBai;
import com.example.thitracnghiem.Models.DanhSachLichSuCauTraLoi;
import com.example.thitracnghiem.Models.DeThi;
import com.example.thitracnghiem.SQLHelper.DatabaseHelper;
//import com.example.thitracnghiem.SQLHelper.KetThucActivity;
import com.example.thitracnghiem.databinding.ActivityCauHoiTracNghiemBinding;

import java.util.ArrayList;
import java.util.List;

public class CauHoiTracNghiemActivity extends AppCompatActivity {
    ActivityCauHoiTracNghiemBinding binding;
    private DatabaseHelper databaseHelper;
    private List<CauHoiTracNghiem> list;
    private int count = 0;
    private int position = 0;
    private int dapandung = 0;
    CountDownTimer thoigian;
    private long initialTimeMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCauHoiTracNghiemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        int idDeThi = intent.getIntExtra("iddethi", 0);
        resetThoiGian();
        thoigian.start();
        Log.e("IDDDDDDDD",String.valueOf(idDeThi));
        Toast.makeText(CauHoiTracNghiemActivity.this,"Số đề thi"+idDeThi, Toast.LENGTH_LONG).show();
        list=databaseHelper.getCauHoiTracNghiemByDeThi(idDeThi);
        databaseHelper.deleteLichSuById(idDeThi);
        for (int i = 0; i < 4; i++) {
            binding.LuaChonDapAn.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    KiemTraDapAn((Button)view);

                }
            });
        }
        playanimation(binding.CauHoi,0,list.get(position).getNoiDungCauHoi());
        binding.btnCautieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thoigian!=null){
                    thoigian.cancel();
                }
                thoigian.start();
                binding.btnCautieptheo.setEnabled(false);
                binding.btnCautieptheo.setAlpha(0.3f);
                enableOption(true);
                position++;
                if(position==list.size()){
                    Intent intent =new Intent(CauHoiTracNghiemActivity.this,KetThucActivity.class);
                    intent.putExtra("dapandung",dapandung);
                    intent.putExtra("tongsocaudalam",list.size());
                    intent.putExtra("idDeThi",idDeThi);
                    startActivity(intent);
                    finish();
                }
                count=0;
                playanimation(binding.CauHoi,0,list.get(position).getNoiDungCauHoi());
            }


        });
    }

    private void resetThoiGian() {
        thoigian=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                binding.ThoiGian.setText(String.valueOf(l/1000));
            }
            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(CauHoiTracNghiemActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.timeout_dialog);
                dialog.findViewById(R.id.tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(CauHoiTracNghiemActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.show();
            }
        };
        initialTimeMillis = System.currentTimeMillis();
        thoigian.start();

    }

    private void playanimation(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                        if(value==0 && count<4){
                            String luachon="";
                            if(count==0){
                                luachon=list.get(position).getPhuongAnA();
                            }else if(count==1){
                                luachon=list.get(position).getPhuongAnB();
                            }else if(count==2){
                                luachon=list.get(position).getPhuongAnC();
                            }else if(count==3){
                                luachon=list.get(position).getPhuongAnD();
                            }
                            playanimation(binding.LuaChonDapAn.getChildAt(count),0,luachon);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        if(value==0){
                            try{
                                ((TextView)view).setText(data);
                                binding.SoCauHoi.setText(position+1+"/"+list.size());

                            }catch (Exception e){
                                ((Button)view).setText(data);
                                System.out.println(e.toString());
                            }
                            view.setTag(data);
                            playanimation(view,1,data);
                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });

    }

    private void KiemTraDapAn(Button chondapan ) {
        if(thoigian!=null){
            thoigian.cancel();
        }
        enableOption(false );
        binding.btnCautieptheo.setEnabled(true);
        binding.btnCautieptheo.setAlpha(1);
        if(chondapan.getText().toString().equals(list.get(position).getDapAnDung())){
            dapandung++;
            chondapan.setBackgroundResource(R.drawable.dapandung);
        }else{
            chondapan.setBackgroundResource(R.drawable.dapansai);
            Button dapanchinhxac=(Button) binding.LuaChonDapAn.findViewWithTag(list.get(position).getDapAnDung());
            dapanchinhxac.setBackgroundResource(R.drawable.dapandung);
        }
        long currentTimeMillis = System.currentTimeMillis();
        long thoiGianTraLoi = currentTimeMillis - initialTimeMillis;
        String dapAnDung = list.get(position).getDapAnDung();
        String dapAnDaChon = chondapan.getText().toString();
        String thoiGianLamBai = String.valueOf(thoiGianTraLoi / 1000); // Đổi thời gian thành giây
        databaseHelper.addLichSuLamBai(list.get(position).getIdDeThi(), thoiGianLamBai, dapAnDaChon, dapAnDung, list.get(position).getNoiDungCauHoi(), list.get(position).getPhuongAnA(), list.get(position).getPhuongAnB(), list.get(position).getPhuongAnC(), list.get(position).getPhuongAnD());
        databaseHelper.getAllLichSuLamBai();

    }
    private void enableOption(boolean b) {
        for (int i=0;i<4;i++){
            binding.LuaChonDapAn.getChildAt(i).setEnabled(b);
            if(b){
                binding.LuaChonDapAn.getChildAt(i).setBackgroundResource(R.drawable.btn_option_back);
            }
        }
    }
}