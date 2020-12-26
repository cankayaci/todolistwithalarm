package com.pru.mis.todolistwithalarm201735039.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.pru.mis.todolistwithalarm201735039.R;
import com.pru.mis.todolistwithalarm201735039.model.Todo;
import com.pru.mis.todolistwithalarm201735039.util.SharedPrefUtil;
import com.pru.mis.todolistwithalarm201735039.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.pru.mis.todolistwithalarm201735039.util.Utils.showToast;
import static com.pru.mis.todolistwithalarm201735039.util.Validator.isValidTodo;

public class AddTodoActivity extends EditableTodoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        etTodoName = findViewById(R.id.etTodoName);
        etTodoDate = findViewById(R.id.etTodoDate);
        etTodoTime = findViewById(R.id.etTodoTime);

        sharedPref = getSharedPreferences(SharedPrefUtil.SHARED_TAG, Context.MODE_PRIVATE);

        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        dayOfMonth = cal.get(Calendar.DATE);
        hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
        minute = (cal.get(Calendar.MINUTE) + 1) % 60;

        etTodoDate.setText(String.format("%d.%d.%d", dayOfMonth, month + 1, year));
        etTodoTime.setText(String.format("%02d:%02d", hourOfDay, minute));
    }

    public void saveOnClickButton(View view) {
        String todoName = etTodoName.getText().toString();

        Todo todo = new Todo(todoName, year, month, dayOfMonth, hourOfDay, minute);

        if (!isValidTodo(this, todo)) {
            return;
        }

        List<Todo> todoList = SharedPrefUtil.getList(sharedPref);
        if (todoList == null) {
            todoList = new ArrayList<>();
        }

        Utils.createAlarm(getBaseContext(), this, todoList.size(), todo);
        todoList.add(todo);

        SharedPrefUtil.setList(sharedPref, SharedPrefUtil.TODO_KEY, todoList);

        etTodoName.setText("");
        etTodoDate.setText("");
        etTodoTime.setText("");

        showToast(this, R.string.saved_todo);
    }

}