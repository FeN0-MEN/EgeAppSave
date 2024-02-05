package com.example.egeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void logOut(View view) {
        LogingActivity.signOut();
        Intent intent = new Intent(this, LogingActivity.class);
        startActivity(intent);
    }
    public void onClickTest(View view){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
    public void onClickChoiceTheory(View view){
        Intent intent = new Intent(this, ChoosingTheoryActivity.class);
        startActivity(intent);
    }
}