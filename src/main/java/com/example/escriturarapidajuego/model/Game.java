package com.example.escriturarapidajuego.model;

import com.example.escriturarapidajuego.controller.GameController;
import javafx.scene.control.Label;

/**
 * Main game class that manages the game state, player, and game flow.
 * Handles level progression, timing, user input validation, and game session management.
 * Serves as the central coordinator between all game components.
 *
 * @author David
 * @version 1.0
 */
public class Game {

    private Player player;
    private IWordProvider wordProvider;
    private GameRules rules;
    private GameTimer timer;
    private int currentLevel;
    private String currentWord;

    /**
     * Constructs a new Game with a timer display label and game controller reference.
     * Allows the timer to communicate with the game controller for time expiration events.
     *
     * @param timerLabel the label to display timer information
     * @param gameController the game controller for timer callbacks
     */
    public Game(Label timerLabel, GameController gameController) {
        this.player = new Player(0, 0, 0);
        this.wordProvider = new WordProviderImplementation();
        this.rules = new GameRules(wordProvider);
        this.timer = new GameTimer(timerLabel, gameController);
        this.currentLevel = 1;

        this.currentWord = wordProvider.getNextWord();
        timer.startTimer(currentLevel);
    }

    /**
     * Processes a player's turn with their answer submission.
     * Validates the answer, updates game state, and handles level progression.
     *
     * @param userAnswer the player's input to validate
     * @return true if the answer was correct, false otherwise
     */
    public boolean playTurn(String userAnswer) {
        // Check if time has expired before processing the answer
        if (timer.isTimeOver()) {
            showResults();
            return false;
        }

        // Validate the answer against the current word
        boolean correct = rules.validateLevel(currentWord, userAnswer, player);

        if (correct) {
            currentLevel++;

            // Check if there are more words available
            if (wordProvider.hasMoreWords()) {
                currentWord = wordProvider.getNextWord();
                timer.startTimer(currentLevel); // Restart timer with adjusted time
            } else {
                showResults(); // End game if no more words
            }
        }

        // If incorrect, remain on the same level for retry
        return correct;
    }

    /**
     * Checks if the game is over.
     * Game ends when either no more words are available or time has expired.
     *
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        return !wordProvider.hasMoreWords() || timer.isTimeOver();
    }

    /**
     * Displays the game results and stops the timer.
     * Outputs final statistics to the console.
     */
    public void showResults() {
        timer.stopTimer(); // Stop the timer

        System.out.println("Juego terminado");
        System.out.println("Aciertos: " + player.getCorrectLevels());
        System.out.println("Errores: " + player.getIncorrectLevels());
        System.out.println("Puntaje final: " + player.getScore());
    }

    /**
     * Gets the current word/phrase to be typed.
     *
     * @return the current word/phrase
     */
    public String getCurrentWord() {
        return currentWord;
    }

    /**
     * Gets the player object with current statistics.
     *
     * @return the player instance
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the current level number.
     *
     * @return the current level number
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Gets the game timer instance.
     *
     * @return the game timer
     */
    public GameTimer getTimer() {
        return timer;
    }

    /**
     * Gets the game rules instance.
     *
     * @return the game rules
     */
    public GameRules getRules() {
        return rules;
    }
}