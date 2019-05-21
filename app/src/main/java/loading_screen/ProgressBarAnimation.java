package loading_screen;

import android.content.Context;
import android.content.Intent;


import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;


import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.clienthttp.LoginActivity;
import com.example.clienthttp.MainActivity;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ApiManager.ApiManager;
import personal.data.Doctor;
import personal.data.PersonalData;

import static android.content.Context.MODE_PRIVATE;
import static java.security.AccessController.getContext;


public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;

    private float from;
    private float to;

    private PersonalData personalData;
    private Intent intent;
    private boolean persDat;

    private File accountInforamtions;

    public ProgressBarAnimation(Context context,ProgressBar progressBar,float from,float to,PersonalData personalID){
        this.context = context;
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
        persDat = false;
        setFields(personalID.getPacientCode());
        intent = new Intent(context,MainActivity.class);
        personalData.setPacientCode(personalID.getPacientCode());
        accountInforamtions = new File(context.getFilesDir(), "info.log");
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from)*interpolatedTime;
        progressBar.setProgress((int)value);
        if(!isNetworkAvailable()){
            checkAccountInfo();
        }else if(persDat){

            saveAccountInfo();
            context.startActivity(intent);
        }
    }

    private void saveAccountInfo(){
        //With share preferences
        SharedPreferences.Editor editor = context.getSharedPreferences("info.log", MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("name", personalData.getName());
        editor.putString("pacientId", personalData.getPacientCode());
        editor.putString("phoneNo",personalData.getPhoneNumber());
        editor.putString("doctorName",personalData.getDoctor().getName());
        editor.putString("doctorPhoneNo",personalData.getDoctor().getPhoneNumber());
        editor.putString("doctorAdd",personalData.getDoctor().getCabinetAddress());
        editor.apply();

        //Old version...
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(accountInforamtions);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//            ObjectOutputStream infoOut = new ObjectOutputStream(bufferedOutputStream);
//            infoOut.writeObject(personalData);
//            infoOut.close();
//            Log.d("LOADING","File saved in"+accountInforamtions.getAbsolutePath());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void setFields(String patientId){
        String jsonURL = "https://ro-medical-app.herokuapp.com/api/patients/get?id="+patientId;
//        String imgURL = "https://cdn.shopify.com/s/files/1/3026/6974/files/happy-alpacas-landscape_1024x1024.jpg?v=1532619630";

        personalData = new PersonalData();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, jsonURL, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            personalData.setName(response.getString("firstName")+" "+response.getString("lastName"));
                            personalData.setPhoneNumber(response.getString("tel"));
//                            intent.putExtra("account",personalData);
                            Log.d("LOADING","Name set!");
                            JSONObject doctor = response.getJSONObject("doctor");
                            Doctor doc = new Doctor();
                            doc.setName(doctor.getString("firstName")+" "+response.getString("lastName"));
                            doc.setPhoneNumber(doctor.getString("tel"));
                            doc.setCabinetAddress(doctor.getString("hospital"));
                            Log.d("LOADING","Doctor parsed");
                            personalData.setDoctor(doc);
                            Log.d("LOADING","Doctor set");
                            persDat = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Could not connect to the server!",Toast.LENGTH_LONG).show();
//                        intent.putExtra("account",personalData);
                        persDat = true;
                    }
                });

        ApiManager.getInstance(context).addToRequestQueue(jsonObjectRequest);

//        ImageRequest imageRequest = new ImageRequest(imgURL,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//
//                        avatar=bitmap;
//
//                    }
//                }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
//                new Response.ErrorListener() {
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//
//        ApiManager.getInstance(context).addToRequestQueue(imageRequest);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkAccountInfo(){
        //With share preferences
        SharedPreferences prefs = context.getSharedPreferences("info.log", MODE_PRIVATE);
        String restoredText = prefs.getString("pacientId", null);
        if (restoredText != null) {
            context.startActivity(intent);
        }
//        else {
//            Toast.makeText(context, "Connect to internet and login first!", Toast.LENGTH_LONG).show();
//            Intent intentLogin = new Intent(context, LoginActivity.class);
//            context.startActivity(intentLogin);
//        }

        //Old version...
//        try {
//            accountInforamtions = new File(this.getApplicationContext().getFilesDir(), "info.log");
//            Log.d("LOGIN","Searching for file in "+ accountInforamtions.getAbsolutePath());
//            if(accountInforamtions.exists()){
//                FileInputStream fileInputStream = new FileInputStream(accountInforamtions);
//                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
//                personalData = (PersonalData) objectInputStream.readObject();
//                objectInputStream.close();
//                intent.putExtra("account",personalData);
//                this.startActivity(intent);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }


}
