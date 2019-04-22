package loading_screen;

import android.content.Context;
import android.content.Intent;


import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;


import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.clienthttp.MainActivity;


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
import java.io.ObjectOutputStream;

import ApiManager.ApiManager;
import personal.data.PersonalData;


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
        setFields();
        intent = new Intent(context,MainActivity.class);
        personalData.setPacientCode(personalID.getPacientCode());
        accountInforamtions = new File(context.getFilesDir(), "info.log");
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from)*interpolatedTime;
        progressBar.setProgress((int)value);
        if(persDat){

            saveAccountInfo();
            context.startActivity(intent);
        }
    }

    private void saveAccountInfo(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(accountInforamtions);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream infoOut = new ObjectOutputStream(bufferedOutputStream);
            infoOut.writeObject(personalData);
            infoOut.close();
            Log.d("LOADING","File saved in"+accountInforamtions.getAbsolutePath());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFields(){
        String jsonURL = "https://api.myjson.com/bins/arg9u";
//        String imgURL = "https://cdn.shopify.com/s/files/1/3026/6974/files/happy-alpacas-landscape_1024x1024.jpg?v=1532619630";

        personalData = new PersonalData();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, jsonURL, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            personalData.setName(response.get("name").toString());
                            persDat = true;
                            intent.putExtra("account",personalData);
                            Log.d("LOADING","Name set!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Error fetching data!",Toast.LENGTH_SHORT).show();
                        intent.putExtra("account",personalData);
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


}
