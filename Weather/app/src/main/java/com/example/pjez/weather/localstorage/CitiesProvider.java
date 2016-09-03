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

    String FILENAME = "cities.data";

    ArrayList<String> cities = new ArrayList<String>();

    public CitiesProvider(Context context) {
        super(context);

    }


    public void addCity(String city) throws Exception {

        if (isExist(city))
            throw new Exception("Miasto już jest na liście.");

        cities.add(city);

        saveCities();

    }


    public void removeCity(String city) {

        if (isExist(city))
            cities.remove(city);

        saveCities();


    }

    public ArrayList getCities() {

        loadCities();

        return cities;
    }


    public void saveCities() {

        String data = "";

        if (!cities.isEmpty()) {
            for (String s : cities) {
                data += s + ';';
            }
            data = data.substring(0, data.length() - 1);
        }

        try {
            saveToFile(FILENAME, data);

        } catch (Exception e) {

        }

    }

    public void loadCities() {
        cities = new ArrayList<String>();

        try {
            String data = readFromFile(FILENAME);

            if (data.length() > 0) {

                String[] arr = data.split(";");
                for (String s : arr) {
                    cities.add(s);
                }
            }

        } catch (Exception e) {

        }

    }


    public boolean isExist(String city) {

        loadCities();

        if (cities.contains(city))
            return true;

        return false;

    }


}
