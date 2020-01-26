package com.shivasnape.mydeviceutil;

import android.app.Application;

import timber.log.Timber;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
