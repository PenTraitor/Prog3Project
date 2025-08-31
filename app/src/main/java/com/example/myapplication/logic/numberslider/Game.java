package com.example.myapplication.logic.numberslider;

import android.content.Context;

import com.example.myapplication.database.access.AppDatabase;
import com.example.myapplication.database.entity.numberslider.play_field;

public class Game {
    public String current_field;
    private final AppDatabase db;
    public Game(Context context) {
        // Always use applicationContext to avoid memory leaks
        this.db = AppDatabase.getInstance(context.getApplicationContext());
    }
    public void Start(){
        current_field = null;



        play_field newField = new play_field();


        new Thread(() -> db.playFieldDao().insert(newField)).start();
    }
}
