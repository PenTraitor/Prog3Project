/* Code wurde inspiriert von https://www.geeksforgeeks.org/java/tic-tac-toe-game-in-java/  */
package com.example.myapplication.model;
import java.util.Arrays;

public class TicTacToe {
    private final String[] playfield = {"1","2","3","4","5","6","7","8","9"};

    public String checkWinner() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> playfield[0] + playfield[1] + playfield[2];
                case 1 -> playfield[3] + playfield[4] + playfield[5];
                case 2 -> playfield[6] + playfield[7] + playfield[8];
                case 3 -> playfield[0] + playfield[3] + playfield[6];
                case 4 -> playfield[1] + playfield[4] + playfield[7];
                case 5 -> playfield[2] + playfield[5] + playfield[8];
                case 6 -> playfield[0] + playfield[4] + playfield[8];
                case 7 -> playfield[2] + playfield[4] + playfield[6];
                default -> null;
            };

            if (line.equals("XXX")) {
                return "player";
            }

            else if (line.equals("OOO")) {
                return "cpu";
            }
        }

        for (int j = 0; j < 9; j++) {
            if (Arrays.asList(playfield).contains(String.valueOf(j + 1))) {
                break;
            } else if (j == 8) {
                return "draw";
            }
        }
        return null;
    }
    public void setMove(int index, String player) {
        playfield[index] = player;
    }
}
