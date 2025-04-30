package com.example.android_battery_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BatteryNotifier extends BroadcastReceiver {

    private static final String TAG = "BatteryNotifier";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "BatteryNotifier received broadcast");

        Bundle resultExtras = getResultExtras(true);
        String batteryStatus = resultExtras.getString("batteryStatus", "Unknown");

        Toast.makeText(context, "Battery Status: " + batteryStatus, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Battery Status: " + batteryStatus);
    }
}
