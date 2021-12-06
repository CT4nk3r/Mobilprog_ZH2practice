package com.example.masodikzhgyakorlo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    List<Sensor> sensorList;

    ListView listView;

    TextView textGyro, textAccel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textGyro = findViewById(R.id.textGyro);
        textAccel = findViewById(R.id.textAccel);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> stringArrayList = new ArrayList<>();

        for (Sensor item:
             sensorList) {
            if (item.getVersion() >= 2) {
                stringArrayList.add(item.toString());
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(arrayAdapter);

        Button btnHome = findViewById(R.id.btnHome);
        Button btnLight = findViewById(R.id.btnLight);
        Button btnPhone = findViewById(R.id.btnPhone);
        Button btnThreads = findViewById(R.id.btnThreads);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LightActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnThreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThreadsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Toast.makeText(this, "Coords " + "X: " + motionEvent.getX() + " Y: " + motionEvent.getY(), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        home.setEnabled(false);

        Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        home.setEnabled(true);

        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textGyro.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            textGyro.setText("Gyro: \n" + sensorEvent.values[0] + "\n" + sensorEvent.values[0] + "\n" + sensorEvent.values[0]);
                        }
                    });
                }
            }).start();
        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textAccel.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            textAccel.setText("Accel: \n" + sensorEvent.values[0] + "\n" + sensorEvent.values[1] + "\n" + sensorEvent.values[2]);
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}