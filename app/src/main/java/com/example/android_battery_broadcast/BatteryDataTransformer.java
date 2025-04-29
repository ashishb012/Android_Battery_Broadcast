package com.example.android_battery_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BatteryDataTransformer extends BroadcastReceiver {

    private static final String TAG = "BatteryDataTransformer";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "BatteryDataTransformer received broadcast");

        // Get battery level and charging state
        int batteryLevel = intent.getIntExtra(MainActivity.BATTERY_LEVEL, 0);
        boolean isCharging = intent.getBooleanExtra(MainActivity.IS_CHARGING, false);

        // Log battery information
        Log.d(TAG, "Battery Level: " + batteryLevel + ", Charging: " + isCharging);

        // Transform battery level to description
        String batteryStatus;
        if (batteryLevel >= 80) {
            batteryStatus = "High";
        } else if (batteryLevel >= 50) {
            batteryStatus = "Medium";
        } else {
            batteryStatus = "Low";
        }

        // Set battery status as result
        Bundle bundle = new Bundle();
        bundle.putString("batteryStatus", batteryStatus);
        setResultExtras(bundle);

        // Abort broadcast if battery is low
        if (batteryLevel < 20) {
            abortBroadcast();
            Log.d(TAG, "Broadcast aborted due to low battery");
        }
    }
}