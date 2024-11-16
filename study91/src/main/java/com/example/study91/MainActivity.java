/*
 ВАЖНЫЕ ЗАМЕЧАНИЯ:
 1. Сразу реализовал финальный вариант MainActivity.java
 2. Пришлось (из-за ругани компилятора) заменить:
    getLastNonConfigurationInstance() на getLastCustomNonConfigurationInstance(),
    onRetainNonConfigurationInstance() на onRetainCustomNonConfigurationInstance
    А ВООБЩЕ советуют использовать ViewModel (из  androidx.......)
 */
package com.example.study91;

import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyTask mt;
    TextView tv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d("qwe", "create MainActivity: " + this.hashCode());

        tv = (TextView) findViewById(R.id.tv);

        mt = (MyTask) getLastCustomNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        // передаем в MyTask ссылку на текущее MainActivity
        mt.link(this);

        Log.d("qwe", "create MyTask: " + mt.hashCode());
    }

    public Object onRetainCustomNonConfigurationInstance() {
        // удаляем из MyTask ссылку на старое MainActivity
        mt.unLink();
        return mt;
    }


    static class MyTask extends AsyncTask<String, Integer, Void> {

        MainActivity activity;

        // получаем ссылку на MainActivity
        void link(MainActivity act) {
            activity = act;
        }

        // обнуляем ссылку
        void unLink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("qwe", "i = " + i + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + activity.hashCode());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.tv.setText("i = " + values[0]);
        }
    }
}