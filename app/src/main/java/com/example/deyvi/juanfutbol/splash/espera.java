package com.example.deyvi.juanfutbol.splash;

import android.app.Application;
import android.os.SystemClock;
import android.provider.Settings;

/**
 * Created by deyvi on 26/04/2017.
 */

public class espera extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
