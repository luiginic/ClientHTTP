package com.example.clienthttp;


import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.Toast;


import java.io.File;

import personal.data.PersonalData;



public class LoginActivity extends AppCompatActivity {



    // UI references.
    private TextInputEditText pacientID;

    public static Context ctx;
    private PersonalData personalData;
    private Intent intent;
    private Button continueBtn;
    private Button makeAnAppointmentBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Set up the login form.

        ctx = this.getApplicationContext();
        intent = new Intent(this, LoadingScreen.class);

        checkAccountInfo();

        pacientID = (TextInputEditText) findViewById(R.id.pacient_id);
        pacientID.setHint(R.string.pacient_id);


        personalData = new PersonalData();

        continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        makeAnAppointmentBtn = findViewById(R.id.makeAnAppointment);
        makeAnAppointmentBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Unavailable at this moment!",Toast.LENGTH_LONG).show();
                makeAnAppointmentBtn.setEnabled(false);
                setButtonCollor(makeAnAppointmentBtn);
            }
        });

    }

    private void checkAccountInfo(){
        //With share preferences
        SharedPreferences prefs = getSharedPreferences("info.log", MODE_PRIVATE);
        String restoredText = prefs.getString("pacientId", null);
        if (restoredText != null) {
            this.startActivity(intent);
        }

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

    private void attemptLogin(){
        String code = pacientID.getText().toString();
        if(this.validateCode(code)) {
            personalData.setPacientCode(code);
            SharedPreferences.Editor editor = this.getSharedPreferences("info.log", MODE_PRIVATE).edit();
            editor.clear();
            editor.putString("pacientId",personalData.getPacientCode());
            editor.apply();
            this.startActivity(intent);
        } else {
            pacientID.setError("The provided ID is invalid!");
            pacientID.requestFocus();
        }

    }

    private boolean validateCode(String code){
        return code.length() >=1;
    }
    protected void setButtonCollor(Button btn){
        if(!btn.isEnabled())
            btn.setBackgroundResource(R.drawable.button_gradient_disabled);
        else btn.setBackgroundResource(R.drawable.button_gradient);
    }

}
