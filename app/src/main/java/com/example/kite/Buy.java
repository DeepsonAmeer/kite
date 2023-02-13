package com.example.kite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Buy extends AppCompatActivity {

    Button buy;
    EditText amount;
    TextView textView;
    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "shared_prefs";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }


        amount = (EditText) findViewById(R.id.amount_tv);
        buy = (Button) findViewById(R.id.buy_amount_btn);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        textView = (TextView)findViewById(R.id.textViewbuy);
        String email = sharedpreferences.getString("email_key", null);
        sharedpreferences = getSharedPreferences("price",Context.MODE_PRIVATE);
        String price = sharedpreferences.getString("price",null);
        String c_name = sharedpreferences.getString("c_name",null);
        textView.setText("how much "+c_name+" do you want to buy?");
        //Toast.makeText(Buy.this,"information fetched "+email+" "+price,Toast.LENGTH_SHORT).show();
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users")
                        .whereEqualTo("email",email)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                        double balance = documentSnapshot.getDouble("balance");
                                        double amountToBuy = Double.parseDouble(String.valueOf(amount.getText()));
                                        String documentId = documentSnapshot.getId();

                                        if(amountToBuy<=balance){
                                            double x = (Integer.parseInt(String.valueOf(price)) / 100)* amountToBuy;
                                            balance -= amountToBuy;
                                            Map<String,Object> up = new HashMap<>();
                                            up.put("balance",balance);
                                            db.collection("users")
                                                    .document(documentId)
                                                    .update(up)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(Buy.this,"Currency purchased successfully",Toast.LENGTH_SHORT).show();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(Buy.this,"An error occurred",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                            db.collection("currencies")
                                                    .whereEqualTo("owner_email",email)
                                                    .get()
                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                            if(task.isSuccessful()){
                                                                for(DocumentSnapshot dc:task.getResult()){
                                                                    if(dc.get("name").equals(c_name)){
                                                                        double amountfdb = dc.getDouble("amount") + x;
                                                                        String documentIdc = dc.getId();
                                                                        Map<String,Object> ata = new HashMap<>();
                                                                        ata.put("amount",amountfdb);
                                                                        db.collection("currencies")
                                                                                .document(documentIdc)
                                                                                .update(ata)
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        Intent i = new Intent(Buy.this,Asset.class);
                                                                                        startActivity(i);
                                                                                        Buy.this.finish();
                                                                                        //Toast.makeText(Buy.this, "Currency added", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                });

                                                                    }
                                                                }
                                                            }
                                                        }
                                                    });
                                        }
                                        else{
                                            Toast.makeText(Buy.this,"Insufficient balance",Toast.LENGTH_SHORT).show();
                                        }

                                }
                            }
                        });
            }
        });
    }

}