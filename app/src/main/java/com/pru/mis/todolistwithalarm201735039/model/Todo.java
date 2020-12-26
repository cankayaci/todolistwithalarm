package com.pru.mis.todolistwithalarm201735039.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private String todoName;
    private boolean done = false;
    private int year;
    private int month;
    private int dayOfMonth;
    private int hourOfDay;
    private int minute;

    public Todo(String todoName, int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this.todoName = todoName;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }

    protected Todo(Parcel in) {
        todoName = in.readString();
        done = in.readByte() != 0;
        year = in.readInt();
        month = in.readInt();
        dayOfMonth = in.readInt();
        hourOfDay = in.readInt();
        minute = in.readInt();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTodoDate() {
        return String.format("%d.%d.%d", dayOfMonth, month + 1, year);
    }

    public String getTodoTime() {
        return String.format("%02d:%02d", hourOfDay, minute);
    }

    public String getDateTime() {
        return getTodoDate() + " " + getTodoTime();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(todoName);
        dest.writeByte((byte) (done ? 1 : 0));
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(dayOfMonth);
        dest.writeInt(hourOfDay);
        dest.writeInt(minute);
    }
}

