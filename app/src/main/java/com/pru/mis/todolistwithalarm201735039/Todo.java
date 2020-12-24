package com.pru.mis.todolistwithalarm201735039;

public class Todo {
    private String todoName;
    private String todoDate;
    private String todoTime;
    private boolean done;

    public Todo() {
    }

    public Todo(String todoName, String todoDate, String todoTime) {
        this.todoName = todoName;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
    }

    public Todo(String todoName, String todoDate, String todoTime, boolean done) {
        this.todoName = todoName;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
        this.done = done;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public String getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    public String getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

