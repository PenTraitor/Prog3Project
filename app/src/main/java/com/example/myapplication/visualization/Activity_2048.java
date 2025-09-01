/**
 * Darstellung des 2048 Minispiels
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.visualization;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.logic.numberslider.Direction;
import com.example.myapplication.logic.numberslider.GameUpdateListener;
import com.example.myapplication.logic.numberslider.game2048;

public class Activity_2048 extends AppCompatActivity implements GameUpdateListener {

    TextView[][] gridCells = new TextView[4][4];
    private game2048 game;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);

        // Initialize gridCells from layout
        gridCells[0][0] = findViewById(R.id.cell_A_1);
        gridCells[0][1] = findViewById(R.id.cell_A_2);
        gridCells[0][2] = findViewById(R.id.cell_A_3);
        gridCells[0][3] = findViewById(R.id.cell_A_4);

        gridCells[1][0] = findViewById(R.id.cell_B_1);
        gridCells[1][1] = findViewById(R.id.cell_B_2);
        gridCells[1][2] = findViewById(R.id.cell_B_3);
        gridCells[1][3] = findViewById(R.id.cell_B_4);

        gridCells[2][0] = findViewById(R.id.cell_C_1);
        gridCells[2][1] = findViewById(R.id.cell_C_2);
        gridCells[2][2] = findViewById(R.id.cell_C_3);
        gridCells[2][3] = findViewById(R.id.cell_C_4);

        gridCells[3][0] = findViewById(R.id.cell_D_1);
        gridCells[3][1] = findViewById(R.id.cell_D_2);
        gridCells[3][2] = findViewById(R.id.cell_D_3);
        gridCells[3][3] = findViewById(R.id.cell_D_4);




        game = new game2048(this);
        game.setListener(this);
        game.Load();

        Button restartButton = findViewById(R.id.topRightButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.Start(); // Spiel neu starten
            }
        });

        DirectionSwipeDetector swipeDetector = new DirectionSwipeDetector() {
            @Override
            public void onSwipeRight() {
                Log.d("MovementDir", "Right");
                game.NewMove(Direction.RIGHT);
            }

            @Override
            public void onSwipeLeft() {
                Log.d("MovementDir", "Left");
                game.NewMove(Direction.LEFT);
            }

            @Override
            public void onSwipeUp() {
                Log.d("MovementDir", "Up");
                game.NewMove(Direction.UP);
            }

            @Override
            public void onSwipeDown() {
                Log.d("MovementDir", "Down");
                game.NewMove(Direction.DOWN);
            }
        };
        GestureDetector gesturedetector = new GestureDetector(this, swipeDetector);

        // Register the listener on your gridLayout (or other view)

        findViewById(R.id.RootLayout).setOnTouchListener(new View.OnTouchListener() {


            @Override

            public boolean onTouch(View v, MotionEvent event) {

                gesturedetector.onTouchEvent(event);

                return true;

            }

        });

    }


    private void updateView(int[][] gridValues) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int value = gridValues[row][col];
                TextView cell = gridCells[row][col];
                if (value == 0) {
                    cell.setText("");
                } else {
                    cell.setText(String.valueOf(value));
                }

                int backgroundColor = getTileColor(value);
                cell.setBackgroundColor(backgroundColor);
            }
        }
    }


    @Override
    public void onGridUpdated(int[][] newGrid) {
        updateView(newGrid);
    }

    @Override
    public void onWin(int score) {
       // Toast.makeText(this, "You win! Score: " + score, Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Gewonnen!");
        builder.setMessage("You win! Score: " + score);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.setCancelable(false);
        builder.show();
    }

    private int getTileColor(int value) {
        Context context = gridCells[0][0].getContext();

        switch (value) {
            case 0: return ContextCompat.getColor(context, R.color.tile_0);
            case 1: return ContextCompat.getColor(context, R.color.tile_1);
            case 2: return ContextCompat.getColor(context, R.color.tile_2);
            case 4: return ContextCompat.getColor(context, R.color.tile_4);
            case 8: return ContextCompat.getColor(context, R.color.tile_8);
            case 16: return ContextCompat.getColor(context, R.color.tile_16);
            case 32: return ContextCompat.getColor(context, R.color.tile_32);
            case 64: return ContextCompat.getColor(context, R.color.tile_64);
            case 128: return ContextCompat.getColor(context, R.color.tile_128);
            case 256: return ContextCompat.getColor(context, R.color.tile_256);
            case 512: return ContextCompat.getColor(context, R.color.tile_512);
            case 1024: return ContextCompat.getColor(context, R.color.tile_1024);
            case 2048: return ContextCompat.getColor(context, R.color.tile_2048);
            case 4096: return ContextCompat.getColor(context, R.color.tile_4096);
            case 8192: return ContextCompat.getColor(context, R.color.tile_8192);
            case 16384: return ContextCompat.getColor(context, R.color.tile_16384);
            case 32768: return ContextCompat.getColor(context, R.color.tile_32768);
            default: return Color.BLACK;
        }
    }

}
