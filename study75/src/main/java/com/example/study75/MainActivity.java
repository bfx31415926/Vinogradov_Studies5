/*
    Остался открытым вопрос:
        как писать файлы во внутреннюю память телефона,
        но не в /data/......
        РАЗОБРАЛСЯ:
        Включил соотв. код в writeFile()
 */

package com.example.study75;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    final String FILENAME = "file";

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onclick(View v) {
        if(v.getId() == R.id.btnWrite)
            writeFile();
        else if(v.getId() == R.id.btnRead)
            readFile();
        else if(v.getId() == R.id.btnWriteSD)
            writeFileSD();
        else if(v.getId() == R.id.btnReadSD)
            readFileSD();
    }

    void writeFile() {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write("Содержимое файла");
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
//            Код для создания поддиректории [MyNewDirectory] директории [Downloads],
//            и создания в [MyNewDirectory] файла "MyNewFile.txt"
//            и записью в него строки "Привет!"

        File dirDownLoads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Log.d(LOG_TAG, "dirDownLoads.getAbsolutePath() = " + dirDownLoads.getAbsolutePath());
        File dir = new File(dirDownLoads, "MyNewDirectory");
        if (!dir.exists()) {
            dir.mkdirs();
            Log.d(LOG_TAG, "MyNewDirectory is created!");
        }
        Log.d(LOG_TAG, "dir.getAbsolutePath() = " + dir.getAbsolutePath());
        File file = new File(dir + File.separator + "MyNewFile.txt");
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);   // После чего создаем поток для записи
            outputStream.write("Привет!".getBytes());                            // и производим непосредственно запись
            outputStream.close();
        } catch (IOException e) {
            Log.d(LOG_TAG, "[IOException] Problems with file!");
            e.printStackTrace();
        } finally{
            Log.d(LOG_TAG, "file.getAbsolutePath() = " + file.getAbsolutePath());
        }
         */
    }

    void readFile() {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFileSD() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            // пишем данные
            bw.write("Содержимое файла на SD");
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "IOException ERROR!");
        }
    }

    void readFileSD() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "FileNotFoundException ERROR!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "IOException ERROR!");
        }
    }
}