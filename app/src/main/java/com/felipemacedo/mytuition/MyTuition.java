package com.felipemacedo.mytuition;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by felipemacedo on 25/10/17.
 */

public class MyTuition extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
