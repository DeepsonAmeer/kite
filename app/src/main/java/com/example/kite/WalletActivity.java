package com.example.kite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class WalletActivity extends AppCompatActivity {

    ImageButton home,asset,wallet,profile;
    Button withdaw,recharge;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        home = (ImageButton) findViewById(R.id.w_btn_home);
        asset = (ImageButton) findViewById(R.id.w_btn_assets);
        wallet = (ImageButton) findViewById(R.id.w_btn_wallet);
        profile = (ImageButton) findViewById(R.id.w_btn_profile);
        withdaw = (Button) findViewById(R.id.w_btn_widthraw);
        recharge = (Button) findViewById(R.id.w_btn_recharge);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletActivity.this,Home.class);
                startActivity(i);
                WalletActivity.this.finish();
            }
        });
        asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletActivity.this,Asset.class);
                startActivity(i);
                WalletActivity.this.finish();
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletActivity.this,WalletActivity.class);
                startActivity(i);
                WalletActivity.this.finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletActivity.this,ProfileActivity.class);
                startActivity(i);
                WalletActivity.this.finish();
            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WalletActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WalletActivity.this,CheckoutActivity.class);
                startActivity(i);
            }
        });
        withdaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WalletActivity.this,WithdrawActivity.class);
                startActivity(i);
            }
        });

    }
}