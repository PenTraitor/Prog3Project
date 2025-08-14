package com.example.myapplication.model;

public class tamagotchi {
    int hunger;
    int energie;

    public tamagotchi(){
        this.hunger = 100;
        this.energie = 100;
    }
    public void tictactoeGewonnen(){
        this.hunger+=20;
    }
}
