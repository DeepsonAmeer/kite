package com.example.kite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Splash extends AppCompatActivity {

    Button b;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        b = (Button) findViewById(R.id.btn_gs);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Splash.this,Login.class);
                Splash.this.startActivity(i);
                Splash.this.finish();
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(Splash.this,Login.class);
//                Splash.this.startActivity(i);
//                Splash.this.finish();
//            }
//        },5000);
    }
}