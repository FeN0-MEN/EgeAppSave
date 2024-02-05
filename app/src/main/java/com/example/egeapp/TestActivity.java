package com.example.egeapp;




import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;


public class TestActivity extends AppCompatActivity {
    private TextView TaskText;
    private String imageUrl;
    private String downloadUrl;
    private String rightAnswer;
    private ImageView imageBox;
    private Button buttonDownload;
    private String textForTask;
    private Integer globalScore = 0;
    private Integer scoreForTask;
    private EditText answerEditText;
    private Integer number = 0;
    private String[] answerArray = new String[27];
    private Integer[] scoreArray = new Integer[27];
    private int index = 0;
    private int sumOfScore = 0;
    private String numberChoice;
    private int indexChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        answerEditText = findViewById(R.id.AnswerEditText);
        imageBox = findViewById(R.id.imageBox);
        TaskText = findViewById(R.id.TextTask);
        buttonDownload = findViewById(R.id.buttonDownload);
        buttonDownload.setVisibility(View.INVISIBLE);
        Button buttonNext = findViewById(R.id.buttonNext);
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setVisibility(View.INVISIBLE);
        String[] numberKeys = {"Number_1", "Number_2"};
        Random random = new Random();
        indexChoice = random.nextInt(numberKeys.length);
        numberChoice = numberKeys[indexChoice];
        loadTaskFromFirebase("Task_1", numberChoice);

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownload();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number >= 0) {
                    buttonBack.setVisibility(View.VISIBLE);
                }
                Context nice = v.getContext();
                String userInput = answerEditText.getText().toString().trim();
                if (answerEditText != null) {
                    answerArray[index] = userInput;
                    answerEditText.setText("");
                    if (userInput.equals(rightAnswer)) {
                        scoreArray[index] = scoreForTask;
                    }
                    else {
                        if (scoreArray[index] != null) {
                            if ((scoreArray[index] == 1) || scoreArray[index] == 2) {
                                scoreArray[index] = 0;
                            }
                        }
                    }
                }
                index ++;
                if (TextUtils.isEmpty(userInput)) {
                    Toast.makeText(nice, "Заполните ответ", Toast.LENGTH_SHORT).show();
                }
                String[] taskKeys = {"Task_1", "Task_2", "Task_3", "Task_4", "Task_5", "Task_6", "Task_7", "Task_8", "Task_9", "Task_10", "Task_11", "Task_12", "Task_13", "Task_14", "Task_15", "Task_16", "Task_17", "Task_18", "Task_19", "Task_20", "Task_21", "Task_22", "Task_23", "Task_24", "Task_25", "Task_26", "Task_27"};
                number += 1;
                if (number <= 26) {
                    loadTaskFromFirebase(taskKeys[number], numberChoice);
                    if (number == 26) {
                        buttonNext.setText("Завершить тест");
                    }
                }
                else {
                    for (int i = 0; i < scoreArray.length; i++) {
                        if (scoreArray[i] != null) {
                            sumOfScore += scoreArray[i];
                        }
                    }
                    globalScore = sumOfScore;
                    Context context = v.getContext();
                    Intent intent = new Intent(context, TestResult.class);
                    intent.putExtra("Score", globalScore);
                    context.startActivity(intent);
                }

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 0) {
                    if (number <= 26) {
                        buttonNext.setText("Перейти к следующему заданию");
                    }
                    Context nice = v.getContext();
                    String userInput = answerEditText.getText().toString().trim();
                    if (answerEditText != null) {
                        answerArray[index] = userInput;
                        answerEditText.setText("");
                        if (userInput.equals(rightAnswer)) {
                            scoreArray[index] = scoreForTask;
                        }
                        else {
                            if (scoreArray[index] != null) {
                                if ((scoreArray[index] == 1) || scoreArray[index] == 2) {
                                    scoreArray[index] = 0;
                                }
                            }
                        }
                    }
                    index--;
                    if (TextUtils.isEmpty(userInput)) {
                        Toast.makeText(nice, "Заполните ответ", Toast.LENGTH_SHORT).show();
                    }
                    String[] taskKeys = {"Task_1", "Task_2", "Task_3", "Task_4", "Task_5", "Task_6", "Task_7", "Task_8", "Task_9", "Task_10", "Task_11", "Task_12", "Task_13", "Task_14", "Task_15", "Task_16", "Task_17", "Task_18", "Task_19", "Task_20", "Task_21", "Task_22", "Task_23", "Task_24", "Task_25", "Task_26", "Task_27"};
                    number -= 1;
                    if (number <= 26) {
                        loadTaskFromFirebase(taskKeys[number], numberChoice);
                        if (number == 26) {
                            buttonNext.setText("Завершить тест");
                        }
                    } else {
                        for (int i = 0; i < scoreArray.length; i++) {
                            if (scoreArray[i] != null) {
                                sumOfScore += scoreArray[i];
                            }
                        }
                        globalScore = sumOfScore;
                        Context context = v.getContext();
                        Intent intent = new Intent(context, TestResult.class);
                        intent.putExtra("Score", globalScore);
                        context.startActivity(intent);
                    }
                }
            }
        });

    }

    public void onClickExit(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadTaskFromFirebase(String taskKey, String numberKey) {
        // Получение ссылки на узел задания в Firebase
        DatabaseReference taskRef = FirebaseDatabase.getInstance("https://appbase-66912-default-rtdb.europe-west1.firebasedatabase.app").getReference().child(taskKey).child(numberKey);

        taskRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Получение значений из Firebase и присвоение их переменным
                    textForTask = dataSnapshot.child("Question").getValue(String.class);
                    imageUrl = dataSnapshot.child("Img").getValue(String.class);
                    downloadUrl = dataSnapshot.child("FileLink").getValue(String.class);
                    rightAnswer = dataSnapshot.child("Correct_Answer").getValue(String.class);
                    scoreForTask = dataSnapshot.child("Score").getValue(Integer.class);

                    // Установка текста задания в TextView
                    TaskText.setText(textForTask);

                    // Если ссылка на картинку имеется, вы можете загрузить и отобразить ее в ImageView
                    if (imageUrl == null) {
                        imageBox.setVisibility(View.GONE);
                    }
                    else {
                        // Загрузка и отображение изображения
                        imageBox.setVisibility(View.VISIBLE);
                        Picasso.get().load(imageUrl).into(imageBox);
                    }
                    // Если ссылка на файл для скачивания имеется, вы можете предоставить пользователю возможность скачать его
                    if (downloadUrl != null) {
                        // Отображение кнопки для скачивания файла
                        buttonDownload.setVisibility(View.VISIBLE);
                    }
                    else {
                        buttonDownload.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Обработка ошибок чтения данных из Firebase
            }
        });
    }
    private void startDownload() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Загрузка файла");
        request.setDescription("Загрузка файла...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "file");

        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }
}

