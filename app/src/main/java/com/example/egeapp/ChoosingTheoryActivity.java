package com.example.egeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoosingTheoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_theory);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button10 = findViewById(R.id.button10);
        Button button11 = findViewById(R.id.button11);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/Yk9tTNX/Screenshot-20230524-213129-3.png"); // Передача имени файла изображения
                intent.putExtra("image2", "https://i.ibb.co/jDS7qPp/Screenshot-20230524-213139-4.png");
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/DV9xcGp/slide-21.jpg"); // Передача имени файла изображения
                intent.putExtra("image2", (String) null);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/w68qS0q/Screenshot-20230524-214727-3.png"); // Передача имени файла изображения
                intent.putExtra("image2", (String) null);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/f9rK8v0/Screenshot-20230524-214740-3.png"); // Передача имени файла изображения
                intent.putExtra("image2", (String) null);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/YdpqN29/Screenshot-20230524-214849-3.png"); // Передача имени файла изображения
                intent.putExtra("image2", "https://i.ibb.co/ChBGCT5/Screenshot-20230524-214901-3.png");
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/6XSc6tg/Screenshot-20230524-214930-3.png"); // Передача имени файла изображения
                intent.putExtra("image2", "https://i.ibb.co/swZJwzm/Screenshot-20230524-215310-4.png");
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/yW8WzZ3/Screenshot-20230524-215324-3.png"); // Передача имени файла изображения
                intent.putExtra("image2", "https://i.ibb.co/gW4wMts/Screenshot-20230524-215335-3.png");
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/NtS3q5X/Screenshot-20230524-215406-2.png"); // Передача имени файла изображения
                intent.putExtra("image2", (String) null);
                startActivity(intent);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/8PLTRz2/Screenshot-20230524-215415-2.png"); // Передача имени файла изображения
                intent.putExtra("image2", "https://i.ibb.co/FwC1xDb/Screenshot-20230524-215424-3.png");
                startActivity(intent);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/K9YXBSh/Screenshot-20230524-215438-2.png"); // Передача имени файла изображения
                intent.putExtra("image2", (String) null);
                startActivity(intent);
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов активности с передачей входных данных
                Intent intent = new Intent(ChoosingTheoryActivity.this, TheoryActivity.class);
                intent.putExtra("image", "https://i.ibb.co/0sgrjkM/Screenshot-20230524-215507-2.png"); // Передача имени файла изображения
                intent.putExtra("image2", (String) null);
                startActivity(intent);
            }
        });
    }

    public void backToMenu (View view) {
        finish();
    }
}