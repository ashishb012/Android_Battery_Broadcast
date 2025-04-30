package com.example.android_battery_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BATTERY_UPDATE_ACTION = "com.example.android_battery_broadcast.BATTERY_UPDATE";
    public static final String BATTERY_LEVEL = "batteryLevel";
    public static final String IS_CHARGING = "isCharging";

    BatteryDataTransformer transformer;
    BatteryNotifier notifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transformer = new BatteryDataTransformer();
        notifier = new BatteryNotifier();

        // Register both receivers dynamically
        IntentFilter transformerFilter = new IntentFilter(BATTERY_UPDATE_ACTION);
        transformerFilter.setPriority(100);  // Higher priority
        registerReceiver(transformer, transformerFilter, Context.RECEIVER_EXPORTED);

        IntentFilter notifierFilter = new IntentFilter(BATTERY_UPDATE_ACTION);
        notifierFilter.setPriority(50);  // Lower priority
        registerReceiver(notifier, notifierFilter, Context.RECEIVER_EXPORTED);

        Button sendBatteryUpdateButton = findViewById(R.id.sendBatteryUpdateButton);
        sendBatteryUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBatteryUpdate();
            }
        });
    }

    private void sendBatteryUpdate() {
        int batteryLevel = (int) (Math.random() * 100);
        boolean isCharging = Math.random() < 0.5;

        Log.d(TAG, "Sending Battery Update - Level: " + batteryLevel + ", Charging: " + isCharging);
        Toast.makeText(this, "Sending Battery Update - Level: " + batteryLevel + ", Charging: " + isCharging, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(BATTERY_UPDATE_ACTION);
        intent.putExtra(BATTERY_LEVEL, batteryLevel);
        intent.putExtra(IS_CHARGING, isCharging);

        sendOrderedBroadcast(intent, null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(transformer);
        unregisterReceiver(notifier);
    }
}
