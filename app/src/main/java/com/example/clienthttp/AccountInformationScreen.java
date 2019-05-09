package com.example.clienthttp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import personal.data.PersonalData;

public class AccountInformationScreen extends AppCompatActivity {

    private PersonalData personalData = new PersonalData();
    private TextView name;
    private TextView pacientID;
    private CircleImageView avatar;
    private TextView pacientCNP;
    private TextView phoneNo;
    private TextView birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information_screen);
        avatar = findViewById(R.id.avt);
        retrieveAccountDetails();
//        persoanlData = (PersonalData) getIntent().getSerializableExtra("personalInfo");
        name = findViewById(R.id.name2);
        name.setText(personalData.getName());
        pacientID = findViewById(R.id.pacientID);
        pacientID.setText(personalData.getPacientCode());
        avatar.setImageBitmap((Bitmap)getIntent().getParcelableExtra("avatar"));
        pacientCNP = findViewById(R.id.pacientCNP);
        pacientCNP.setText(personalData.getCnp());
        phoneNo = findViewById(R.id.phoneNo);
        phoneNo.setText(personalData.getPhoneNumber());
        birthDate = findViewById(R.id.birthDate);
        birthDate.setText(personalData.getDateOfBirth()+"\n"+"Age: "+personalData.getAge(personalData.getDateOfBirth()));




    }
    private void retrieveAccountDetails(){
        SharedPreferences prefs = getSharedPreferences("info.log", MODE_PRIVATE);
        String restoredText = prefs.getString("pacientId", null);
        if (restoredText != null) {
            personalData.setPacientCode(prefs.getString("pacientId","")); //null is the default value.
            personalData.setName(prefs.getString("name", getResources().getString(R.string.name_goes_here)));//"getResources().getString(R.string.name_goes_here" = "Name goes here" and it  is the default value.
            personalData.setPhoneNumber(prefs.getString("phoneNo",getResources().getString(R.string.phoneNo_default)));
            personalData.setCnp(prefs.getString("pacientCnp",getResources().getString(R.string.CNP_default)));
            //TODO change the way birthday is stored

        }
    }
}
