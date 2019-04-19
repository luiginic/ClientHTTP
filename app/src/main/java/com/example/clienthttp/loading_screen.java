package com.example.clienthttp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.ImageView;
import android.widget.ProgressBar;

import loading_screen.ProgressBarAnimation;



import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class loading_screen extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView logo;
    private static final String TAG = "CLIENT.loading_screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        getWindow().setFlags(FLAG_FULLSCREEN,
                FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progressBar);
        logo=findViewById(R.id.logo);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this,progressBar,0f,100f);
        anim.setDuration(2000);
        progressBar.setAnimation(anim);

    }



}



