package com.azoza.rcafe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.azoza.rcafe.R;
import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Rcafe_FullScreen);
        setContentView(R.layout.activity_splash);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        ImageView imageView = findViewById(R.id.splashLog);
        Picasso.get().load(R.drawable.logo)
                .resize(400,500)
                .into(imageView);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.splashProgressBar).setVisibility(View.VISIBLE);
            }
        },1000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.splashProgressBar).setVisibility(View.INVISIBLE);
                Intent intent1 = new Intent(SplashActivity.this, OnBoardActivity.class);
               //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        },5000);
    }
}