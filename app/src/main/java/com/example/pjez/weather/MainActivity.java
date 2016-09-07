package com.example.pjez.weather;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    private EditText cityText;
    private TextView status;
    protected Spinner citiesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citiesList = (Spinner) findViewById(R.id.cities_list);
        cityText = (EditText) findViewById(R.id.city_name);
        status = (TextView) findViewById(R.id.status);
    }


    // ustawianie statusu
    private void setStatus(String statusText) {
        status.setText(statusText);
    }


    // dodawanie miasta
    public void clickAddCity(View view) {

        Editable cityName = cityText.getText();

        if(cityName.length() == 0){
            setStatus("Nazwa miasta nie może byc pusta.");
            return;
        }

        setStatus(cityName.toString());
    }

}
