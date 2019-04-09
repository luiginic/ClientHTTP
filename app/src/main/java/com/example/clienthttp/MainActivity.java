package com.example.clienthttp;
import ApiManager.ApiManager;
import de.hdodenhof.circleimageview.CircleImageView;


import android.graphics.Bitmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


//import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import personal.data.PersonalData;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CLIENT.MainActivity";
   Button viewTreatmentBtn;
//    Button reactionBtn = findViewById(R.id.reportChangesBtn);
    CircleImageView avatar;
    TextView name;
    PersonalData personalData;
//    final View statsView = findViewById(R.id.statsView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewTreatmentBtn = findViewById(R.id.viewTreatmentBtn);
        if(!viewTreatmentBtn.isEnabled())
            viewTreatmentBtn.setBackgroundResource(R.drawable.button_gradient_disabled);
        else viewTreatmentBtn.setBackgroundResource(R.drawable.button_gradient);
        String jsonURL = "https://api.myjson.com/bins/arg9u";
        String imgURL = "https://cdn.shopify.com/s/files/1/3026/6974/files/happy-alpacas-landscape_1024x1024.jpg?v=1532619630";

        name = findViewById(R.id.name);
        personalData = new PersonalData();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, jsonURL, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG,"Name is setting to: " + response.get("name").toString());
                            personalData.setName(response.get("name").toString());
                            name.setText((CharSequence)personalData.getName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG,"Message recived, name set!");
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
                }, 0, 0, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        avatar.setImageResource(R.drawable.no_image_user);
                    }
                });

        ApiManager.getInstance(this.getApplicationContext()).addToRequestQueue(imageRequest);
    }
}
