package com.pru.mis.todolistwithalarm201735039;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void showToast(Context context, int text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
