package com.example.pjez.weather.download;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by pjez on 03.09.16.
 */
public class DownloadUrlTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        try {

            return new DownloadUrl().get(urls[0]);

        } catch (IOException e) {
            return "Serwer jest niedostępny.";
        }

    }

}
