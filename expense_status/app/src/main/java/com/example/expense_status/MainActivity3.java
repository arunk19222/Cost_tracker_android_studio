package com.example.expense_status;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2500);
    }
}