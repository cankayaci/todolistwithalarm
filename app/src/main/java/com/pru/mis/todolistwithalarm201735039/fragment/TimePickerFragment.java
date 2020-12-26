package com.pru.mis.todolistwithalarm201735039.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = (c.get(Calendar.MINUTE) + 1) % 60;

        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
}
