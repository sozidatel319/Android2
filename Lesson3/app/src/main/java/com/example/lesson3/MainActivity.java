package com.example.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView textTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTimer = findViewById(R.id.timer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Task task = new Task();
        task.execute(10);
    }


    class Task extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textTimer.setText("Подготовка к старту ракеты!");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int par = params[0];
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            for (int j = 0; par > j; par--) {
              publishProgress(par);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textTimer.setText("Осталось " + values[0] + " секунд");
        }

        @Override
        protected void onPostExecute(Void s) {
            textTimer.setText("Пуск!");
        }
    }
}