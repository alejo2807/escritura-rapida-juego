package com.example.escriturarapidajuego.model;

import javafx.scene.control.Label;


public class Game {

    private Player player;
    private IWordProvider wordProvider;
    private GameRules rules;
    private GameTimer timer;
    private int currentLevel;

    // Constructor
    public Game(Label timerLabel) {
        this.player = new Player(0, 0, 0);
        this.wordProvider = new WordProviderImplementation();
        this.rules = new GameRules(wordProvider);
        this.timer = new GameTimer(timerLabel);
        this.currentLevel = 1;
    }

    // Inicia o reinicia la partida
    public void startGame() {
        this.player = new Player(0, 0, 0);  // reinicia estadísticas
        this.wordProvider = new WordProviderImplementation(); // reinicia banco de palabras
        this.rules = new GameRules(wordProvider);
        this.currentLevel = 1;
        timer.startTimer(currentLevel);
    }

    // Valida la respuesta del usuario
    public boolean playTurn(String userAnswer) {
        String expectedWord = wordProvider.getNextWord(); // obtiene palabra desde el proveedor
        boolean correct = rules.validateLevel(expectedWord, userAnswer, player);

        if (correct) {
            currentLevel++;
            timer.startTimer(currentLevel); // reinicia timer con menos tiempo si toca
        } else {
            if(timer.isTimeOver()){
                showResults();
            }
            else{
                //we do nothing, since the user has the opportunity to continue typing
                //while time is not equeal to zero (we dont level up either)
            }
        }
        return correct;
    }

    // Termina la partida
    public void showResults() {
        timer.stopTimer(); // detenemos el tiempo
        System.out.println("Juego terminado. Puntaje final: " + player.getScore());
        System.out.println("Aciertos: " + player.getCorrectLevels());
        System.out.println("Errores: " + player.getIncorrectLevels());

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

