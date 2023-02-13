package com.example.kite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class Sell extends AppCompatActivity {

    Button sell;
    EditText amount;
    TextView textView = (TextView) findViewById(R.id.textView10);
    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "shared_prefs";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        amount = (EditText) findViewById(R.id.sell_amount);
        amount.setSelected(true);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        String email = sharedpreferences.getString("email_key", null);
        sharedpreferences = getSharedPreferences("price",Context.MODE_PRIVATE);
        String price = sharedpreferences.getString("price",null);
        String c_name = sharedpreferences.getString("c_name",null);
        sell = (Button) findViewById(R.id.buy_amount_btn);
        textView.setText("how much "+c_name+" do you want to sell");
        db.collection("users")
                .whereEqualTo("email",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            double balance = documentSnapshot.getDouble("balance");
                            double amountToSell = Double.parseDouble(String.valueOf(amount.getText()));
                            String documentId = documentSnapshot.getId();
                            double x = (Double.parseDouble(String.valueOf(price)) / 100) * amountToSell;
                            balance += x;
                            Map<String,Object> up = new HashMap<>();
                            up.put("balance",balance);
                            db.collection("users")
                                    .document(documentId)
                                    .update(up)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Sell.this,"Currency Sold successfully",Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Sell.this,"An error occurred",Toast.LENGTH_SHORT).show();
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
                                                        double amountfdb = dc.getDouble("amount") - amountToSell;
                                                        String documentIdc = dc.getId();
                                                        Map<String,Object> ata = new HashMap<>();
                                                        ata.put("amount",amountfdb);
                                                        db.collection("currencies")
                                                                .document(documentIdc)
                                                                .update(ata)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        Intent i = new Intent(Sell.this,Asset.class);
                                                                        startActivity(i);
                                                                        Sell.this.finish();
                                                                        Toast.makeText(Sell.this, "Currency sold", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });

                                                    }
                                                }
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}
