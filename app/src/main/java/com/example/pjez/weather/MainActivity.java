package com.example.pjez.weather;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pjez.weather.Service.ApiWeatherService;
import com.example.pjez.weather.Service.Exception.EmptyNameException;
import com.example.pjez.weather.api.Weather.Object.ApiWeatherCity;
import com.example.pjez.weather.provider.Entity.City;
import com.example.pjez.weather.provider.Sql.CitiesProvider;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    private EditText cityText;
    private TextView status;
    protected Spinner citiesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        citiesList = (Spinner) findViewById(R.id.cities_list);
        cityText = (EditText) findViewById(R.id.city_name);
        status = (TextView) findViewById(R.id.status);
    }


    // ustawianie statusu
    private void setStatus(String statusText) {
        status.setText(statusText);
    }


    // wyczysc nazwe miasta
    private void clearCityText() {
        cityText.setText("");
    }


    // dodawanie miasta
    public void clickAddCity(View view) {

        String name = cityText.getText().toString();

        new ApiWeatherService(this) {
            @Override
            protected void onSuccess(Object result) {
                super.onSuccess(result);
                City city = (City) result;
                setStatus("Dodano miasto: " + city.getName());
                clearCityText();
            }

            @Override
            protected void onFailed(Object result) {
                super.onFailed(result);
                setStatus("Nazwa miasta nie może być pusta.");
                clearCityText();
            }
        }
                .addCity(name);

    }


}
