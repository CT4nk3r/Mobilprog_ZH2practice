package com.example.masodikzhgyakorlo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LightActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        imageView = findViewById(R.id.imageViewLight);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Button btnHome = findViewById(R.id.btnHome);
        Button btnLight = findViewById(R.id.btnLight);
        Button btnPhone = findViewById(R.id.btnPhone);
        Button btnThreads = findViewById(R.id.btnThreads);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LightActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LightActivity.this, LightActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LightActivity.this, PhoneActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnThreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LightActivity.this, ThreadsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageView.setEnabled(false);

        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        imageView.setEnabled(true);
        sensorManager.unregisterListener(this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            if(sensorEvent.values[0] < 10000){
                imageView.setImageDrawable(getDrawable(R.drawable.ic_bulb_on));
            }
            else {
                imageView.setImageDrawable(getDrawable(R.drawable.ic_bulb_off));
            }
            Toast.makeText(this, "Light: "+sensorEvent.values[0]+" lux", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}