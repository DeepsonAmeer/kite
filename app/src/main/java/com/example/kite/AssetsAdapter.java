package com.example.kite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AssetsAdapter extends RecyclerView.Adapter<AssetsAdapter.MyViewHolder> {
    Context context;
    ArrayList<Assets> assets;
    public AssetsAdapter(Context context, ArrayList<Assets> assets) {
        this.context = context;
        this.assets = assets;
    }


    @NonNull
    @Override
    public AssetsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.asset_rv_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetsAdapter.MyViewHolder holder, int position) {
        DecimalFormat df2 = new DecimalFormat("#,###");
        Assets asset = assets.get(position);
        holder.name.setText(asset.name);
        holder.amount.setText("NGN "+ df2.format(asset.amount));
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,amount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.asset_name);
            amount = itemView.findViewById(R.id.asset_amount);
        }
    }
}
