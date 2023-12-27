package com.example.thitracnghiem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thitracnghiem.DanhSachDeThiDaLamActivity;
import com.example.thitracnghiem.Models.MonHoc;
import com.example.thitracnghiem.R;
import com.example.thitracnghiem.databinding.ItemHistoryBinding;

import java.util.ArrayList;

public class LichSuMonAdapter extends RecyclerView.Adapter<LichSuMonAdapter.viewHolder> {
    private ArrayList<MonHoc> list;
    private Context context;

    public LichSuMonAdapter(ArrayList<MonHoc> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        MonHoc model=list.get(position);
        holder.binding.idMon.setText(model.getTenMonHoc());
        holder.binding.cvItemHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DanhSachDeThiDaLamActivity.class);
                intent.putExtra("idmonhoc",model.getId());
                intent.putExtra("tenmon",model.getTenMonHoc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        ItemHistoryBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemHistoryBinding.bind(itemView);
        }
    }
}