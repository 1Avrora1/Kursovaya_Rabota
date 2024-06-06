package com.mirea.kt.ribo;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> {
    interface OnDistrictClickListener{
        void onDistrictClick(District district, int position);
    }
    private ArrayList<District> districts;
    private OnDistrictClickListener onDistrictClickListener;


    public DistrictAdapter(ArrayList<District> districts, OnDistrictClickListener onDistrictClickListener) {
        this.districts = districts;
        this.onDistrictClickListener = onDistrictClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView acrView;
        private final TextView nameView;
        private final ImageView avatView;
        ViewHolder(View view){
            super(view);
            acrView = view.findViewById(R.id.DistrictAcronym);
            nameView = view.findViewById(R.id.DistrictName);
            avatView = view.findViewById(R.id.Avatar);
        }
    }

    @NonNull
    @Override
    public DistrictAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictAdapter.ViewHolder holder, int position) {
        District district = districts.get(position);
        holder.acrView.setText(String.format("%s", district.getAcronym()));
        holder.nameView.setText(String.format("%s",district.getName()));
        if(position==0){
            holder.avatView.setImageResource(R.drawable.coa);
        }
        else if (position==1){
            holder.avatView.setImageResource(R.drawable.sao);
        }
        else if (position==2){
            holder.avatView.setImageResource(R.drawable.svao);
        }
        else if (position==3){
            holder.avatView.setImageResource(R.drawable.vao);
        }
        else if (position==4){
            holder.avatView.setImageResource(R.drawable.uvao);
        }
        else if (position==5){
            holder.avatView.setImageResource(R.drawable.uao);
        }
        else if (position==6){
            holder.avatView.setImageResource(R.drawable.uzao);
        }
        else if (position==7){
            holder.avatView.setImageResource(R.drawable.zao);
        }
        else if (position==8){
            holder.avatView.setImageResource(R.drawable.szao);
        }
        else if (position==9){
            holder.avatView.setImageResource(R.drawable.zel_ao);
        }
        else if (position==10){
            holder.avatView.setImageResource(R.drawable.tao);
        }
        else if (position==11){
            holder.avatView.setImageResource(R.drawable.nao);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onDistrictClickListener.onDistrictClick(district, holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return districts.size();
    }
}
