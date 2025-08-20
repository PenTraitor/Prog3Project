package com.example.myapplication.controller.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.myapplication.R;
import com.example.myapplication.model.mood;
import com.example.myapplication.model.Tamagotchi;

public class GameFragmentTamagotchi extends Fragment {
    private ImageView spriteView;
    private Tamagotchi myTamagotchi;
    private final Handler handler = new Handler();
    private final int DECREASE_INTERVAL = 10000;
    private final int DECREASE_AMOUNT = 5;

    public GameFragmentTamagotchi() {}
    private Runnable decreaseSatisfactionRunnable = new Runnable() {
        @Override
        public void run() {
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
        myTamagotchi.updateEmotion();
        startIdleAnimation(myTamagotchi.getEmotion());
        return view;
    }

    public void updateEnergy(int value) {
        myTamagotchi.setEnergy(value);
        myTamagotchi.updateEmotion();
        startIdleAnimation(myTamagotchi.getEmotion());
    }

    public void updateHappiness(int value) {
        myTamagotchi.setSatisfaction(value);
        myTamagotchi.updateEmotion();
        startIdleAnimation(myTamagotchi.getEmotion());
    }

    private void startIdleAnimation(mood currentMood) {
        int animRes;
        switch (currentMood) {
            case HAPPY:
                animRes = R.drawable.anim_happy;
                break;
            case SAD:
                animRes = R.drawable.anim_sad;
                break;
            default:
                animRes = R.drawable.anim_neutral;
                break;
        }
        spriteView.setImageResource(animRes);
        spriteView.post(() -> {
            AnimationDrawable animation = (AnimationDrawable) spriteView.getDrawable();
            if (animation != null) {
                animation.start();
            }
        });
    }

}
