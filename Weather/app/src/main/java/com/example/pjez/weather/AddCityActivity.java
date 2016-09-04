package com.example.pjez.weather;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pjez.weather.api.Weather.Object.ApiWeatherCity;
import com.example.pjez.weather.api.Weather.UrlBuilder;
import com.example.pjez.weather.api.Weather.JSONParser;
import com.example.pjez.weather.common.Common;
import com.example.pjez.weather.download.DownloadUrlTask;
import com.example.pjez.weather.provider.CitiesProvider;

public class AddCityActivity extends AppCompatActivity {

    private static final String TAG = "AddCity";


    private EditText cityText;
    private TextView status;

    protected String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        cityText = (EditText) findViewById(R.id.city_name);
        status = (TextView) findViewById(R.id.status);

    }

    public void back(View view) {
        finish();
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

        return cityName = city.toString();

    }

    protected void fetchCityData() throws Exception {

        if (!new Common(this).isNetworkConnected()) {
            throw new Exception("Brak połączenia z siecią");
        }

        UrlBuilder api = new UrlBuilder();
        api.setCityName(getCityName());
        String stringUrl = api.getFinalUrl();

        new DownloadCityTask().execute(stringUrl);
    }


    protected void afterFetch(String json) {

        try {

            ApiWeatherCity cityObject = new JSONParser(json).getCityWeather();
            processCityName(cityObject.name);
            finish();

        } catch (Exception e) {
            setStatus(e.getLocalizedMessage());
        }


    }

    protected void processCityName(String s) throws Exception {

        if (!cityName.toLowerCase().equals(s.toLowerCase()))
            throw new Resources.NotFoundException("Nie znaleziono takiego miasta");

        CitiesProvider cities = new CitiesProvider(this.getApplicationContext());
        cities.addCity(s);

    }


    private class DownloadCityTask extends DownloadUrlTask {

        @Override
        protected void onPostExecute(String s) {
            afterFetch(s);
        }
    }


}
