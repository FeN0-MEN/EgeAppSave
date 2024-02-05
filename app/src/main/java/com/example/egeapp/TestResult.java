package com.example.egeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TestResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        TextView ResultTextView = findViewById(R.id.ResultTextView);
        TextView CongratulationsTextView = findViewById(R.id.CongratulationsTextView);
        TextView ScoreTextView = findViewById(R.id.ScoreTextView);
        int globalScore = getIntent().getIntExtra("Score", 0);
        if ((globalScore == 1) || (globalScore == 21)) {
            ScoreTextView.setText("БАЛЛ");
        }
        else if ((globalScore > 1) && (globalScore < 5)) {
            ScoreTextView.setText("БАЛЛА");
        }
        else if ((globalScore > 21) && (globalScore < 25)) {
            ScoreTextView.setText("БАЛЛА");
        }
        else {
            ScoreTextView.setText("БАЛЛОВ");
        }

        if (globalScore > 20) {
            CongratulationsTextView.setText("ПОЗДРАВЛЯЕМ!");
        }
        else if ((globalScore <= 20) && (globalScore > 7))  {
            CongratulationsTextView.setText("НЕПЛОХО!");
        }
        else {
            CongratulationsTextView.setText("К СОЖАЛЕНИЮ,");
        }
        ResultTextView.setText(String.valueOf(globalScore));
    }
    public void Exit (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}