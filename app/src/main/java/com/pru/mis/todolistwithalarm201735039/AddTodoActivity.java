package com.pru.mis.todolistwithalarm201735039;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import static com.pru.mis.todolistwithalarm201735039.Validator.isDateValid;
import static com.pru.mis.todolistwithalarm201735039.Validator.isTimeValid;

public class AddTodoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    SharedPreferences sharedPref;
    EditText etTodoDate;
    EditText etTodoTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        etTodoDate = findViewById(R.id.etTodoDate);
        etTodoTime = findViewById(R.id.etTodoTime);

        sharedPref = getSharedPreferences(SharedPrefUtil.SHARED_TAG, Context.MODE_PRIVATE);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void saveOnClickButton(View view) {
        EditText etTodoName = findViewById(R.id.etTodoName);
        String todoName = etTodoName.getText().toString();

        String todoDate = etTodoDate.getText().toString();

        String todoTime = etTodoTime.getText().toString();

        if (todoName.isEmpty()) {
            showToast(R.string.todo_name_error);
            return;
        }

        if (!isDateValid(todoDate)) {
            showToast(R.string.todo_date_error);
            return;
        }

        if (!isTimeValid(todoTime)) {
            showToast(R.string.todo_time_error);
            return;
        }

        Todo todo = new Todo(todoName, todoDate, todoTime);

        List<Todo> todoList = SharedPrefUtil.getList(sharedPref);
        if (todoList == null) {
            todoList = new ArrayList<>();
        }

        todoList.add(todo);

        SharedPrefUtil.setList(sharedPref, SharedPrefUtil.TODO_KEY, todoList);

        etTodoName.setText("");
        etTodoDate.setText("");
        etTodoTime.setText("");

        showToast(R.string.saved_todo);
    }

    private void showToast(int text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        etTodoDate.setText(String.format("%d.%d.%d", dayOfMonth, month + 1, year));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        etTodoTime.setText(String.format("%02d:%02d", hourOfDay, minute));
    }
}