package com.example.pjez.weather;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pjez.weather.api.Weather.JSONParser;
import com.example.pjez.weather.api.Weather.Object.ApiWeatherCity;
import com.example.pjez.weather.api.Weather.UrlBuilder;
import com.example.pjez.weather.common.Common;
import com.example.pjez.weather.download.DownloadUrlTask;
import com.example.pjez.weather.provider.Entity.City;
import com.example.pjez.weather.provider.Sql.CitiesProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAct";

    private EditText cityText;
    private TextView status;
    protected Spinner citiesList;

    protected String cityName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citiesList = (Spinner) findViewById(R.id.cities_list);

        cityText = (EditText) findViewById(R.id.city_name);
        status = (TextView) findViewById(R.id.status);

        fillCitiesList();

    }


    // wczytanie listy miast
    protected void fillCitiesList() {

        try {

            CitiesProvider citiesProvider = new CitiesProvider(getApplicationContext());
            List<City> cities = citiesProvider.getAll();

            ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            citiesList.setAdapter(spinAdapter);
            citiesList.setPrompt("Wybierz miasto");


        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }


    }


    // ustawianie statusu
    protected void setStatus(String statusText) {

        status.setText(statusText);
    }


    // dodawanie miasta
    public void addCity(View view) {

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

    private class DownloadCityTask extends DownloadUrlTask {

        @Override
        protected void onPostExecute(String s) {
            afterFetch(s);
        }
    }


    protected void afterFetch(String json) {

        try {

            ApiWeatherCity cityObject = new JSONParser(json).getCityWeather();
            processCityName(cityObject.name);

            setStatus(cityObject.toString());
            cityText.setText("");

            fillCitiesList();

        } catch (Exception e) {
            setStatus(e.getLocalizedMessage());
        }


    }

    protected void processCityName(String name) throws Exception {

        if (!cityName.toLowerCase().equals(name.toLowerCase()))
            throw new Resources.NotFoundException("Nie znaleziono takiego miasta");

        City city = new City(name);

        CitiesProvider cities = new CitiesProvider(this.getApplicationContext());
        cities.add(city);

    }


    // usuwanie miasta
    public void removeCity(View view) {

        try {
            Object item = citiesList.getSelectedItem();

            if (item != null) {

                City city = new City(item.toString());
                CitiesProvider citiesProvider = new CitiesProvider(getApplicationContext());

                citiesProvider.delete(city);
                fillCitiesList();
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

    }


}
