package com.pru.mis.todolistwithalarm201735039.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pru.mis.todolistwithalarm201735039.model.Todo;

import java.lang.reflect.Type;
import java.util.List;

public class SharedPrefUtil {

    public static final String TODO_KEY = "SHARED_TODO_LIST";
    public static final String SHARED_TAG = "SHARED_TAG";

    public static <T> void setList(SharedPreferences sharedPref, String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(sharedPref, key, json);
    }

    public static void set(SharedPreferences sharedPref, String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public static List<Todo> getList(SharedPreferences sharedPref) {
        List<Todo> todoList = null;
        String serializedObject = sharedPref.getString(TODO_KEY, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Todo>>() {
            }.getType();
            todoList = gson.fromJson(serializedObject, type);
        }
        return todoList;
    }

}
