package com.pru.mis.todolistwithalarm201735039.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.pru.mis.todolistwithalarm201735039.activity.AlarmActivity;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, AlarmActivity.class);
        alarmIntent.setAction(intent.getAction());
        context.startActivity(alarmIntent);
    }
}
