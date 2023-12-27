package com.example.thitracnghiem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thitracnghiem.ChiTietLichSuActivity;
import com.example.thitracnghiem.Models.DeThi;
import com.example.thitracnghiem.R;
import com.example.thitracnghiem.databinding.ItemLichsudeBinding;

import java.util.ArrayList;

public class LichSuDeAdapter extends RecyclerView.Adapter<LichSuDeAdapter.viewHolder> {
    Context context;
    ArrayList<DeThi> list;

    public LichSuDeAdapter(Context context, ArrayList<DeThi> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lichsude, parent, false);
        return new LichSuDeAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DeThi model = list.get(position);
        holder.binding.DeThiSo.setText("Đề thi số - " + String.valueOf(model.getSoDeThi()));
        Log.e("So de thi", String.valueOf(model));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietLichSuActivity.class);
                intent.putExtra("iddethi", model.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ItemLichsudeBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemLichsudeBinding.bind(itemView);
        }
    }
}
