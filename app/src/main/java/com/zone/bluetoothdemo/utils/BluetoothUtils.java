package com.zone.bluetoothdemo.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class BluetoothUtils {

    /**
     * 获取蓝牙连接设备列表,
     */
    public static ArrayList<BluetoothDevice> getConnectedBTList() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Class bluetoothAdapterClass = BluetoothAdapter.class;
        ArrayList<BluetoothDevice> deviceList = new ArrayList<BluetoothDevice>();
        try {
            Method method = bluetoothAdapterClass.getDeclaredMethod("getConnectionState");
            method.setAccessible(true);
            int state = (int) method.invoke(adapter, (Object[]) null);
            if (state == BluetoothAdapter.STATE_CONNECTED || state == BluetoothAdapter.STATE_CONNECTING) {
                Set<BluetoothDevice> devices = adapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected");
                    method.setAccessible(true);
                    boolean isConnected = (boolean) isConnectedMethod.invoke(device);
                    if (isConnected) {
                        deviceList.add(device);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deviceList;
    }

    /**
     * Get the current connection state of the local Bluetooth adapter.
     * This can be used to check whether the local Bluetooth adapter is connected
     * to any profile of any other remote Bluetooth Device.
     *
     * <p> Use this function along with {@link #ACTION_CONNECTION_STATE_CHANGED}
     * intent to get the connection state of the adapter.
     *
     * @return One of {@link #STATE_CONNECTED}, {@link #STATE_DISCONNECTED},
     * {@link #STATE_CONNECTING} or {@link #STATE_DISCONNECTED}
     *
     * @hide
     *
     * public int getConnectionState()
     */
}
