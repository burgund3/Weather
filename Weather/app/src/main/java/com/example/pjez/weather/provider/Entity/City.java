package com.example.pjez.weather.provider.Entity;

/**
 * Created by pjez on 05.09.16.
 */
public class City {

    protected Integer id;
    protected String name;

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
