package com.example.clienthttp;
import ApiManager.ApiManager;
import de.hdodenhof.circleimageview.CircleImageView;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.clienthttp.treatment.ViewPagerAdapter;

import personal.data.PersonalData;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CLIENT.MainActivity";

    private Button viewTreatmentBtn;
    private Button makeAnAppointmentBtn;
    private  CircleImageView avatar;
    private TextView name;
    private PersonalData personalData = new PersonalData();
    private ViewPager mesurementsPager;
    private ViewPagerAdapter mesurementsPagerAdapter;
    private TabLayout mesurementsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewTreatmentBtn = findViewById(R.id.viewTreatmentBtn);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(isNetworkAvailable()){
            viewTreatmentBtn.setEnabled(true);
            setButtonCollor(viewTreatmentBtn);
        } else {
            viewTreatmentBtn.setEnabled(false);
            setButtonCollor(viewTreatmentBtn);
        }

        name = findViewById(R.id.name);
        retrieveAccountDetails();
        name.setText(personalData.getName());

        setAvatar();

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAccountDetails();
            }
        });
        viewTreatmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDataEntry();
            }
        });
        makeAnAppointmentBtn=findViewById(R.id.makeAnAppointment);
        makeAnAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Unavailable at this moment!",Toast.LENGTH_LONG).show();
                makeAnAppointmentBtn.setEnabled(false);
                setButtonCollor(makeAnAppointmentBtn);
            }
        });

        mesurementsPager = findViewById(R.id.mesurmentsView);
        mesurementsPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mesurementsPager.setAdapter(mesurementsPagerAdapter);
        mesurementsTab = findViewById(R.id.tabLayout);
        mesurementsTab.setupWithViewPager(mesurementsPager);

    }

    private void goToDataEntry(){
        Intent intent = new Intent(this,DataEntryActivity.class );
        intent.putExtra("personalInfo",personalData);
        this.startActivity(intent);
    }

    private void retrieveAccountDetails(){
        SharedPreferences prefs = getSharedPreferences("info.log", MODE_PRIVATE);
        String restoredText = prefs.getString("pacientId", null);
        if (restoredText != null) {
            //personalData.setPacientCode(prefs.getString("pacientId","")); //null is the default value.
            personalData.setName(prefs.getString("name", getResources().getString(R.string.name_goes_here)));//"getResources().getString(R.string.name_goes_here)" = "Name goes here" and it  is the default value.
           //personalData.setPhoneNumber(prefs.getString("phoneNo",getResources().getString(R.string.phoneNo_default)));//"getResources().getString(R.string.phoneNo_default)" = "No Phone No. provided" - default value
        }
    }

    private void goToAccountDetails(){
        Intent intent = new Intent(this, AccountInformationScreen.class);
//        intent.putExtra("personalInfo",personalData);
        intent.putExtra("avatar",(Bitmap)avatar.getDrawingCache());
        this.startActivity(intent);
    }

    private void setAvatar(){
//        String jsonURL = "https://api.myjson.com/bins/arg9u";
        String imgURL = "https://cdn.shopify.com/s/files/1/3026/6974/files/happy-alpacas-landscape_1024x1024.jpg?v=1532619630";

//        name = findViewById(R.id.name);
//        personalData = new PersonalData();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, jsonURL, (String) null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d(TAG,"Name is setting to: " + response.get("name").toString());
//                            personalData.setName(response.get("name").toString());
//                            name.setText(personalData.getName());
//                            nme[0] = true;
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Log.d(TAG,"Message recived, name set!");
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),"Error fetching data!",Toast.LENGTH_SHORT).show();
//                        Log.d(TAG,"Message not recived");
//                    }
//                });
//
//        ApiManager.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjectRequest);

        avatar = findViewById(R.id.avatar);
        ImageRequest imageRequest = new ImageRequest(imgURL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        Log.d(TAG,"Image recived");
                        avatar.setImageBitmap(bitmap);
                        avatar.buildDrawingCache();
                        Log.d(TAG,"Avatar is set");
                    }
                }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        avatar.setImageResource(R.drawable.no_image_user);
                    }
                });

        ApiManager.getInstance(this.getApplicationContext()).addToRequestQueue(imageRequest);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected void setButtonCollor(Button btn){
        if(!btn.isEnabled())
            btn.setBackgroundResource(R.drawable.button_gradient_disabled);
        else btn.setBackgroundResource(R.drawable.button_gradient);
    }


}
