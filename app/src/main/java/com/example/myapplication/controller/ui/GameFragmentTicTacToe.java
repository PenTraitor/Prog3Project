package com.example.myapplication.controller.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

public class GameFragmentTicTacToe extends Fragment {
    public GameFragmentTicTacToe() {}

    private TicTacToe game;
    private final Button[] buttons = new Button[9];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_tictactoe, container, false);

        game = new TicTacToe();

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("btn" + (i + 1), "id", requireContext().getPackageName());
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

            // Alle freien Buttons blockieren
            setAllButtonsEnabled(false);

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                handleCpuMove();
                // Nach CPU-Zug wieder freigeben
                setAllButtonsEnabled(true);
            }, 750);
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
            String message = switch (result) {
                case "player" -> "Du hast gewonnen!";
                case "cpu" -> "CPU hat gewonnen!";
                case "draw" -> "Unentschieden!";
                default -> "";
            };
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            resetBoard();
            if (result.equals("player")) {
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
            b.setEnabled(true);
        }
    }

    private void setAllButtonsEnabled(boolean enabled) {
        for (Button b : buttons) {
            if (b.getText().toString().isEmpty()) {
                b.setEnabled(enabled);
            }
        }
    }
}
