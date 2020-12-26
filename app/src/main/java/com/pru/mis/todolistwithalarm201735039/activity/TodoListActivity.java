package com.pru.mis.todolistwithalarm201735039.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pru.mis.todolistwithalarm201735039.R;
import com.pru.mis.todolistwithalarm201735039.adapter.TodoAdapter;
import com.pru.mis.todolistwithalarm201735039.model.Todo;
import com.pru.mis.todolistwithalarm201735039.util.SharedPrefUtil;

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

        setVisibilities(todoList.size());
    }

    private void setVisibilities(int size) {
        TextView tvNoTodoMessage = findViewById(R.id.tvNoTodoMessage);
        ImageView ivAddTodo = findViewById(R.id.ivAddTodo);
        TextView tvAddTodo = findViewById(R.id.tvAddTodo);

        if (size == 0) {
            rvTodoList.setVisibility(View.GONE);
            tvNoTodoMessage.setVisibility(View.VISIBLE);
            ivAddTodo.setVisibility(View.VISIBLE);
            tvAddTodo.setVisibility(View.VISIBLE);
        } else {
            rvTodoList.setVisibility(View.VISIBLE);
            tvNoTodoMessage.setVisibility(View.GONE);
            ivAddTodo.setVisibility(View.GONE);
            tvAddTodo.setVisibility(View.GONE);
        }
    }

    public void addTodoOnClick(View view) {
        Intent intent = new Intent(this, AddTodoActivity.class);
        startActivity(intent);
    }
}