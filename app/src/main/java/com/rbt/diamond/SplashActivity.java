package com.rbt.diamond;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    protected VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        videoView = findViewById(R.id.videoView);



        videoView.setVideoURI(R.mipmap.splash);

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}