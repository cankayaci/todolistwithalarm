package com.pru.mis.todolistwithalarm201735039;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private RecyclerView rvTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences(SharedPrefUtil.SHARED_TAG, Context.MODE_PRIVATE);

        List<Todo> todoList = SharedPrefUtil.getList(sharedPref);

        rvTodoList = findViewById(R.id.rvTodoList);
        TodoAdapter todoAdapter = new TodoAdapter(todoList);
        rvTodoList.setAdapter(todoAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTodoList.setLayoutManager(linearLayoutManager);

        rvTodoList.addItemDecoration(new DividerItemDecoration(rvTodoList.getContext(), DividerItemDecoration.VERTICAL));
    }
}