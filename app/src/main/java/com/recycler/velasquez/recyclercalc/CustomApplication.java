package com.recycler.velasquez.recyclercalc;

import android.app.Application;
import android.content.res.Configuration;

import com.recycler.velasquez.recyclercalc.Utilities.RealmOperations;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yadder on 7/19/17.
 */

public class CustomApplication extends Application {

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!

        //init for realm
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        RealmOperations.initRealmDatabase();
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
