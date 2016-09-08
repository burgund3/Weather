package com.example.pjez.weather.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pjez on 03.09.16.
 */
public class Common {

    Activity activity = null;

    public Common(Activity activity) {
        this.activity = activity;
    }


    public class Networking {

        public ConnectivityManager getConnectivityManager() {

            return (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        public NetworkInfo getNetworkInfo() {

            return getConnectivityManager().getActiveNetworkInfo();
        }

        public boolean isNetworkConnected() {

            return getNetworkInfo() != null && getNetworkInfo().isConnected();

        }
    }

}
