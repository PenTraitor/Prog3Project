/**
 * Listener zur Kommunikation zwischen View und Model
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.logic.numberslider;

public interface GameUpdateListener {
  void onGridUpdated(int[][] newGrid);
  void onWin(int score);

}
