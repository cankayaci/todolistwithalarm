package com.pru.mis.todolistwithalarm201735039;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        Intent intent = getIntent();

        EditText etTodoName = findViewById(R.id.etTodoName);
        etTodoName.setText(intent.getStringExtra("todoName"));

        EditText etTodoDate = findViewById(R.id.etTodoDate);
        etTodoDate.setText(intent.getStringExtra("todoDate"));

        EditText etTodoTime = findViewById(R.id.etTodoTime);
        etTodoTime.setText(intent.getStringExtra("todoTime"));
    }
}