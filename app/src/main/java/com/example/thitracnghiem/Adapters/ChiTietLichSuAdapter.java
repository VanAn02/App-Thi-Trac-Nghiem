package com.example.thitracnghiem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thitracnghiem.Models.LichSuLamBai;
import com.example.thitracnghiem.R;
import com.example.thitracnghiem.databinding.ItemChitietBinding;

import java.util.ArrayList;

public class ChiTietLichSuAdapter extends RecyclerView.Adapter<ChiTietLichSuAdapter.viewHolder> {

    Context context;
    ArrayList<LichSuLamBai> list;
    public ChiTietLichSuAdapter(Context context, ArrayList<LichSuLamBai> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chitiet, parent, false);
        return new ChiTietLichSuAdapter.viewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        LichSuLamBai model = list.get(position);
        holder.binding.idCauHoi.setText(model.getCauHoi());
        holder.binding.idDapAnDaChon.setText(model.getDapAnDaChon());
        holder.binding.idDapAnDung.setText(model.getDapAnDung());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        ItemChitietBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemChitietBinding.bind(itemView);
        }
    }
}
