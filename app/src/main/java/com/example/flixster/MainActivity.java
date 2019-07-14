package com.example.flixster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,recyclerview.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}
