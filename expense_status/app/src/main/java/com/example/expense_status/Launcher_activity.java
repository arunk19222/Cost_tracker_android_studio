package com.example.expense_status;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Launcher_activity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    Animation top_anim,bottom_anim;
    ImageFilterView imageFilterView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        constraintLayout=findViewById(R.id.bg_id);
        imageFilterView=findViewById(R.id.imageFilterView);
        textView=findViewById(R.id.textView2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        imageFilterView.setAnimation(top_anim);
        textView.setAnimation(bottom_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Launcher_activity.this,MainActivity.class));
                finish();
            }
        },5000);
    }
}