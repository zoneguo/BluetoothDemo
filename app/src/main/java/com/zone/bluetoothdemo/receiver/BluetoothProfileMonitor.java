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

    public boolean getProfile() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Log.d(TAG, "[getProfile] bluetooth is disabled");
            return false;
        }

        Log.d(TAG, "[getProfile] A2DP = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.A2DP));
        Log.d(TAG, "[getProfile] HEADSET = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.HEADSET));
        Log.d(TAG, "[getProfile] GATT = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.GATT));
        Log.d(TAG, "[getProfile] GATT_SERVER = " + bluetoothAdapter.getProfileProxy(MyApplication.getAppContext(), mServiceListener, BluetoothProfile.GATT_SERVER));

        return true;
    }

    public void getProfileStatus() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Log.d(TAG, "[getProfileStatus] bluetooth is disabled");
            return;
        }

        Log.d(TAG, "[getProfileStatus] A2DP = " + bluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP));
        Log.d(TAG, "[getProfileStatus] HEADSET = " + bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET));
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
