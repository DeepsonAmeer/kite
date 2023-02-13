package com.example.kite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    ImageButton home,asset,wallet,profile;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        home = (ImageButton) findViewById(R.id.p_btn_home);
        asset = (ImageButton) findViewById(R.id.p_btn_assets);
        wallet = (ImageButton) findViewById(R.id.p_btn_wallet);
        profile = (ImageButton) findViewById(R.id.p_btn_profile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,Home.class);
                startActivity(i);
                ProfileActivity.this.finish();
            }
        });
        asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,Asset.class);
                startActivity(i);
                ProfileActivity.this.finish();
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,WalletActivity.class);
                startActivity(i);
                ProfileActivity.this.finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,ProfileActivity.class);
                startActivity(i);
                ProfileActivity.this.finish();
            }
        });
    }
}