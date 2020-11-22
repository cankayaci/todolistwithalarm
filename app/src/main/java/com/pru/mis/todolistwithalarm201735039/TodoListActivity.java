package com.pru.mis.todolistwithalarm201735039;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private RecyclerView rvTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        rvTodoList = findViewById(R.id.rvTodoList);
        TodoAdapter todoAdapter = new TodoAdapter(getTodos());
        rvTodoList.setAdapter(todoAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTodoList.setLayoutManager(linearLayoutManager);

        rvTodoList.addItemDecoration(new DividerItemDecoration(rvTodoList.getContext(), DividerItemDecoration.VERTICAL));

    }

    List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Can", "01.12.2020", "12:25"));
        todos.add(new Todo("Can", "01.12.2020", "12:25"));
        todos.add(new Todo("Can", "01.12.2020", "12:25"));
        todos.add(new Todo("Canananana", "01.12.2020", "22:22"));

        return todos;
    }
}