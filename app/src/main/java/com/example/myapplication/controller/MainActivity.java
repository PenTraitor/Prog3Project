package com.example.myapplication.controller;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.controller.ui.GameFragmentTamagotchi;
import com.example.myapplication.controller.ui.TopBarFragmentTamagotchi;
import com.example.myapplication.model.Tamagotchi;
import android.os.Handler;
import android.os.Looper;


public class MainActivity extends AppCompatActivity implements TamagotchiListener{

    private GameFragmentTamagotchi gameFragment;
    private Tamagotchi myTamagotchi = Tamagotchi.getInstance();
    private Handler handler = new Handler(Looper.getMainLooper());
    private final int SATISFACTION_DECREASE = 5; // Wert pro Intervall
    private final int INTERVAL = 10000; // 10 Sekunden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    public void onHungerChanged(int newValue) {
        if (gameFragment != null) {
            gameFragment.updateEnergy(newValue);
        }
    }

    @Override
    public void onHappinessChanged(int newValue) {
        if (gameFragment != null) {
            gameFragment.updateHappiness(newValue);
        }
    }

    @Override
    public int getHunger() { return myTamagotchi.getSatisfaction(); }

    @Override
    public int getEnergy() { return myTamagotchi.getEnergy(); }

    public void feedTamagotchi() {
        myTamagotchi.feed();
        updateTopBar();
    }

    public void restTamagotchi() {
        myTamagotchi.rest();
        updateTopBar();
    }

    private void updateTopBar() {
        TopBarFragmentTamagotchi topBar = (TopBarFragmentTamagotchi) getSupportFragmentManager()
                .findFragmentById(R.id.topBarFragmentTamagotchi);
        if (topBar != null) topBar.updateProgressBars(this);
    }

}