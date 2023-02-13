package com.example.kite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    Button login;
    EditText email,password;
    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btn_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users")
                        .whereEqualTo("email",email.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult()){
                                        if(queryDocumentSnapshot.get("email").equals(email.getText().toString())&& queryDocumentSnapshot.get("password").equals(password.getText().toString())){
                                            Intent i = new Intent(Login.this,Home.class);
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString(EMAIL_KEY, email.getText().toString());
                                            editor.apply();
                                            i.putExtra("email",email.getText().toString());
                                            Login.this.startActivity(i);
                                            Login.this.finish();
                                            Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(Login.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }


                            }
                        })
                        .addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(Login.this,"Invalid email or password",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });



    }

}