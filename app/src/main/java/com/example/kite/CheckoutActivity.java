package com.example.kite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class CheckoutActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
   Dialog dialog;
    EditText exp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        dialog = new Dialog(CheckoutActivity.this);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        exp = (EditText) findViewById(R.id.exp);
//        exp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.setContentView(R.layout.date_picker);
//                dialog.create();
//                dialog.show();
//
//            }
//        });
        exp.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dialog.setContentView(R.layout.date_picker);
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }
}