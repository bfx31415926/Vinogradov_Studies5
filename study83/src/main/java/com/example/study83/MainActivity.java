package com.example.study83;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Handler h;

    Handler.Callback hc = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Log.d(LOG_TAG, "what = " + msg.what);
            return false;
        }
    };

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        h = new Handler(hc);
        sendMessages();
    }

   /* 1-ый вариант
    void sendMessages() {
        Log.d(LOG_TAG, "send messages");
        h.sendEmptyMessageDelayed(1, 1000);
        h.sendEmptyMessageDelayed(2, 2000);
        h.sendEmptyMessageDelayed(3, 3000);
    }
  */
   /* 2-ый вариант
    void sendMessages() {
      h.sendEmptyMessageDelayed(1, 1000);
      h.sendEmptyMessageDelayed(2, 2000);
      h.sendEmptyMessageDelayed(3, 3000);
      h.removeMessages(2);
    }
  */
//   /* 3-ый вариант
    void sendMessages() {
      Log.d(LOG_TAG, "send messages");
      h.sendEmptyMessageDelayed(1, 1000);
      h.sendEmptyMessageDelayed(2, 2000);
      h.sendEmptyMessageDelayed(3, 3000);
      h.sendEmptyMessageDelayed(2, 4000);
      h.sendEmptyMessageDelayed(5, 5000);
      h.sendEmptyMessageDelayed(2, 6000);
      h.sendEmptyMessageDelayed(7, 7000);
      h.removeMessages(2);
    }
//   */
}
