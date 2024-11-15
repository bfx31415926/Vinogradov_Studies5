/*
    toDo Не забыть прочитать (и проделать) код про фикс темы Handler: http://forum.startandroid.ru/viewtopic.php?f=30&t=1870.)
 */
package com.example.study80;

import java.util.concurrent.TimeUnit;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Handler h;
    TextView tvInfo;
    Button btnStart;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnStart = (Button) findViewById(R.id.btnStart);
        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                tvInfo.setText("Закачано файлов: " + msg.what);
                if (msg.what == 10) btnStart.setEnabled(true);
            };
        };
    }
    public void onclick(View v) {
        if(v.getId() == R.id.btnStart) {
            btnStart.setEnabled(false);
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 1; i <= 10; i++) {
                        // долгий процесс
                        downloadFile();
                        h.sendEmptyMessage(i);
                        // пишем лог
                        Log.d(LOG_TAG, "i = " + i);
                    }
                }
            });
            t.start();
        }else if(v.getId() == R.id.btnTest)
            Log.d(LOG_TAG, "test");
    }

    void downloadFile() {
        // пауза - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}