package com.example.pjez.weather.api;

import android.support.annotation.NonNull;

import com.example.pjez.weather.localstorage.InternalStorage;

/**
 * Created by pjez on 03.09.16.
 */
public class WeatherApiObject_City extends WeatherApiObject {


    public Coord coord = new Coord();
    public Weather weather = new Weather();
    public String base = null;
    public Main main = new Main();
    public Integer visibility;
    public Wind wind = new Wind();
    public Rain rain = new Rain();
    public Clouds clouds = new Clouds();
    public Integer dt = null;
    public Sys sys = new Sys();
    public Integer id = null;
    public String name = null;
    public Integer cod = null;

    @Override
    public String toString() {
        return "WeatherApiObject_City{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", rain=" + rain +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }


//    #############

    public class Coord {
        public double lon = 0;
        public double lat = 0;

        public String toString() {
            return "Coord{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    public class Weather {

        public Integer id = null;
        public String main = null;
        public String description = null;
        public String icon = null;

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public class Main {
        public double temp = 0;
        public double pressure = 0;
        public double humidity = 0;
        public double temp_min = 0;
        public double temp_max = 0;

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    '}';
        }
    }

    public class Wind {
        public double speed = 0;
        public double deg = 0;

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

    public class Rain {
        public double hour = 0;

        @Override
        public String toString() {
            return "Rain{" +
                    "hour=" + hour +
                    '}';
        }
    }

    public class Clouds {
        public double all = 0;

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }

    public class Sys {
        public Integer type = null;
        public Integer id = null;
        public double message = 0;
        public String country = null;
        public Integer sunrise = null;
        public Integer sunset = null;

        @Override
        public String toString() {
            return "Sys{" +
                    "type=" + type +
                    ", id=" + id +
                    ", message=" + message +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

}






