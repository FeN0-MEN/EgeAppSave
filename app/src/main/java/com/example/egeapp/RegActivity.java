package com.example.egeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

    }
    public void StartLogingActivity(View v){
        Intent intent = new Intent(this, LogingActivity.class);
        startActivity(intent);
    }

    public void registerUser(View view) {
        EditText emailEditText = findViewById(R.id.background);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Пожалуйста, введите почту", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Пожалуйста, введите пароль", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegActivity.this, "Регистрация завершена", Toast.LENGTH_SHORT).show();


                            // Пользователь успешно зарегистрирован, можно перейти на другой экран или выполнить другие действия
                        } else {
                            Toast.makeText(RegActivity.this, "Ошибка регистрации данных: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }



}