package com.example.escriturarapidajuego.model;

public class Player  implements IPlayer{

    private int score;
    private int correctLevels;
    private int incorrectLevels;

    public Player(int score, int correctLevels, int incorrectLevels){
        this.score = 0;
        this.correctLevels = 0;
        this.incorrectLevels = 0;
    }


    public int getScore() {
        return score;
    }

    public int getCorrectLevels() {
        return correctLevels;
    }

    public int getIncorrectLevels() {
        return incorrectLevels;
    }

    public void increaseCorrectLevel(){
        correctLevels++;
        //we will increase by one the current score
        increaseScore(1);
    }

    public void increaseIncorrectLevel(){
        incorrectLevels++;
        //we will decrease by one the current score
        decreaseScore(1);
    }


    @Override
    public void increaseScore(int amount) {
        score+=amount;
    }

    @Override
    public void decreaseScore(int amount) {
        if (score > 0)  score -=amount;
        else score =0;
    }
}


