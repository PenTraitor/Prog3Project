package com.example.myapplication.controller.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.model.mood;
import com.example.myapplication.model.tamagotchi;

public class GameFragmentTamagotchi extends Fragment {

    private ImageView spriteView;
    private tamagotchi myTamagotchi;

    public GameFragmentTamagotchi() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_tamagotchi, container, false);
        spriteView = view.findViewById(R.id.tamagotchiSprite);
        myTamagotchi = new tamagotchi();
        myTamagotchi.updateEmotion();
        startIdleAnimation(myTamagotchi.getEmotion());
        return view;
    }

    public void updateHunger(int value) {
        myTamagotchi.setSatisfaction(value);
        myTamagotchi.updateEmotion();
        startIdleAnimation(myTamagotchi.getEmotion());
    }

    public void updateHappiness(int value) {
        myTamagotchi.setEnergy(value);
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
