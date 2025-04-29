package com.example.android_battery_broadcast;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendBatteryUpdateButton = findViewById(R.id.sendBatteryUpdateButton);
        sendBatteryUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBatteryUpdate();
            }
        });
    }

    private void sendBatteryUpdate() {
        // Simulate battery data
        int batteryLevel = (int) (Math.random() * 100); // 0-100
        boolean isCharging = Math.random() < 0.5; // 50% chance of true/false

        Log.d(TAG, "Sending Battery Update - Level: " + batteryLevel + ", Charging: " + isCharging);
        Toast.makeText(this, "Sending Battery Update - Level: " + batteryLevel + ", Charging: " + isCharging, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(BATTERY_UPDATE_ACTION);
        intent.putExtra(BATTERY_LEVEL, batteryLevel);
        intent.putExtra(IS_CHARGING, isCharging);

        // Send ordered broadcast
        sendOrderedBroadcast(intent, "com.example.android_battery_broadcast.PERMISSION");
    }
}