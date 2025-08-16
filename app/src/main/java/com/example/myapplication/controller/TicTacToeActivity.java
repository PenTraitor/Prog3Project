package com.example.myapplication.controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class TicTacToeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Verknüpft die Activity mit dem Haupt-Layout, das die drei FragmentContainerViews enthält
        setContentView(R.layout.activity_tic_tac_toe);
    }
}
