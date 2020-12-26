package com.pru.mis.todolistwithalarm201735039.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pru.mis.todolistwithalarm201735039.R;
import com.pru.mis.todolistwithalarm201735039.activity.EditTodoActivity;
import com.pru.mis.todolistwithalarm201735039.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    private List<Todo> todoList;

    public TodoAdapter(List<Todo> todoList) {
        this.todoList = todoList;
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
        Todo todo = todoList.get(position);
        holder.setTodo(todo);
    }

    @Override
    public int getItemCount() {
        if (todoList != null) {
            return todoList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDate;
        private TextView tvTime;
        private ImageView ivStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTodoName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivStatus = itemView.findViewById(R.id.ivStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EditTodoActivity.class);
                    intent.putExtra("todoName", tvName.getText());
                    intent.putExtra("todoDate", tvDate.getText());
                    intent.putExtra("todoTime", tvTime.getText());
                    intent.putExtra("todoPosition", getAdapterPosition());
                    intent.putExtra("todo", todoList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

        public void setTodo(Todo todo) {
            tvName.setText(todo.getTodoName());
            tvDate.setText(todo.getTodoDate());
            tvTime.setText(todo.getTodoTime());
            if (todo.isDone()) {
                ivStatus.setImageResource(R.drawable.ic_done_black_24dp);
            } else {
                ivStatus.setImageResource(R.drawable.ic_schedule_black_24dp);
            }
        }

    }

}
