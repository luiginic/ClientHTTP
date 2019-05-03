package com.example.clienthttp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import personal.data.PersonalData;

public class AccountInformationScreen extends AppCompatActivity {

    private PersonalData persoanlData = new PersonalData();
    private TextView name;
    private TextView pacientID;
    private CircleImageView avatar;
    private TextView pacientCNP;
    private TextView phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information_screen);
        avatar = findViewById(R.id.avt);
        persoanlData = (PersonalData) getIntent().getSerializableExtra("personalInfo");
        name = findViewById(R.id.name2);
        name.setText(persoanlData.getName());
        pacientID = findViewById(R.id.pacientID);
        pacientID.setText("Pacient ID: " + persoanlData.getPacientCode());
        avatar.setImageBitmap((Bitmap)getIntent().getParcelableExtra("avatar"));
        pacientCNP = findViewById(R.id.pacientCNP);
        pacientCNP.setText("CNP: "+persoanlData.getCnp());
        phoneNo = findViewById(R.id.phoneNo);
        phoneNo.setText("Phone no.: "+persoanlData.getPhoneNumber());



    }
}
