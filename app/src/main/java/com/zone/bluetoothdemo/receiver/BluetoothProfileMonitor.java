package com.zone.bluetoothdemo.receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.util.Log;

import com.zone.bluetoothdemo.MyApplication;

import java.util.List;

public class BluetoothProfileMonitor {
    private static final String TAG = "BluetoothProfileMonitor";

    private BluetoothProfileMonitor() {

    }

    private static class SingletonHolder {
        private static final BluetoothProfileMonitor INSTANCE = new BluetoothProfileMonitor();
    }

    public static BluetoothProfileMonitor getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean start() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Log.d(TAG, "[start] bluetooth is disabled");
            return false;
        }

        Log.d(TAG, "[start] A2DP = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.A2DP));
        Log.d(TAG, "[start] HEADSET = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.HEADSET));
        Log.d(TAG, "[start] GATT = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.GATT));
        Log.d(TAG, "[start] GATT_SERVER = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.GATT_SERVER));

        return true;
    }

    private BluetoothProfile.ServiceListener mServiceListener = new BluetoothProfile.ServiceListener() {
        @Override
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            Log.d(TAG, "[onServiceConnected] profile = " + getProfileDesc(profile));
            if (proxy != null) {
                List<BluetoothDevice> deviceList = proxy.getConnectedDevices();
                if (deviceList != null) {
                    for (BluetoothDevice device : deviceList) {
                        Log.d(TAG, "name = " + device.getName());
                    }
                }
            }
        }

        @Override
        public void onServiceDisconnected(int profile) {
            Log.d(TAG, "[onServiceDisconnected] profile = " + getProfileDesc(profile));
        }
    };

    private String getProfileDesc(int profile) {
        switch (profile) {
            case BluetoothProfile.GATT:
                return "GATT";

            case BluetoothProfile.GATT_SERVER:
                return "GATT_SERVER";

            case BluetoothProfile.A2DP:
                return "A2DP";

            case BluetoothProfile.HEADSET:
                return "HEADSET";

            default:
                return "Other";
        }
    }
}
