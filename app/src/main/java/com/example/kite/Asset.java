package com.example.kite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Asset extends AppCompatActivity {

    ImageButton home,asset,wallet,profile;
    RecyclerView recyclerView;
    ArrayList<Assets> assetsArrayList;
    AssetsAdapter assetsAdapter;
    FirebaseFirestore db;
    Button send,receive;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        home = (ImageButton) findViewById(R.id.a_btn_home);
        asset = (ImageButton) findViewById(R.id.a_btn_assets);
        wallet = (ImageButton) findViewById(R.id.a_btn_wallet);
        profile = (ImageButton) findViewById(R.id.a_btn_profile);
        send = (Button) findViewById(R.id.a_btn_send);
        recyclerView = (RecyclerView) findViewById(R.id.myRV);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        assetsArrayList = new ArrayList<Assets>();
        assetsAdapter = new AssetsAdapter(this,assetsArrayList);
        recyclerView.setAdapter(assetsAdapter);
        EventChangeListener();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Asset.this,Home.class);
                startActivity(i);
                Asset.this.finish();
            }
        });
        asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Asset.this,Asset.class);
                startActivity(i);
                Asset.this.finish();
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Asset.this,WalletActivity.class);
                startActivity(i);
                Asset.this.finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Asset.this,ProfileActivity.class);
                startActivity(i);
                Asset.this.finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Asset.this,SendActivity.class);
                startActivity(i);
            }
        });
    }

    private void EventChangeListener() {
        db.collection("currencies")
                .whereEqualTo("owner_email","johndoe@gmail.com")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for(DocumentChange dc:value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                assetsArrayList.add(dc.getDocument().toObject(Assets.class));
                            }
                            assetsAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

}