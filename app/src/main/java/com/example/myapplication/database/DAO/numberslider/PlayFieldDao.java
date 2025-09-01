/**
 * Speicherung und abfrage von play_field Tabelle
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
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.myapplication.database.entity.numberslider.play_field;

import java.util.List;

@Dao
public interface PlayFieldDao {

    @Insert
    void insert(play_field field);

    @Update
    void update(play_field field);

    @Delete
    void delete(play_field field);

    @Query("SELECT * FROM play_field")
    List<play_field> getAll();

    @Query("SELECT * FROM play_field WHERE id = :id LIMIT 1")
    play_field getById(String id);

}
