package com.pru.mis.todolistwithalarm201735039.activity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pru.mis.todolistwithalarm201735039.R;
import com.pru.mis.todolistwithalarm201735039.model.Todo;
import com.pru.mis.todolistwithalarm201735039.receiver.AlarmReceiver;
import com.pru.mis.todolistwithalarm201735039.util.SharedPrefUtil;
import com.pru.mis.todolistwithalarm201735039.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.pru.mis.todolistwithalarm201735039.util.Utils.showToast;
import static com.pru.mis.todolistwithalarm201735039.util.Validator.isValidTodo;

public class EditTodoActivity extends EditableTodoActivity {

    private int itemPosition;
    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        sharedPref = getSharedPreferences(SharedPrefUtil.SHARED_TAG, Context.MODE_PRIVATE);

        etTodoName = findViewById(R.id.etTodoName);
        etTodoDate = findViewById(R.id.etTodoDate);
        etTodoTime = findViewById(R.id.etTodoTime);

        Intent intent = getIntent();

        itemPosition = intent.getIntExtra("todoPosition", -1);
        if (intent.getExtras() != null) {
            todo = intent.getExtras().getParcelable("todo");
        }

        if (todo != null) {
            etTodoName.setText(todo.getTodoName());
            etTodoDate.setText(todo.getTodoDate());
            etTodoTime.setText(todo.getTodoTime());

            year = todo.getYear();
            month = todo.getMonth();
            dayOfMonth = todo.getDayOfMonth();
            hourOfDay = todo.getHourOfDay();
            minute = todo.getMinute();
        }

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
            cancelOldAlarm(itemPosition, this.todo);
            todoList.remove(itemPosition);
            SharedPrefUtil.setList(sharedPref, SharedPrefUtil.TODO_KEY, todoList);
            finish();
        }
    }

    public void editOnClick(View view) {
        String todoName = etTodoName.getText().toString();
        Todo todo = new Todo(todoName, year, month, dayOfMonth, hourOfDay, minute);

        if (!isValidTodo(this, todo)) {
            return;
        }

        List<Todo> todoList = SharedPrefUtil.getList(sharedPref);
        if (todoList == null) {
            todoList = new ArrayList<>();
        }

        cancelOldAlarm(itemPosition, this.todo);
        Utils.createAlarm(getBaseContext(), this, itemPosition, todo);

        todoList.set(itemPosition, todo);

        SharedPrefUtil.setList(sharedPref, SharedPrefUtil.TODO_KEY, todoList);
        showToast(this, R.string.edited_todo);
    }

    private void cancelOldAlarm(int todoId, Todo oldTodo) {
        Calendar oldCal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

        oldCal.set(Calendar.DATE, oldTodo.getDayOfMonth());
        oldCal.set(Calendar.MONTH, oldTodo.getMonth());
        oldCal.set(Calendar.YEAR, oldTodo.getYear());

        oldCal.set(Calendar.HOUR_OF_DAY, oldTodo.getHourOfDay());
        oldCal.set(Calendar.MINUTE, oldTodo.getMinute());
        oldCal.set(Calendar.SECOND, 0);

        Intent oldIntent = new Intent(getBaseContext(), AlarmReceiver.class);
        oldIntent.setAction(String.valueOf(todoId));
        PendingIntent oldPendingIntent = PendingIntent.getBroadcast(this, 0, oldIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(oldPendingIntent);
    }
}