package com.example.pjez.weather.api.Weather.Object;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by pjez on 03.09.16.
 */
abstract public class AbstractApiWeatherObject {


    public String toString() {

        String t = getClass().getSimpleName() + "{";

        for (Field f : this.getClass().getDeclaredFields()) {

            if (!f.isSynthetic()) {

                try {
                    String name = f.getName().toString();
                    f.setAccessible(true);

                    String value = f.get(this) + "";
                    t += name + "=" + value + ", ";

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        t += "}";

        return t;

    }

}






