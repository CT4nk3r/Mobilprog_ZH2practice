package com.example.masodikzhgyakorlo;

import static android.telephony.TelephonyManager.CALL_STATE_RINGING;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhoneActivity extends AppCompatActivity {

    TextView textConnection;
    TelephonyManager telephonyManager;
    LinearLayout verticalLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        textConnection = findViewById(R.id.textConnection);
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        verticalLayout = findViewById(R.id.verticalLayout);

        if (telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED){
            textConnection.setText("CONNECTED");
        }else if (telephonyManager.getDataState() == TelephonyManager.DATA_DISCONNECTED){
            textConnection.setText("DISCONNECTED");
        }

        telephonyManager.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                if (state == CALL_STATE_RINGING){
                    verticalLayout.setBackgroundResource(R.drawable.ic_phone);
                }else{
                    verticalLayout.setBackground(null);
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

        Button btnHome = findViewById(R.id.btnHome);
        Button btnLight = findViewById(R.id.btnLight);
        Button btnPhone = findViewById(R.id.btnPhone);
        Button btnThreads = findViewById(R.id.btnThreads);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneActivity.this, LightActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneActivity.this, PhoneActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnThreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneActivity.this, ThreadsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}