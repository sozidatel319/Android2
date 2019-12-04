package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView temp;
    private TextView humidity;
    private Sensor sensorTemp;
    private Sensor sensorHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = findViewById(R.id.temp);
        humidity = findViewById(R.id.humidity);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    SensorEventListener listenerTemp = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            temp.setText(new StringBuilder().append("Температура").append(" ").append(event.values[0]));
        }
    };

    SensorEventListener listenerHumidity = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            humidity.setText(new StringBuilder().append("Влажность").append(" ").append(event.values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    protected void onResume() {
        if (listenerTemp == null) {
            sensorManager.registerListener(listenerTemp, sensorTemp, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (listenerHumidity == null) {
            sensorManager.registerListener(listenerHumidity, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (listenerTemp != null) {
            sensorManager.unregisterListener(listenerTemp, sensorTemp);
            listenerTemp = null;
        }
        if (listenerHumidity != null) {
            sensorManager.unregisterListener(listenerHumidity, sensorHumidity);
            listenerHumidity = null;
        }
    }
}
