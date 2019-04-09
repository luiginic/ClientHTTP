package com.example.clienthttp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import ApiManager.ApiManager;
import de.hdodenhof.circleimageview.CircleImageView;
import loading_screen.ProgressBarAnimation;


import personal.data.PersonalData;

public class loading_screen extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView logo;
    private static final String TAG = "CLIENT.loading_screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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


