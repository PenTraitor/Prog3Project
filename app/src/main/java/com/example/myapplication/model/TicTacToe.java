package com.example.myapplication.model;

import java.util.Arrays;

public class TicTacToe {
    private String[] playfield = {"1","2","3","4","5","6","7","8","9"};

    public String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = playfield[0] + playfield[1] + playfield[2];
                    break;
                case 1:
                    line = playfield[3] + playfield[4] + playfield[5];
                    break;
                case 2:
                    line = playfield[6] + playfield[7] + playfield[8];
                    break;
                case 3:
                    line = playfield[0] + playfield[3] + playfield[6];
                    break;
                case 4:
                    line = playfield[1] + playfield[4] + playfield[7];
                    break;
                case 5:
                    line = playfield[2] + playfield[5] + playfield[8];
                    break;
                case 6:
                    line = playfield[0] + playfield[4] + playfield[8];
                    break;
                case 7:
                    line = playfield[2] + playfield[4] + playfield[6];
                    break;
            }

            if (line.equals("XXX")) {
                return "player";
            }

            else if (line.equals("OOO")) {
                return "cpu";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(playfield).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }
        return null;
    }
    public void setMove(int index, String player) {
        playfield[index] = player;
    }
}
