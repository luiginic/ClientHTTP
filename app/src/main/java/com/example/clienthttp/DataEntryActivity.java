package com.example.clienthttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import data_collection.GenericMessage;
import data_collection.PacientDailyInfo;
import data_collection.PacientDataEntryClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataEntryActivity extends AppCompatActivity implements SensorEventListener {

    public static final String TAG = "DataEntryActivity";

    private TextView numberOfGlassesTV;
    private TextView glassesText;
    private Button minusButton;
    private SeekBar seekBarWeight;
    private TextView weightInKgTv;
    private TextView pulseBPM;
    private SeekBar seekBarPulse;
    private TextView tempInCelsius;
    private SeekBar seekBarTemp;
    private TextView stepsText;
    private SensorManager sensorManager;
    private Sensor stepCounter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        numberOfGlassesTV = findViewById(R.id.number_of_glasses);
        glassesText = findViewById(R.id.glasses_text);

        seekBarWeight = findViewById(R.id.seekBarWeight);
        weightInKgTv = findViewById(R.id.weightInKg);

        seekBarPulse =findViewById(R.id.seekBarPulse);
        pulseBPM = findViewById(R.id.pulseBPM);

        tempInCelsius = findViewById(R.id.tempInCelsius);
        seekBarTemp = findViewById(R.id.seekBarTemp);

        stepsText = findViewById(R.id.number_of_stepsTV);

        seekBarWeight.setMax(200);
        seekBarTemp.setMax(20);

        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                weightInKgTv.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarPulse.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pulseBPM.setText("" + (50 + i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tempInCelsius.setText("" + (28 + i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        } else {
            Toast.makeText(this, "This device does not have a step counter sensor", Toast.LENGTH_LONG).show();
        }


    }


    private void makeToolbarWhite() {
        ActionBar topBar = getSupportActionBar();
        topBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        topBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Tell us what you do" + "</font>"));
    }

    //This method gets called whenever plus or minus buttons
    // in the 'Water' cardview are pressed
    public void changeNumberOfGlasses(View view) {
        int viewId = view.getId();
        int nrOfGlasses = Integer.parseInt(numberOfGlassesTV.getText().toString());

        if (viewId == R.id.minus_glass_button && nrOfGlasses > 0){
            //decrement number of glasses
            numberOfGlassesTV.setText("" + (--nrOfGlasses));
        } else if (viewId == R.id.plus_glass_button) {
            //imcrement number of glasses
            numberOfGlassesTV.setText("" + (++nrOfGlasses));
        }

        if (nrOfGlasses == 1) glassesText.setText("glass");
        else glassesText.setText("glasses");


    }


    //This method gets called whenever user presses send button
    public void sendDailyData(View view) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://medicad.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PacientDataEntryClient client = retrofit.create(PacientDataEntryClient.class);
        PacientDailyInfo info = getAllPacientDataInfo();

        Call<GenericMessage> call = client.sendDailyData(info);

        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(Call<GenericMessage> call, Response<GenericMessage> response) {
                Toast.makeText(DataEntryActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GenericMessage> call, Throwable t) {

            }
        });

    }

    private PacientDailyInfo getAllPacientDataInfo() {
        Integer water       = Integer.parseInt(numberOfGlassesTV.getText().toString());
        Integer weight      = Integer.parseInt(weightInKgTv.getText().toString());
        Integer pulse       = Integer.parseInt(pulseBPM.getText().toString());
        Integer temperature = Integer.parseInt(tempInCelsius.getText().toString());
        Integer steps       = Integer.parseInt(stepsText.getText().toString());
        Integer pacient_id  = Integer.parseInt(LoginActivity.DUMMY_CREDENTIALS[0]);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String today = df.format(Calendar.getInstance().getTime());

        Log.d(TAG, "getAllPacientDataInfo: "+ today);

        return new PacientDailyInfo(water, weight, pulse, temperature, steps, today,pacient_id);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "onSensorChanged: BEAT!");
        int stepsCount = (int)sensorEvent.values[0];
        stepsText.setText(stepsCount + "");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
