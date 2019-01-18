package com.dell.example.newsApp;

/****
 * Class to check the network connection
 */

import android.content.Context;
import android.net.ConnectivityManager;

public class UtilityMethods {

    public static boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) MyTimesApplication.getMyTimesApplicationInstance()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
