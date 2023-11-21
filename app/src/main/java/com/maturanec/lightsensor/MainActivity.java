package com.maturanec.lightsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textview;
    private SensorManager sensorManager;
    Sensor ourSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INICIJALIZACIJA i registracija
        textview = findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ourSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(ourSensor == null)
        {
            Toast.makeText(this, "No proximity sensor found on this device", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            sensorManager.registerListener(this, ourSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }//onCreate

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//OVDJE IDE STA SE DESAVA
        if(sensorEvent.sensor.getType()== Sensor.TYPE_PROXIMITY)
        {
            textview.setText("Distance to object: " + sensorEvent.values[0]);
        }

    }//onSensorChanged

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }//onAccuracyChanged

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}