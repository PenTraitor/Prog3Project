/**
 * Rstellung neuer Grids und errechnung von Spielzügen
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.logic.numberslider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    public int[][] createNewGrid(){
        int[][] grid = new int[4][4];
        grid = placeRandomCells(grid);
        return grid;
    }

    public int[][] placeRandomCells(int[][] grid) {
        int[] options = {1, 2, 4};
        Random rand = new Random();
        int placed = 0;
        int numberOfZeros = countZeros(grid);
        int randomNumber=1;

        List<int[]> freeCells = new ArrayList<>();

        // Alle freien Zellen (Wert 0) sammeln
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    freeCells.add(new int[]{row, col});
                }
            }
        }

        // Wenn keine freie Zelle vorhanden ist, nichts machen
        if (freeCells.isEmpty()) {
            return grid;
        }

        // Zufällige freie Zelle auswählen
        int[] chosenCell = freeCells.get(rand.nextInt(freeCells.size()));

        // Zahl setzen
        grid[chosenCell[0]][chosenCell[1]] = rand.nextInt(options.length);


        return grid;
    }




    private int countZeros(int[][] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public int[][] generateMove(int[][] grid, Direction direction){
        //x >
        //y ^
        switch (direction){
            case UP:
                moveUp(grid);
                break;
            case DOWN:
                moveDown(grid);
                break;
            case LEFT:
                moveLeft(grid);
                break;
            case RIGHT:
                moveRight(grid);
                break;
        }
        return grid;
    }

    private static void moveUp(int[][] grid) {

        for (int col = 0; col < 4; col++) {
            // Step 1: extract column
            int[] column = new int[4];
            for (int row = 0; row < 4; row++) {
                column[row] = grid[row][col];
            }
            // Step 2: process column
            int[] newColumn =  orderArray(column,false);

            // Step 3: put column back
            for (int row = 0; row < 4; row++) {
                grid[row][col] = newColumn[row];
            }
        }
    }
    private static void moveDown(int[][] grid) {
        for (int col = 0; col < 4; col++) {
            // Step 1: extract column
            int[] column = new int[4];
            for (int row = 0; row < 4; row++) {
                column[row] = grid[row][col];
            }
            // Step 2: process column
            int[] newColumn =  orderArray(column,true);

            // Step 3: put column back
            for (int row = 0; row < 4; row++) {
                grid[row][col] = newColumn[row];
            }
        }
    }

    private static void moveLeft(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            grid[i] = orderArray(grid[i],false);
        }
    }

    private static void moveRight(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            grid[i] = orderArray(grid[i],true);
        }
    }

    private static int[] orderArray(int[] values, boolean shouldMoveToStart){
        if (shouldMoveToStart) {
            move0toStart(values);
            mergeToStart(values);
            move0toStart(values);
        }else{
            //move 0 to one side
            move0toEnd(values);
            //merge numbers
            mergeToEnd(values);
            //move 0 to one side again
            move0toEnd(values);
        }
        return values;
    }

    private static void mergeToEnd(int[] values) {
        for (int x = 0; x < 3; x++) {
            if(values[x]== values[x+1]&& values[x]!=0){
                values[x]= values[x]*2;
                values[x+1]= 0;
            }
        }
    }
    private static void mergeToStart(int[] values) {
        for (int x = 3; x > 0; x--) {
            if(values[x]== values[x-1]&& values[x]!=0){
                values[x]= values[x]*2;
                values[x-1]= 0;
            }
        }
    }

    private static void move0toEnd(int[] values) {

        int insertPos = 0;

        // First, move non-zero elements forward
        for (int num : values) {
            if (num != 0) {
                values[insertPos++] = num;
            }
        }

        // Fill the rest with zeros
        while (insertPos < values.length) {
            values[insertPos++] = 0;
        }
    }
    private static void move0toStart(int[] values) {
        int insertPos = values.length - 1;

        // Move non-zero elements to the end
        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] != 0) {
                values[insertPos--] = values[i];
            }
        }

        // Fill the rest with zeros at the beginning
        while (insertPos >= 0) {
            values[insertPos--] = 0;
        }
    }

}
