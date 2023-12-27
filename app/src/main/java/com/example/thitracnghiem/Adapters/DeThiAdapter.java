//package com.example.thitracnghiem.Adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.thitracnghiem.CauHoiTracNghiemActivity;
//import com.example.thitracnghiem.Models.DeThi;
//import com.example.thitracnghiem.R;
//import com.example.thitracnghiem.databinding.ItemDethiBinding;
//
//import java.util.ArrayList;
//
//public class DeThiAdapter extends RecyclerView.Adapter<DeThiAdapter.viewHolder>{
//    Context context;
//    ArrayList<DeThi> list;
//
//    public DeThiAdapter(Context context, ArrayList<DeThi> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(context).inflate(R.layout.item_dethi,parent,false);
//        return new viewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//        DeThi model=list.get(position);
//        holder.binding.DeThiSo.setText("Đề thi số - "+String.valueOf(model.getSoDeThi()));
//        Log.e("So de thi",String.valueOf(model.getSoDeThi()));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, CauHoiTracNghiemActivity.class);
//                intent.putExtra("sodethi",model.getSoDeThi());
//                context.startActivity(intent);
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class viewHolder extends RecyclerView.ViewHolder{
//
//        ItemDethiBinding binding;
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//            binding=ItemDethiBinding.bind(itemView);
//        }
//    }
//}
package com.example.thitracnghiem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thitracnghiem.CauHoiTracNghiemActivity;
import com.example.thitracnghiem.Models.DeThi;
import com.example.thitracnghiem.R;
import com.example.thitracnghiem.databinding.ItemDethiBinding;

import java.util.ArrayList;
import java.util.List;

public class DeThiAdapter extends RecyclerView.Adapter<DeThiAdapter.viewHolder> {
    Context context;
    ArrayList<DeThi> list;

    public DeThiAdapter(Context context, ArrayList<DeThi> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dethi, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DeThi model = list.get(position);
        holder.binding.DeThiSo.setText("Đề thi số - " + String.valueOf(model.getSoDeThi()));
        Log.e("So de thi", String.valueOf(model));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CauHoiTracNghiemActivity.class);
                intent.putExtra("iddethi", model.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ItemDethiBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemDethiBinding.bind(itemView);
        }
    }
}
