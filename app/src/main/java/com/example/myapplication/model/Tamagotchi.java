package com.example.myapplication.model;

import android.widget.ImageView;

import com.example.myapplication.controller.AnimationStrategy;
import com.example.myapplication.controller.TamagotchiObserver;

import java.util.ArrayList;
import java.util.List;

public class Tamagotchi {
    private int satisfaction;
    private int energy;
    private Mood emotion;
    private final List<TamagotchiObserver> observers = new ArrayList<>();
    private static Tamagotchi instance;
    private AnimationStrategy animationStrategy;


    private Tamagotchi() {
        energy = 100;
        satisfaction = 100;
        this.emotion = Mood.NEUTRAL;
        this.updateEmotion();
    }

    public static Tamagotchi getInstance() {
        if (instance == null) {
            instance = new Tamagotchi();
        }
        return instance;
    }
    public void addObserver(TamagotchiObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(TamagotchiObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (TamagotchiObserver observer : observers) {
            observer.onTamagotchiChanged(this);
        }
    }
    public int getSatisfaction(){
        return this.satisfaction;
    }
    public int getEnergy(){
        return this.energy;
    }
    public Mood getEmotion() {
        return this.emotion;
    }
    public void setSatisfaction(int value) {
        satisfaction = Math.max(0, Math.min(100, value));
        updateEmotion();
        notifyObservers();
    }
    public void setEnergy(int value) {
        energy = Math.max(0, Math.min(100, value));
        updateEmotion();
        notifyObservers();
    }

    public void updateEmotion(){
        if(getSatisfaction() > 75){
            this.emotion = Mood.HAPPY;
        }else if(getSatisfaction() <= 75 && getSatisfaction() >= 50){
            this.emotion = Mood.NEUTRAL;
        }else{
            this.emotion= Mood.SAD;
        }
    }
    public void setAnimationStrategy(AnimationStrategy strategy) {
        this.animationStrategy = strategy;
    }

    public void startIdleAnimation(ImageView spriteView) {
        if (animationStrategy != null) {
            animationStrategy.startAnimation(spriteView);
        }
    }

}
