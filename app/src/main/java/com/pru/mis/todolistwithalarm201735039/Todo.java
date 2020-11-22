package com.pru.mis.todolistwithalarm201735039;

public class Todo {
    private String todoName;
    private String todoDate;
    private String todoTime;

    public Todo() {
    }

    public Todo(String todoName, String todoDate, String todoTime) {
        this.todoName = todoName;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
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

}

