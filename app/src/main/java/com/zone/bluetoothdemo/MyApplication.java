package com.zone.bluetoothdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.zone.bluetoothdemo.receiver.BluetoothStatusReceiver;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    private BluetoothStatusReceiver mBTReceiver;

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "[onCreate]");
        sContext = this;

        init();
    }

    private void init() {
        registerBTReceiver();
    }

    private void registerBTReceiver() {
        mBTReceiver = new BluetoothStatusReceiver();
        registerReceiver(mBTReceiver, BluetoothStatusReceiver.constructIntentFilter());
    }

    public static Context getAppContext() {
        return sContext;
    }
}
