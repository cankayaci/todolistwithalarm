package com.pru.mis.todolistwithalarm201735039;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    private List<Todo> todos;

    public TodoAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.setTodo(todo);
    }

    @Override
    public int getItemCount() {
        if (todos != null) {
            return todos.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDate;
        private TextView tvTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTodoName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EditTodoActivity.class);
                    intent.putExtra("todoName", tvName.getText());
                    intent.putExtra("todoDate", tvDate.getText());
                    intent.putExtra("todoTime", tvTime.getText());
                    context.startActivity(intent);
                }
            });
        }

        public void setTodo(Todo todo) {
            tvName.setText(todo.getTodoName());
            tvDate.setText(todo.getTodoDate());
            tvTime.setText(todo.getTodoTime());
        }

    }

}
