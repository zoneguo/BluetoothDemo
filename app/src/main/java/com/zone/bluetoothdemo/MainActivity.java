package com.zone.bluetoothdemo;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zone.bluetoothdemo.receiver.BluetoothProfileMonitor;
import com.zone.bluetoothdemo.utils.BluetoothUtils;

import java.util.List;

/**
 * 功能列表
 * 1. 获取当前已连接蓝牙设备信息
 * 2. 监听蓝牙设备断连状态
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button mBtnGetConnectedBT;

    private Button mBtnGetProfile;

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        initVars();
    }

    private void initUI() {
        mBtnGetConnectedBT = findViewById(R.id.btn_get_connected_bt);
        mBtnGetConnectedBT.setOnClickListener(this);

        mBtnGetProfile = findViewById(R.id.btn_get_profile);
        mBtnGetProfile.setOnClickListener(this);
    }

    private void initVars() {
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_connected_bt:
                int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
                getConnectedBTList();
                break;

            case R.id.btn_get_profile:
                BluetoothProfileMonitor.getInstance().getProfile();
                BluetoothProfileMonitor.getInstance().getProfileStatus();
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND
                                | AudioManager.FLAG_SHOW_UI);
                break;
        }
    }

    private void getConnectedBTList() {
        List<BluetoothDevice> btList = BluetoothUtils.getConnectedBTList();
        if (btList != null) {
            for (BluetoothDevice device : btList) {
                Log.d(TAG, "[getConnectedBTList] dev=" + device.getName());
            }
        }
    }
}
