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

    protected String cityName;

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

        getCityName();

        if (new Common(this).isNetworkConnected()) {

            fetchCityData();

        } else {

            setStatus("Brak połączenia z siecią");
        }
    }

    protected void getCityName() {

        Editable city = cityText.getText();

        if (city.length() == 0) {
            cityName = null;
            setStatus("Podaj nazwę miasta");
        } else {
            cityName = city.toString();

        }

    }

    protected void fetchCityData() {

        if (cityName != null) {

            WeatherApi api = new WeatherApi();
            api.setCityName(cityName);
            String stringUrl = api.getFinalUrl();

            new DownloadCityTask().execute(stringUrl);
        }
    }

    private class DownloadCityTask extends DownloadUrlTask {

        @Override
        protected void onPostExecute(String s) {
            setStatus(s);
        }
    }


}
