package com.example.tej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class showuploads extends AppCompatActivity {

    Button button4;
    ImageView imageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuploads);
        button4 = findViewById(R.id.button4);
       imageView = findViewById(R.id.imageView4);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = getIntent();
               String URL= it.getStringExtra("url");

              //  String URL ="https://firebasestorage.googleapis.com/v0/b/pratap-1b756.appspot.com/o/uploads%2F1580525009973.jpg?alt=media&token=7c39813f-ab3d-42c3-be08-513bbc97ead8";
                Glide.with(getApplicationContext()).load(URL).into(imageView);
            }
        });

    }
}
