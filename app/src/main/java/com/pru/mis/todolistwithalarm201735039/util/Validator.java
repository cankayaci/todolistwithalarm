package com.pru.mis.todolistwithalarm201735039.util;

import android.content.Context;

import com.pru.mis.todolistwithalarm201735039.R;
import com.pru.mis.todolistwithalarm201735039.model.Todo;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.pru.mis.todolistwithalarm201735039.util.Utils.showToast;

public class Validator {

    private static boolean isDateValid(String date) {
        return date.matches("^\\d{2}.\\d{2}.\\d{4}$");
    }

    private static boolean isTimeValid(String time) {
        return time.matches("^\\d{2}:\\d{2}$");
    }

    private static boolean isDateTimeValid(Todo todo) {
        Calendar nowDate = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        Calendar toDate = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

        toDate.set(Calendar.DATE, todo.getDayOfMonth());
        toDate.set(Calendar.MONTH, todo.getMonth());
        toDate.set(Calendar.YEAR, todo.getYear());

        toDate.set(Calendar.HOUR_OF_DAY, todo.getHourOfDay());
        toDate.set(Calendar.MINUTE, todo.getMinute());
        toDate.set(Calendar.SECOND, 0);

        return toDate.after(nowDate);
    }

    public static boolean isValidTodo(Context context, Todo todo) {
        if (todo.getTodoName().isEmpty()) {
            showToast(context, R.string.todo_name_error);
            return false;
        }

        if (!isDateValid(todo.getTodoDate())) {
            showToast(context, R.string.todo_date_error);
            return false;
        }

        if (!isTimeValid(todo.getTodoTime())) {
            showToast(context, R.string.todo_time_error);
            return false;
        }

        if (!isDateTimeValid(todo)) {
            showToast(context, R.string.todo_date_time_error);
            return false;
        }

        return true;
    }

}
