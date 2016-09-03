package com.example.pjez.weather.api;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pjez on 03.09.16.
 */
public class WeatherDataParser {

    private static final String TAG = "Parser";

    protected JSONObject reader = null;
    protected String json = null;

    protected WeatherApiObject_City city;

    public WeatherDataParser(String in) throws JSONException {

        json = in;
        reader = new JSONObject(json);

    }

    public String getCity() throws JSONException {

        return reader.getString("name");

    }

    public WeatherApiObject_City getObject() throws JSONException {

        parseAll();
        return city;
    }

    public void parseAll() throws JSONException {

        this.city = new WeatherApiObject_City();

        JSONObject r;

        Log.d(TAG, reader.keys().toString());

        // coord
        if (reader.has("coord")) {
            r = reader.getJSONObject("coord");
            if (r.has("lat"))
                this.city.coord.lat = r.getDouble("lat");
            if (r.has("lon"))
                this.city.coord.lon = r.getDouble("lon");
        }

        // weather
        if (reader.has("weather")) {
            JSONArray arr = reader.getJSONArray("weather");
            r = arr.getJSONObject(0);
            if (r.has("id"))
                this.city.weather.id = r.getInt("id");
            if (r.has("main"))
                this.city.weather.main = r.getString("main");
            if (r.has("description"))
                this.city.weather.description = r.getString("description");
            if (r.has("icon"))
                this.city.weather.icon = r.getString("icon");
        }

        // main
        if (reader.has("main")) {
            r = reader.getJSONObject("main");
            if (r.has("temp"))
                this.city.main.temp = r.getInt("temp");
            if (r.has("pressure"))
                this.city.main.pressure = r.getDouble("pressure");
            if (r.has("humidity"))
                this.city.main.humidity = r.getDouble("humidity");
            if (r.has("temp_min"))
                this.city.main.temp_min = r.getDouble("temp_min");
            if (r.has("temp_max"))
                this.city.main.temp_max = r.getDouble("temp_max");
        }

        // wind
        if (reader.has("wind")) {
            r = reader.getJSONObject("wind");
            if (r.has("speed"))
                this.city.wind.speed = r.getDouble("speed");
            if (r.has("deg"))
                this.city.wind.deg = r.getDouble("deg");
        }

        // rain
        if (reader.has("rain")) {
            r = reader.getJSONObject("rain");
            if (r.has("1h"))
                this.city.rain.hour = r.getDouble("1h");
        }
        // clouds
        if (reader.has("clouds")) {
            r = reader.getJSONObject("clouds");
            if (r.has("all"))
                this.city.clouds.all = r.getDouble("all");
        }

        // sys
        if (reader.has("sys")) {
            r = reader.getJSONObject("sys");

            if (r.has("type"))
                this.city.sys.type = r.getInt("type");
            if (r.has("id"))
                this.city.sys.id = r.getInt("id");
            if (r.has("message"))
                this.city.sys.message = r.getDouble("message");
            if (r.has("country"))
                this.city.sys.country = r.getString("country");
            if (r.has("sunrise"))
                this.city.sys.sunrise = r.getInt("sunrise");
            if (r.has("sunset"))
                this.city.sys.sunset = r.getInt("sunset");
        }

        // others
        if (reader.has("base"))
            this.city.base = reader.getString("base");

        if (reader.has("visibility"))
            this.city.visibility = reader.getInt("visibility");

        if (reader.has("id"))
            this.city.id = reader.getInt("id");

        if (reader.has("name"))
            this.city.name = reader.getString("name");

        if (reader.has("cod"))
            this.city.cod = reader.getInt("cod");

    }


}
