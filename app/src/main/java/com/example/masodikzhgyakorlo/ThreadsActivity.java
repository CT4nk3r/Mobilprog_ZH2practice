package com.example.masodikzhgyakorlo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ThreadsActivity extends AppCompatActivity {

    EditText editTextMaxNumber, editTextUserText;
    String sentence, sentence2;
    TextView textViewUserTextOutput;
    OutputStream output;
    ProgressBar progressBarNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);
    }

    public void characterByCharacter(View view){
        sentence = ""; sentence2 = "";
        sentence = editTextUserText.getText().toString();
        thread.start();
        try {
            output = openFileOutput("sentence.txt",MODE_APPEND);
            output.write((sentence+"\n").getBytes());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < sentence.length(); ++i){
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                Message message = new Message();
                message.arg1 = i;
            }
        }
    });

    public void series(View view){
        progressBarNumbers.setMax(Integer.parseInt(editTextMaxNumber.getText().toString()));
        thread.start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            sentence2 = sentence2 + sentence.charAt(message.arg1);
            textViewUserTextOutput.setText(sentence2);
        }
    };
}