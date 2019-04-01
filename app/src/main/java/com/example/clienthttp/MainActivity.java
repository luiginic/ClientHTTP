package com.example.clienthttp;
import ApiManager.ApiManager;
import de.hdodenhof.circleimageview.CircleImageView;


import android.graphics.Bitmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


//import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CLIENT.MainActivity";
   Button viewTreatmentBtn;
//    Button reactionBtn = findViewById(R.id.reportChangesBtn);
    CircleImageView avatar;
//    TextView name = findViewById(R.id.name);
//    final View statsView = findViewById(R.id.statsView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewTreatmentBtn = findViewById(R.id.viewTreatmentBtn);
        if(viewTreatmentBtn.isEnabled()==false)
            viewTreatmentBtn.setBackgroundResource(R.drawable.button_gradient_disabled);
        else viewTreatmentBtn.setBackgroundResource(R.drawable.button_gradient);
        String jsonURL = "https://api.myjson.com/bins/arg9u";
        String imgURL = "https://cdn.vox-cdn.com/thumbor/1V9LV_FVRHj_EwzA_FCIyoi7_Ek=/0x0:800x450/1200x800/filters:focal(336x161:464x289)/cdn.vox-cdn.com/uploads/chorus_image/image/59883773/pikachu_wide.0.0.jpg";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, jsonURL, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        Log.d(TAG,"Message recived");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error fetching data!",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Message not recived");
                    }
                });

        ApiManager.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjectRequest);

        avatar = findViewById(R.id.avatar);
        ImageRequest imageRequest = new ImageRequest(imgURL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        Log.d(TAG,"Image recived");
                        avatar.setImageBitmap(bitmap);
                        Log.d(TAG,"Avatar is set");
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        avatar.setImageResource(R.drawable.no_image_user);
                    }
                });

        ApiManager.getInstance(this.getApplicationContext()).addToRequestQueue(imageRequest);


    }
}
