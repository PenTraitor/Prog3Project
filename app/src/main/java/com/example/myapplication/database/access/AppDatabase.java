/**
 * Zugriff auf die Datenbank
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.database.access;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.database.DAO.numberslider.GamestateDao;
import com.example.myapplication.database.DAO.numberslider.PlayFieldDao;
import com.example.myapplication.database.entity.numberslider.Gamestate;
import com.example.myapplication.database.entity.numberslider.play_field;

@Database(entities = {play_field.class , Gamestate.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract PlayFieldDao playFieldDao();
    public abstract GamestateDao gamestateDao();

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "game_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

