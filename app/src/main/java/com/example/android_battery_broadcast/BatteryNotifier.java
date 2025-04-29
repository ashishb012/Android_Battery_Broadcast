package com.example.android_battery_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BatteryNotifier extends BroadcastReceiver {

    private static final String TAG = "BatteryNotifier";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "BatteryNotifier received broadcast");

        // Get battery status from result extras
        String batteryStatus = intent.getStringExtra("batteryStatus");

        // Display battery status in a Toast
        Toast.makeText(context, "Battery Status: " + batteryStatus, Toast.LENGTH_SHORT).show();
        // Log battery status
        Log.d(TAG, "Battery Status: " + batteryStatus);
    }
}