package com.kodomo.ktortemplate

import android.app.Application
import com.facebook.stetho.Stetho

class CustomeApplication : Application() {
    override fun onCreate() {
        super.onCreate();
        // Stetho Init

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

}