/**
 * Tabelle der Spielfelds
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

import java.util.UUID;

@Entity
public class play_field {
    //play_field will always be 4x4
    @PrimaryKey
    @NonNull
    public String id;
    public String previous_field;
    // top row 1
    public int A1;
    public int A2;
    public int A3;
    public int A4;
    //row 2
    public int B1;
    public int B2;
    public int B3;
    public int B4;
    // row 3
    public int C1;
    public int C2;
    public int C3;
    public int C4;
    //bottom row 1
    public int D1;
    public int D2;
    public int D3;
    public int D4;


    public int[][] toGrid() {
        return new int[][] {
                {A1, A2, A3, A4},
                {B1, B2, B3, B4},
                {C1, C2, C3, C4},
                {D1, D2, D3, D4}
        };
    }

    public void fromGrid(int[][] grid) {
        A1 = grid[0][0]; A2 = grid[0][1]; A3 = grid[0][2]; A4 = grid[0][3];
        B1 = grid[1][0]; B2 = grid[1][1]; B3 = grid[1][2]; B4 = grid[1][3];
        C1 = grid[2][0]; C2 = grid[2][1]; C3 = grid[2][2]; C4 = grid[2][3];
        D1 = grid[3][0]; D2 = grid[3][1]; D3 = grid[3][2]; D4 = grid[3][3];
    }

    public play_field() {
        this.id = UUID.randomUUID().toString();
    }
    public play_field(@androidx.annotation.NonNull String id, String previous_id, int[][] grid ) {
        this.id = id;
        this.previous_field = previous_id;
        fromGrid(grid);
    }

}

