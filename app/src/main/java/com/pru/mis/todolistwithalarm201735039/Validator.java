package com.pru.mis.todolistwithalarm201735039;

public class Validator {

    public static boolean isDateValid(String date) {
        return date.matches("^\\d{2}.\\d{2}.\\d{4}$");
    }

    public static boolean isTimeValid(String time) {
        return time.matches("^\\d{2}:\\d{2}$");
    }
}
