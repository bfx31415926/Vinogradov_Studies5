package com.example.study89;

import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    MyTask mt;
    TextView tvInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        if(v.getId() == R.id.btnStart) {
            mt = new MyTask();
            mt.execute();
        } else if(v.getId() == R.id.btnCancel) {
                cancelTask();
        }
    }

    /* 1-ый и 2-й варианты
    private void cancelTask() {
        if (mt == null) return;
        Log.d(LOG_TAG, "cancel result: " + mt.cancel(false));
    }
     */

//    /* 3-ый вариант
    private void cancelTask() {
        if (mt == null) return;
        Log.d(LOG_TAG, "cancel result: " + mt.cancel(true));
    }
//     */

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LOG_TAG, "Begin");
        }

        @Override
        /* 1-ый вариант
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                }
            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "Interrupted");
                e.printStackTrace();
            }
            return null;
        }
         */

        /* 2-ый вариант
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if (isCancelled()) return null;
                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                }
            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "Interrupted");
                e.printStackTrace();
            }
            return null;
        }
                 */

        //        /* 3-ый вариант
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
//                    if (isCancelled()) return null;
                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                }
            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "Interrupted");
                e.printStackTrace();
            }
            return null;
        }
        //         */

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
            Log.d(LOG_TAG, "End");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvInfo.setText("Cancel");
            Log.d(LOG_TAG, "Cancel");

        }
    }
}