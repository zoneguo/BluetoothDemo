package com.zone.bluetoothdemo;

import android.app.Application;
import android.util.Log;

import com.zone.bluetoothdemo.receiver.BluetoothStatusReceiver;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    private BluetoothStatusReceiver mBTReceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "[onCreate]");
        registerBTReceiver();
    }

    private void registerBTReceiver() {
        mBTReceiver = new BluetoothStatusReceiver();
        registerReceiver(mBTReceiver, BluetoothStatusReceiver.constructIntentFilter());
    }
}
