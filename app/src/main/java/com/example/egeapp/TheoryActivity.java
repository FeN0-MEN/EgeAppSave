package com.example.egeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class TheoryActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);



        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);

        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);

        String imageURL1 = getIntent().getStringExtra("image");
        String imageURL2 = getIntent().getStringExtra("image2");

        if (imageURL1 != null) {
            imageView1.setVisibility(View.VISIBLE);
            Picasso.get().load(imageURL1).into(imageView1);
        }

        if (imageURL2 != null) {
            imageView2.setVisibility(View.VISIBLE);
            Picasso.get().load(imageURL2).into(imageView2);
        }
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == imageView1) {
                    imageUrl = imageURL1;
                } else if (v == imageView2) {
                    imageUrl = imageURL2;
                } else {
                    return;
                }
                // Создание диалогового окна с настраиваемым макетом
                Dialog dialog = new Dialog(TheoryActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.dialog_fullscreen_image);

                // Нахождение ImageView внутри диалогового окна
                ImageView fullscreenImageView = dialog.findViewById(R.id.fullscreen_imageview);

                // Загрузка изображения по ссылке с помощью Picasso и установка его в ImageView
                Picasso.get().load(imageUrl).into(fullscreenImageView);

                // Обработчик нажатия для закрытия диалогового окна
                fullscreenImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // Отображение диалогового окна
                dialog.show();
            }
        };
        imageView1.setOnClickListener(clickListener);
        imageView2.setOnClickListener(clickListener);
    }

    public void backToChosing (View view) {
        finish();
    }
}