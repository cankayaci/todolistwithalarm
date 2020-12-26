package com.pru.mis.todolistwithalarm201735039.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pru.mis.todolistwithalarm201735039.R;
import com.pru.mis.todolistwithalarm201735039.model.Todo;
import com.pru.mis.todolistwithalarm201735039.util.SharedPrefUtil;

import java.util.List;
import java.util.Objects;

public class AlarmActivity extends AppCompatActivity {

    Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent intent = getIntent();

        int todoId = Integer.parseInt(Objects.requireNonNull(intent.getAction()));

        SharedPreferences sharedPref = getSharedPreferences(SharedPrefUtil.SHARED_TAG, Context.MODE_PRIVATE);
        List<Todo> todoList = SharedPrefUtil.getList(sharedPref);

        if (todoList.size() <= todoId) {
            finish();
        }

        Todo todo = todoList.get(todoId);

        TextView tvTodoName = findViewById(R.id.tvTodoName);
        tvTodoName.setText(todo.getTodoName());

        TextView tvDateTime = findViewById(R.id.tvDateTime);
        tvDateTime.setText(String.format("%s %s", todo.getTodoDate(), todo.getTodoTime()));

        todo.setDone(true);
        todoList.set(todoId, todo);
        SharedPrefUtil.setList(sharedPref, SharedPrefUtil.TODO_KEY, todoList);

        playSound();
    }

    private void playSound() {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(this, alarmUri);
        ringtone.play();
    }

    public void stopAlarm(View view) {
        ringtone.stop();
        finish();
    }
}