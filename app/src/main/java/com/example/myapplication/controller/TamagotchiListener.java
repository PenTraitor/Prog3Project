package com.example.myapplication.controller;

public interface TamagotchiListener {
    void onHungerChanged(int newValue);
    void onHappinessChanged(int newValue);
    int getHunger();
    int getEnergy();
}