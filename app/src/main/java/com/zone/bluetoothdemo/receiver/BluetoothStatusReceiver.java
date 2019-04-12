package com.zone.bluetoothdemo.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BluetoothStatusReceiver extends BroadcastReceiver {

    private static final String TAG = "BluetoothStatusReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "[onReceive] action = " + action);
        if (action == null) {
            return;
        }

        if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {

        } else if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {

        }
    }

    public static IntentFilter constructIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        return intentFilter;
    }
}
