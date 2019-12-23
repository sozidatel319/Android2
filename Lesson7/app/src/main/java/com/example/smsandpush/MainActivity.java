package com.example.smsandpush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView receive;
    EditText entersms;
    EditText phonenumber;
    Button sendsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        TextView textTitle = findViewById(R.id.textTitle);
        TextView textBody = findViewById(R.id.textBody);

        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            String title = intent.getStringExtra("title");
            String body = intent.getStringExtra("body");
            textTitle.setText(title);
            textBody.setText(body);
        }

        receive = findViewById(R.id.sms);
        entersms = findViewById(R.id.editText);
        phonenumber = findViewById(R.id.phonenumber);
        sendsms = findViewById(R.id.send);

        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    // Ask for permision
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
                } else {
                    if (!phonenumber.getText().toString().isEmpty() && !entersms.getText().toString().isEmpty()) {
                        SmsManager.getDefault()
                                .sendTextMessage(phonenumber.getText().toString(), null, entersms.getText().toString(), null, null);
                    }
                }
            }
        });

        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String answer = intent.getStringExtra("answer");
                receive.setText(answer);
            }
        };
// Создаем фильтр для BroadcastReceiver
        IntentFilter intFilt = new IntentFilter("com.example.smsandpush.SmsReceiver");
        registerReceiver(br, intFilt);
    }

}

