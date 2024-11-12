/*
ВАЖНЫЙ Комментарий: В DatePicker месяц отсчитывается [0 .. 11]
 */
package com.example.study59;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int DIALOG_DATE = 1;
    int myYear = 2011;
    int myMonth = 02; //месяц считается с нуля (соответствует марту)
    int myDay = 03;
    TextView tvDate;

    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tvDate = (TextView) findViewById(R.id.tvDate);
    }

    public void onclick(View view) {
        showDialog(DIALOG_DATE);
    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    OnDateSetListener myCallBack = new OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            tvDate.setText("Today is " + myDay + "/" + (myMonth + 1) + "/" + myYear);
        }
    };
}