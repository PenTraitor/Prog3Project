package com.example.myapplication.controller;

import com.example.myapplication.model.Tamagotchi;

public interface TamagotchiObserver {
    void onTamagotchiChanged(Tamagotchi tamagotchi);
}
