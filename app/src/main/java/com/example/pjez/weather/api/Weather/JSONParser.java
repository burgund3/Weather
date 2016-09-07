package com.example.pjez.weather.api.Weather;


import android.util.Log;

import com.example.pjez.weather.api.Weather.Object.ApiWeatherCity;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;


/**
 * Created by pjez on 03.09.16.
 */
public class JSONParser {

    private static final String TAG = "Parser";

    protected String json = null;

    public JSONParser(String in) {

        json = in;

    }

    public ApiWeatherCity getCityWeather() throws Exception {

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);

        ApiWeatherCity city;

        try {
            city = gson.fromJson(reader, ApiWeatherCity.class);
        } catch (Exception e) {
            throw new Exception("Nie znaleziono takiego miasta.");
        }

        return city;
    }


}
