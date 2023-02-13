package com.example.kite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.CurrencyViewholder> {
    private ArrayList<CurrencyModel> currencyModels;
    private Context context;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    public CurrencyRVAdapter(ArrayList<CurrencyModel> currencyModels, Context context) {
        this.currencyModels = currencyModels;
        this.context = context;
    }

    // below is the method to filter our list.
    public void filterList(ArrayList<CurrencyModel> filterlist) {
        // adding filtered list to our
        // array list and notifying data set changed
        currencyModels = filterlist;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_rv_item, parent, false);
        return new CurrencyRVAdapter.CurrencyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVAdapter.CurrencyViewholder holder, @SuppressLint("RecyclerView") int position) {
        // on below line we are setting data to our item of
        // recycler view and all its views.
        DecimalFormat df2 = new DecimalFormat("#,###");
        CurrencyModel modal = currencyModels.get(position);
        holder.nameTV.setText(modal.getName());
        holder.symbolTV.setText(modal.getSymbol());
        holder.rateTV.setText("NGN " + df2.format(modal.getPrice()));
        if(holder.nameTV.getText().toString().toLowerCase().equals("bitcoin")){
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.bitcoin_img));
        }
        else if(holder.nameTV.getText().toString().toLowerCase().equals("tether")){
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.tether));
        }
        else if(holder.nameTV.getText().toString().toLowerCase().equals("bnb")){
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.bnb));
        }
        else if(holder.nameTV.getText().toString().toLowerCase().equals("usd coin")){
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.usd));
        }
        else if(holder.nameTV.getText().toString().toLowerCase().equals("xrp")){
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.xrp));
        }
        else if(holder.nameTV.getText().toString().toLowerCase().equals("binance usd")){
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.binance));
        }
        else{
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.etherium));
        }

        //holder.symbolTV.setText(modal.getSymbol());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(),Statistics.class);
                //i.putExtra("symbol",holder.symbolTV.getText());
                i.putExtra("name",holder.nameTV.getText());
                i.putExtra("price",holder.rateTV.getText());
                i.putExtra("symbol",holder.symbolTV.getText());
                CurrencyModel modal = currencyModels.get(position);
                SharedPreferences mypref = context.getSharedPreferences("price",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mypref.edit();
                editor.putString("price",String.valueOf(modal.getPrice()));
                editor.putString("c_name",holder.nameTV.getText().toString().toLowerCase());
                editor.apply();
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // on below line we are returning
        // the size of our array list.
        return currencyModels.size();
    }

    // on below line we are creating our view holder class
    // which will be used to initialize each view of our layout file.
    public class CurrencyViewholder extends RecyclerView.ViewHolder {
        private TextView symbolTV, rateTV, nameTV;
        private ImageView icon;
        public CurrencyViewholder(@NonNull View itemView) {
            super(itemView);
            // on below line we are initializing all
            // our text views along with  its ids.
            symbolTV = itemView.findViewById(R.id.msymbol);
            icon = itemView.findViewById(R.id.icon);
            rateTV = itemView.findViewById(R.id.rate);
            nameTV = itemView.findViewById(R.id.username);
        }
    }
}
