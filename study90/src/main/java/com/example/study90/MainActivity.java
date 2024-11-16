/*
    Важный комментарий:
    При третьем варианте startTask() статус оказывается FINISHED
    (а не RUNNING, как в уроке, НО ВСЕ РАВНО НЕВЕРНО!)
 */
package com.example.study90;

import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyTask mt;
    TextView tvInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        if(v.getId() == R.id.btnStart)
            startTask();
        else if(v.getId() == R.id.btnStatus)
            showStatus();
    }

    /* 1-ый вариант
    private void startTask() {
        mt = new MyTask();
    }
     */

    /* 2-ый вариант
    private void startTask() {
      mt = new MyTask();
      mt.execute();
    }
     */

//    /* 3-ый вариант
    private void startTask() {
      mt = new MyTask();
      mt.execute();
      mt.cancel(false);
    }
//     */

    /* НЕ уточненный вариант
    private void showStatus() {
        if (mt != null)
            Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();
    }
     */
//    /* УТОЧНЕННЫЙ вариант
    private void showStatus() {
        if (mt != null)
            if (mt.isCancelled())
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();
    }
//     */


    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    if (isCancelled()) return null;
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvInfo.setText("Cancel");
        }
    }
}