package com.example.myapplication.controller.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.model.TicTacToe;
import com.example.myapplication.model.Tamagotchi;
import java.util.Random;



public class GameFragmentTicTacToe extends Fragment{
    public GameFragmentTicTacToe() {}

    private TicTacToe game;
    private Button[] buttons = new Button[9];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_tictactoe, container, false);

        game = new TicTacToe();

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("btn" + (i + 1), "id", getContext().getPackageName());
            buttons[i] = view.findViewById(resId);

            final int index = i;
            buttons[i].setOnClickListener(v -> handlePlayerMove(index));
        }

        return view;
    }

    private void handlePlayerMove(int index) {
        if (buttons[index].getText().toString().isEmpty()) {
            buttons[index].setText("X");
            game.setMove(index, "X");

            String result = game.checkWinner();
            if (checkGameOver(result)) return;

            handleCpuMove();
        }
    }

    private void handleCpuMove() {
        Random rand = new Random();
        int move;
        do {
            move = rand.nextInt(9);
        } while (!buttons[move].getText().toString().isEmpty());

        buttons[move].setText("O");
        game.setMove(move, "O");

        String result = game.checkWinner();
        checkGameOver(result);
    }

    private boolean checkGameOver(String result) {
        if (result != null) {
            String message;
            switch (result) {
                case "player": message = "Du hast gewonnen!"; break;
                case "cpu": message = "CPU hat gewonnen!"; break;
                case "draw": message = "Unentschieden!"; break;
                default: message = "";
            }
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            resetBoard();
            if(result.equals("player")){
                Tamagotchi tamagotchi = Tamagotchi.getInstance();
                tamagotchi.setEnergy(tamagotchi.getEnergy() - 10);
                tamagotchi.setSatisfaction(tamagotchi.getSatisfaction() + 10);
            }
            return true;
        }
        return false;
    }

    private void resetBoard() {
        game = new TicTacToe();
        for (Button b : buttons) {
            b.setText("");
        }
    }
}