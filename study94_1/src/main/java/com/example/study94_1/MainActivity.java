//toDo Куча непонятных вопросов с реализацией Service
package com.example.study94_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickStart(View v) {
        Intent intent = new Intent();
        intent.setPackage("com.example.study94_2");
        intent.setAction("com.example.study94_2.MyService");
        startForegroundService(intent.putExtra("name", "value"));
//        startService(new Intent("com.example.study94_2.MyService").putExtra("name", "value"));
    }
}