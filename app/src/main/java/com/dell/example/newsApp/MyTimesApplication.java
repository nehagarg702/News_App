package com.dell.example.newsApp;

import android.app.Application;

public class MyTimesApplication extends Application {
    private static MyTimesApplication myTimesApplicationInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        myTimesApplicationInstance = this;
    }
    public static MyTimesApplication getMyTimesApplicationInstance(){
        return myTimesApplicationInstance;
    }
}
