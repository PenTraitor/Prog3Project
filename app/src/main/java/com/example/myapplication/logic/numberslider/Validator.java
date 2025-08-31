package com.example.myapplication.logic.numberslider;

public class Validator {
    public boolean gameover(int[][] grid){
        return move_possible(grid, Direction.UP) || move_possible(grid, Direction.LEFT);
    }

    public boolean move_possible(int[][] grid, Direction direction ){
        //up/down and left/right are identical logically
        boolean rvalue = true;
        switch (direction) {
            case UP:
            case DOWN:
                //first 3 rows all columns
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 4; col++) {
                        if (!is_valid(grid[row][col], grid[row + 1][col])) {
                            rvalue = false;
                            break;
                        }
                    }
                }
                break;
            case LEFT:
            case RIGHT:
                //first 3 columns all rows
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 3; col++) {
                        if (!is_valid(grid[row][col], grid[row][col+1])) {
                            rvalue = false;
                            break;
                        }
                    }
                }
                break;
        }

        return rvalue;
    }

    private boolean is_valid(int v1, int v2){
        return v1 == 0 || v2 == 0 || v1 == v2;
    }
}