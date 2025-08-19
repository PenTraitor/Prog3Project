package com.example.myapplication.model;

public class tamagotchi {
    int satisfaction;
    int energy;
    mood emotion;

    public tamagotchi(){
        this.satisfaction = 100;
        this.energy = 100;
        this.emotion = mood.NEUTRAL;
    }
    public int getSatisfaction(){
        return this.satisfaction;
    }
    public int getEnergy(){
        return this.energy;
    }
    public mood getEmotion() {
        return this.emotion;
    }
    public void setSatisfaction(int hunger){
        this.satisfaction = hunger;
        updateEmotion();
    }
    public void setEnergy(int energie){
        this.energy = energie;
        updateEmotion();
    }

    public void feed(){
        this.satisfaction += 10;
        updateEmotion();
    }
    public void rest(){
        this.energy += 10;
        updateEmotion();
    }
    public void updateEmotion(){
        if(getSatisfaction() > 75){
            this.emotion = mood.HAPPY;
        }else if(getSatisfaction() <= 75 && getSatisfaction() >= 50){
            this.emotion = mood.NEUTRAL;
        }else{
            this.emotion= mood.SAD;
        }
    }
}
