package com.example.pjez.weather.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pjez on 03.09.16.
 */
public class Common {

    AppCompatActivity act = null;

    public Common(AppCompatActivity act) {
        this.act = act;
    }

    public ConnectivityManager getConnectivityManager() {

        return (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public NetworkInfo getNetworkInfo() {

        return getConnectivityManager().getActiveNetworkInfo();
    }

    public boolean isNetworkConnected() {

        return getNetworkInfo() != null && getNetworkInfo().isConnected();

    }
}
