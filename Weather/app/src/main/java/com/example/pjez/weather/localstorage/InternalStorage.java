package com.example.pjez.weather.localstorage;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pjez on 03.09.16.
 */
public class InternalStorage {

    Context context;

    public InternalStorage(Context c) {
        context = c;
    }

    public void saveToFile(String file, String data) throws Exception {
        try {

            FileOutputStream fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();

        } catch (IOException e) {
            throw new Exception("Błąd zapisu danych");
        }
    }

    public String readFromFile(String file) throws Exception {
        try {

            FileInputStream fis = context.openFileInput(file);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            fis.close();
            return sb.toString();

        } catch (IOException e) {
            throw new Exception("Błąd odczytu danych");
        }
    }
}
