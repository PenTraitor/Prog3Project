/**
 * Primäre Presenterklasse des 2048 spiels
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.logic.numberslider;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.database.access.AppDatabase;
import com.example.myapplication.database.entity.numberslider.Gamestate;
import com.example.myapplication.database.entity.numberslider.play_field;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class game2048 {
    public String current_field;
    private final AppDatabase db;
    private final validator validator;
    private final Generator generator;
    private GameUpdateListener listener;
    public game2048(Context context) {
        this.db = AppDatabase.getInstance(context.getApplicationContext());
        this.generator = new Generator();
        validator = new validator();
    }
    public void setListener(GameUpdateListener listener) {
        this.listener = listener;
    }
    public void Start(){
        current_field = null;

        play_field newField = new play_field();
        newField.previous_field =null;
        generator.createNewGrid();
        newField.fromGrid( generator.createNewGrid());
        Gamestate gamestate = new Gamestate();
        gamestate.current_id= newField.id;
        new Thread(() -> db.gamestateDao().insert(gamestate)).start();
        new Thread(() -> db.playFieldDao().insert(newField)).start();
        listener.onGridUpdated(newField.toGrid());
    }
    public void Load(){
        current_field = null;

        executor.execute(() -> {
            Gamestate state = db.gamestateDao().getGamestateWithHighestCount();
            if (state != null) {
                // Found a Gamestat
                 play_field field = db.playFieldDao().getById(state.current_id);
                           listener.onGridUpdated(field.toGrid());
            } else {
                // Table empty
                Start();
            }


        });

    }


    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void NewMove(Direction direction) {

        executor.execute(() -> {
            try {
                // Background thread starts here


            Gamestate state = db.gamestateDao().getGamestateWithHighestCount();
            play_field field = db.playFieldDao().getById(state.current_id);
            int[][] grid = field.toGrid();


            //calculate new values
            generator.generateMove(grid, direction);
            //add random
            generator.placeRandomCells(grid);
            //update dbase
            play_field newfield = new play_field();
            newfield.previous_field = field.id;
            newfield.fromGrid(grid);

             db.playFieldDao().insert(newfield);
            state.current_id = newfield.id;

            db.gamestateDao().update(state);

            new Handler(Looper.getMainLooper()).post(() -> {
                listener.onGridUpdated(grid);
                if(!validator.isPlayable(grid)){
                    listener.onWin(getGridSum(grid));
                    Start();
                }
            });
            } catch (Exception e) {
                Log.e("NewMove", "Fehler in NewMove", e);
            }

        });
    }
    public int getGridSum(int[][] grid) {
        int sum = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                sum += grid[row][col];
            }
        }
        return sum;
    }


}
