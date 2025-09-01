package com.example.myapplication.controller.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.controller.TamagotchiObserver;
import com.example.myapplication.controller.HappyAnimation;
import com.example.myapplication.controller.SadAnimation;
import com.example.myapplication.controller.NeutralAnimation;
import com.example.myapplication.model.Tamagotchi;

public class GameFragmentTamagotchi extends Fragment implements TamagotchiObserver {
    private ImageView spriteView;
    private Tamagotchi myTamagotchi;
    private final Handler handler = new Handler();
    private final int DECREASE_INTERVAL = 10000;

    public GameFragmentTamagotchi() {}

    private final Runnable decreaseSatisfactionRunnable = new Runnable() {
        @Override
        public void run() {
            int DECREASE_AMOUNT = 5;
            int newSatisfaction = myTamagotchi.getSatisfaction() - DECREASE_AMOUNT;
            myTamagotchi.setSatisfaction(Math.max(newSatisfaction, 0));
            updateEnergy(myTamagotchi.getEnergy());
            handler.postDelayed(this, DECREASE_INTERVAL);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(decreaseSatisfactionRunnable, DECREASE_INTERVAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(decreaseSatisfactionRunnable);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_tamagotchi, container, false);
        spriteView = view.findViewById(R.id.tamagotchiSprite);

        myTamagotchi = Tamagotchi.getInstance();
        Tamagotchi.getInstance().addObserver(this);

        updateSprite(); // direkt beim Start Animation setzen
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Tamagotchi.getInstance().removeObserver(this);
    }

    public void updateEnergy(int value) {
        myTamagotchi.setEnergy(value);
        updateSprite();
    }

    private void updateSprite() {
        Tamagotchi tamagotchi = Tamagotchi.getInstance();

        // Strategy auswählen
        if (tamagotchi.getSatisfaction() > 70) {
            tamagotchi.setAnimationStrategy(new HappyAnimation());
        } else if (tamagotchi.getSatisfaction() < 30) {
            tamagotchi.setAnimationStrategy(new SadAnimation());
        } else {
            tamagotchi.setAnimationStrategy(new NeutralAnimation());
        }

        // Strategy ausführen
        tamagotchi.startIdleAnimation(spriteView);
    }

    @Override
    public void onTamagotchiChanged(Tamagotchi tamagotchi) {
        updateSprite();
    }
}
