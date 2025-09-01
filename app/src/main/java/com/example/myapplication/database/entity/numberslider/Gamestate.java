/**
 * Tabele des Spielstandes
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.database.entity.numberslider;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;




@Entity
public class Gamestate {
    @PrimaryKey(autoGenerate = true)
    public int count;
    @NonNull
    public String current_id;
}
