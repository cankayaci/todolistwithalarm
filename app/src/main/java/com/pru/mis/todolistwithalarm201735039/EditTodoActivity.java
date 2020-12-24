package com.pru.mis.todolistwithalarm201735039;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import static com.pru.mis.todolistwithalarm201735039.Utils.showToast;
import static com.pru.mis.todolistwithalarm201735039.Validator.isDateValid;
import static com.pru.mis.todolistwithalarm201735039.Validator.isTimeValid;

public class EditTodoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    int itemPosition;
    SharedPreferences sharedPref;
    EditText etTodoName;
    EditText etTodoDate;
    EditText etTodoTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        sharedPref = getSharedPreferences(SharedPrefUtil.SHARED_TAG, Context.MODE_PRIVATE);

        etTodoName = findViewById(R.id.etTodoName);
        etTodoDate = findViewById(R.id.etTodoDate);
        etTodoTime = findViewById(R.id.etTodoTime);

        Intent intent = getIntent();

        etTodoName.setText(intent.getStringExtra("todoName"));
        etTodoDate.setText(intent.getStringExtra("todoDate"));
        etTodoTime.setText(intent.getStringExtra("todoTime"));
        itemPosition = intent.getIntExtra("todoPosition", -1);

        Button buttonDelete = findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditTodoActivity.this);
                builder.setTitle(R.string.delete_entry);
                builder.setMessage(R.string.are_you_sure_delete);
                builder.setPositiveButton(getText(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem();
                    }
                });
                builder.setNegativeButton(R.string.no, null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    public void deleteItem() {
        final List<Todo> todoList = SharedPrefUtil.getList(sharedPref);

        if (todoList != null && todoList.size() > itemPosition && itemPosition != -1) {
            todoList.remove(itemPosition);
            SharedPrefUtil.setList(sharedPref, SharedPrefUtil.TODO_KEY, todoList);
            finish();
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void editOnClick(View view) {
        String todoName = etTodoName.getText().toString();
        String todoDate = etTodoDate.getText().toString();
        String todoTime = etTodoTime.getText().toString();

        if (todoName.isEmpty()) {
            showToast(getApplicationContext(), R.string.todo_name_error);
            return;
        }

        if (!isDateValid(todoDate)) {
            showToast(getApplicationContext(), R.string.todo_date_error);
            return;
        }

        if (!isTimeValid(todoTime)) {
            showToast(getApplicationContext(), R.string.todo_time_error);
            return;
        }

        Todo todo = new Todo(todoName, todoDate, todoTime);

        List<Todo> todoList = SharedPrefUtil.getList(sharedPref);
        if (todoList == null) {
            todoList = new ArrayList<>();
        }

        todoList.set(itemPosition, todo);

        SharedPrefUtil.setList(sharedPref, SharedPrefUtil.TODO_KEY, todoList);
        showToast(getApplicationContext(), R.string.edited_todo);
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