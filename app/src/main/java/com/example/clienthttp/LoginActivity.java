package com.example.clienthttp;


import android.content.Context;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import personal.data.PersonalData;

import static android.view.View.FOCUSABLE;


public class LoginActivity extends AppCompatActivity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "0123456789"
    };

    // UI references.
    private TextInputEditText pacientID;

    public static Context ctx;
    private PersonalData personalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        ctx = this.getApplicationContext();

        pacientID = (TextInputEditText) findViewById(R.id.pacient_id);
        pacientID.setHint(R.string.pacient_id);


        personalData = new PersonalData();

        Button continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    private void attemptLogin(){
        String code = pacientID.getText().toString();
        if(this.validateCode(code)) {
            personalData.setPacientCode(code);
            for (String it : DUMMY_CREDENTIALS) {
                if (it.equals(personalData.getPacientCode()))
                    this.startActivity(new Intent(this, loading_screen.class));
                else pacientID.setError("The provided ID is incorect!");
            }
        } else {
            pacientID.setError("The provided ID is invalid!");
            pacientID.requestFocus();
        }

    }

    private boolean validateCode(String code){
        if(code.length()==10)
            return true;
        return false;
    }

}