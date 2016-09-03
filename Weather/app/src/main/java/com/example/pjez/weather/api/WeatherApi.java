package com.example.pjez.weather.api;

/**
 * Created by pjez on 03.09.16.
 */
public class WeatherApi {


    private static final String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
    private static final String APPID = "2d8dd2e5bcac0ecfa040cb8ab18a8cce";

    private String cityName = null;
    private String finalUrl = "";


    public WeatherApi setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getFinalUrl() {

        resetFinalUrl();
        addAPPID();

        addCityName();

        return finalUrl;
    }

    protected void resetFinalUrl() {
        finalUrl = baseUrl;
    }

    protected void addAPPID() {
        finalUrl += "?APPID=" + APPID;
    }

    protected void addCityName() {
        if (cityName != null)
            finalUrl += "&q=" + cityName;

    }

}
