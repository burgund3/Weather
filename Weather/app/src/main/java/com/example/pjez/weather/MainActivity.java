package com.example.pjez.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pjez.weather.api.WeatherApi;
import com.example.pjez.weather.common.Common;
import com.example.pjez.weather.download.DownloadUrlTask;


public class MainActivity extends AppCompatActivity {


    private EditText cityText;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityText = (EditText) findViewById(R.id.city_name);
        status = (TextView) findViewById(R.id.status);

    }

    protected void setStatus(String statusText) {

        status.setText(statusText);
    }

    public void clickAddCity(View view) {

        try {
            fetchCityData();
        } catch (Exception e) {
            setStatus(e.getMessage());
        }
    }

    protected String getCityName() throws Exception {

        Editable city = cityText.getText();

        if (city.length() == 0) {
            throw new Exception("Nazwa miasta nie może być pusta");
        }

        return city.toString();

    }

    protected void fetchCityData() throws Exception {

        if (!new Common(this).isNetworkConnected()) {
            throw new Exception("Brak połączenia z siecią");
        }

        WeatherApi api = new WeatherApi();
        api.setCityName(getCityName());
        String stringUrl = api.getFinalUrl();

        new DownloadCityTask().execute(stringUrl);
    }

    private class DownloadCityTask extends DownloadUrlTask {

        @Override
        protected void onPostExecute(String s) {
            setStatus(s);
        }
    }


}
