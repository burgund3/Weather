package com.example.pjez.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pjez.weather.api.WeatherApi;
import com.example.pjez.weather.api.WeatherApiObject_City;
import com.example.pjez.weather.api.WeatherDataParser;
import com.example.pjez.weather.common.Common;
import com.example.pjez.weather.download.DownloadUrlTask;
import com.example.pjez.weather.localstorage.CitiesProvider;

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

    public void back(View view){
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

        WeatherApi api = new WeatherApi();
        api.setCityName(getCityName());
        String stringUrl = api.getFinalUrl();

        new DownloadCityTask().execute(stringUrl);
    }


    protected void afterFetch(String s) {

        try {

            Log.d(TAG, s);


            WeatherDataParser parser = new WeatherDataParser(s);
            WeatherApiObject_City weather = parser.getObject();
            String city = weather.name;

            processCityName(city);

            finish();

        } catch (Exception e) {
            setStatus(e.getMessage());
        }


    }

    protected void processCityName(String s) throws Exception {

        if (!cityName.equals(s))
            throw new Exception("Nie znaleziono takiego miasta");

        CitiesProvider cities = new CitiesProvider(this.getApplicationContext());
        cities.addCity(cityName);

    }


    private class DownloadCityTask extends DownloadUrlTask {

        @Override
        protected void onPostExecute(String s) {
            afterFetch(s);
        }
    }


}
