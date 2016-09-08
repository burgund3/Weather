package com.example.pjez.weather.Service;

import android.content.Context;

import com.example.pjez.weather.Service.Exception.EmptyNameException;
import com.example.pjez.weather.api.Weather.ApiWeather;
import com.example.pjez.weather.api.Weather.Object.ApiWeatherCity;
import com.example.pjez.weather.provider.Entity.City;
import com.example.pjez.weather.provider.Sql.CitiesProvider;

import java.security.Provider;

/**
 * Created by pjez on 08.09.16.
 */
public class ApiWeatherService {

    protected Context context;
    protected CitiesProvider provider;
    protected ApiWeather api;

    public ApiWeatherService(Context appContext) {
        context = appContext;
        provider = new CitiesProvider(getContext());
        api = new ApiWeather();
    }

    private Context getContext() {
        return context;
    }


    public void addCity(String name) {

        if (name.length() == 0) {
            onFailed(null);
            return;
        }

        City city = new City(name);

        onSuccess(city);
    }

    protected void onSuccess(Object result) {
        onComplete(result);
    }

    protected void onFailed(Object result) {
        onComplete(result);
    }

    protected void onComplete(Object result) {
    }
}
