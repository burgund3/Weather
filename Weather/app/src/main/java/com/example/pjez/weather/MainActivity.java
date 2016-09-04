package com.example.pjez.weather;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pjez.weather.provider.CitiesProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAct";

    protected Spinner citiesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citiesList = (Spinner) findViewById(R.id.cities_list);

        fillCitiesList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        fillCitiesList();


    }

    protected void fillCitiesList() {

        try {

            CitiesProvider citiesProvider = new CitiesProvider(getApplicationContext());
            ArrayList cities = citiesProvider.getCities();

            ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            citiesList.setAdapter(spinAdapter);


        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }


    }


    public void addCity(View view) {

        Intent intent = new Intent(this, AddCityActivity.class);
        startActivity(intent);

    }

    public void removeCity(View view) {


        try {
            Object item = citiesList.getSelectedItem();

            if (item != null) {

                String city = item.toString();
                CitiesProvider citiesProvider = new CitiesProvider(getApplicationContext());

                citiesProvider.removeCity(city);
                fillCitiesList();
            }

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }


    }
}
