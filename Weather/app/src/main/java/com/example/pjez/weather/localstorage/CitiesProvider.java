package com.example.pjez.weather.localstorage;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by pjez on 03.09.16.
 */
public class CitiesProvider extends InternalStorage {

    private static final String TAG = "Cities";

    String FILENAME = "cities";

    ArrayList<String> cities = new ArrayList<String>();

    public CitiesProvider(Context context) throws Exception {
        super(context);

    }


    public void addCity(String city) throws Exception {

        if (isExist(city))
            throw new Exception("Miasto już jest na liście.");

        cities.add(city);

        saveCities();

    }


    public void removeCity(String city) throws Exception {

        if (isExist(city))
            cities.remove(city);

        saveCities();


    }

    public ArrayList getCities() throws Exception {

        loadCities();

        return cities;
    }


    public void saveCities() throws Exception {

        String data = "";

        if (!cities.isEmpty()) {
            for (String s : cities) {
                data += s + ';';
            }
            data = data.substring(0, data.length() - 1);
        }

        saveToFile(FILENAME, data);

    }

    public void loadCities() throws Exception {

        String data = readFromFile(FILENAME);
        cities = new ArrayList<String>();

        if (data.length() > 0) {

            String[] arr = data.split(";");
            for (String s : arr) {
                cities.add(s);
            }
        }

    }


    public boolean isExist(String city) throws Exception {

        loadCities();

        if (cities.contains(city))
            return true;

        return false;

    }


}
