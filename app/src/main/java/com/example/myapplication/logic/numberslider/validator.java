/**
 * Prüfung ob Spielzüge möglich sind zur festellung von Gameovers.
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.logic.numberslider;

public class validator {
    public boolean isPlayable(int[][] grid) {
        // 1. Prüfe auf leere Zellen
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    return true;
                }
            }
        }

        // 2. Prüfe auf mögliche Kombinationen (rechts oder unten)
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int current = grid[row][col];

                // Nach rechts prüfen
                if (col + 1 < grid[row].length && current == grid[row][col + 1]) {
                    return true;
                }

                // Nach unten prüfen
                if (row + 1 < grid.length && current == grid[row + 1][col]) {
                    return true;
                }
            }
        }

        // Kein Zug mehr möglich
        return false;
    }

}