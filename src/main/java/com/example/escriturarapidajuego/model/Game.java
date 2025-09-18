package com.example.escriturarapidajuego.model;

import com.example.escriturarapidajuego.controller.GameController;
import javafx.scene.control.Label;


public class Game {

    private Player player;
    private IWordProvider wordProvider;
    private GameRules rules;
    private GameTimer timer;
    private int currentLevel;
    private String currentWord;

    // Constructor
    public Game(Label timerLabel) {
        this.player = new Player(0, 0, 0);
        this.wordProvider = new WordProviderImplementation();
        this.rules = new GameRules(wordProvider);
        this.timer = new GameTimer(timerLabel);
        this.currentLevel = 1;


        //Getting the first word of the list
        this.currentWord = wordProvider.getNextWord();

        //Each time we create a Game object, it would start the timer in the first level
        timer.startTimer(currentLevel);
    }

    //New constructor with 2 parameters
    public Game(Label timerLabel, GameController gameController){

        this.player = new Player(0, 0, 0);
        this.wordProvider = new WordProviderImplementation();
        this.rules = new GameRules(wordProvider);
        this.timer = new GameTimer(timerLabel, gameController);
        this.currentLevel = 1;

        this.currentWord = wordProvider.getNextWord();
        timer.startTimer(currentLevel);

    }


    // Valida la respuesta del usuario
    public boolean playTurn(String userAnswer) {
        if (timer.isTimeOver()) {
            showResults();
            return false;
        }
        boolean correct = rules.validateLevel(currentWord, userAnswer, player);
        if (correct) {
            currentLevel++;
            if(wordProvider.hasMoreWords())
            {
                currentWord = wordProvider.getNextWord();
                timer.startTimer(currentLevel); // reinicia timer con menos tiempo si toca
            }
            else showResults();
        }

        //If it's not correct, we dont level up. Instaed, we keep playing
        return correct;
    }

    public boolean isGameOver() {
        return !wordProvider.hasMoreWords() || timer.isTimeOver();
    }

    // al terminar la partida
    public void showResults() {
        timer.stopTimer(); // detenemos el tiempo
        System.out.println("Juego terminado");
        System.out.println("Aciertos: " + player.getCorrectLevels());
        System.out.println("Errores: " + player.getIncorrectLevels());
        System.out.println("Puntaje final: " + player.getScore());


    }

    public String getCurrentWord() {
        return currentWord;
    }

    // Getters útiles para el controlador
    public Player getPlayer() {
        return player;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public GameTimer getTimer() {
        return timer;
    }

    public GameRules getRules() {
        return rules;
    }
}

