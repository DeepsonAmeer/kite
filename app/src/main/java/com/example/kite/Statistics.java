package com.example.kite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Statistics extends AppCompatActivity {

    TextView sym,nam,price;
    Button b,c;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        b = (Button)findViewById(R.id.buy_c);
        c = (Button)findViewById(R.id.sell_c);
        sym = (TextView) findViewById(R.id.symbol);
        nam = (TextView) findViewById(R.id.nam);
        price = (TextView) findViewById(R.id.price);
        sym.setText(getIntent().getStringExtra("symbol"));
        nam.setText(getIntent().getStringExtra("name"));
        price.setText(getIntent().getStringExtra("price"));


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Statistics.this,Buy.class);
                String[] arrOfStr = price.getText().toString().split(",");
                i.putExtra("price",arrOfStr);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Statistics.this,Sell.class);

                startActivity(i);
            }
        });
    }
}