package com.example.thitracnghiem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thitracnghiem.DeThiActivity;
import com.example.thitracnghiem.Models.MonHoc;
import com.example.thitracnghiem.R;
import com.example.thitracnghiem.databinding.ItemMonhocBinding;

import java.util.ArrayList;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.viewHolder>{
    Context context;
    ArrayList<MonHoc> list;

    public MonHocAdapter(Context context, ArrayList<MonHoc> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_monhoc,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        MonHoc model=list.get(position);
        holder.binding.tenmonhoc.setText(model.getTenMonHoc());
        int imageResourceId = model.getHinhAnh();
        Icon icon = Icon.createWithResource(holder.itemView.getContext(), imageResourceId);
        holder.binding.imgMonhoc.setImageIcon(icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DeThiActivity.class);
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
        ItemMonhocBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemMonhocBinding.bind(itemView);
        }
    }
}