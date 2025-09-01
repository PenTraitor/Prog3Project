/**
 * Speicherung und abfrage von Gamestate Tabelle
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.database.DAO.numberslider;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;


import com.example.myapplication.database.entity.numberslider.Gamestate;

import java.util.List;


@Dao
public interface GamestateDao {

    @Insert
    void insert(Gamestate gamestate);

    @Update
    void update(Gamestate gamestate);

    @Delete
    void delete(Gamestate gamestate);


    @Query("SELECT * FROM Gamestate")
    List<Gamestate> getAll();

    @Query("SELECT * FROM Gamestate WHERE current_id = :currentId LIMIT 1")
    Gamestate getByCurrentId(String currentId);

    @Query("SELECT * FROM Gamestate ORDER BY count DESC LIMIT 1")
    Gamestate getGamestateWithHighestCount();
}


