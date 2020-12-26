package com.pru.mis.todolistwithalarm201735039.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pru.mis.todolistwithalarm201735039.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listOnClick(View view) {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    public void addTodoOnClick(View view) {
        Intent intent = new Intent(this, AddTodoActivity.class);
        startActivity(intent);
    }
}