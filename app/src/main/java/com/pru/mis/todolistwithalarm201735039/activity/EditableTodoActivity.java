package com.pru.mis.todolistwithalarm201735039.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.pru.mis.todolistwithalarm201735039.fragment.DatePickerFragment;
import com.pru.mis.todolistwithalarm201735039.fragment.TimePickerFragment;

public class EditableTodoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    protected SharedPreferences sharedPref;
    protected EditText etTodoName;
    protected EditText etTodoDate;
    protected EditText etTodoTime;
    protected int year;
    protected int month;
    protected int dayOfMonth;
    protected int hourOfDay;
    protected int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        etTodoDate.setText(String.format("%d.%d.%d", dayOfMonth, month + 1, year));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        etTodoTime.setText(String.format("%02d:%02d", hourOfDay, minute));
    }
}