package com.pru.mis.todolistwithalarm201735039.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.pru.mis.todolistwithalarm201735039.model.Todo;
import com.pru.mis.todolistwithalarm201735039.receiver.AlarmReceiver;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static void showToast(Context context, int text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void createAlarm(Context baseContext, Context context, int todoId, Todo todo) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

        cal.set(Calendar.DATE, todo.getDayOfMonth());
        cal.set(Calendar.MONTH, todo.getMonth());
        cal.set(Calendar.YEAR, todo.getYear());

        cal.set(Calendar.HOUR_OF_DAY, todo.getHourOfDay());
        cal.set(Calendar.MINUTE, todo.getMinute());
        cal.set(Calendar.SECOND, 0);

        Intent intent = new Intent(baseContext, AlarmReceiver.class);
        intent.setAction(String.valueOf(todoId));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

    }
}
