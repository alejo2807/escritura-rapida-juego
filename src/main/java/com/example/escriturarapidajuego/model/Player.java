package com.example.escriturarapidajuego.model;

/**
 * Represents a player in the typing game with score tracking
 * and level completion statistics. Implements the IPlayer interface.
 *
 * @author David
 * @version 1.0
 */
public class Player implements IPlayer {

    private int score;
    private int correctLevels;
    private int incorrectLevels;

    /**
     * Constructs a new player with initial zero values for all statistics.
     *
     * @param score initial score (ignored, always starts at 0)
     * @param correctLevels initial correct levels count (ignored, always starts at 0)
     * @param incorrectLevels initial incorrect levels count (ignored, always starts at 0)
     */
    public Player(int score, int correctLevels, int incorrectLevels) {
        this.score = 0;
        this.correctLevels = 0;
        this.incorrectLevels = 0;
    }

    /**
     * Gets the current score of the player.
     *
     * @return the player's current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the number of correctly completed levels.
     *
     * @return count of correct levels
     */
    public int getCorrectLevels() {
        return correctLevels;
    }

    /**
     * Gets the number of incorrectly completed levels.
     *
     * @return count of incorrect levels
     */
    public int getIncorrectLevels() {
        return incorrectLevels;
    }

    /**
     * Increases the count of correctly completed levels and adds 1 point to the score.
     */
    public void increaseCorrectLevel() {
        correctLevels++;
        increaseScore(1); // Add 1 point for correct answer
    }

    /**
     * Increases the count of incorrectly completed levels and subtracts 1 point from the score.
     */
    public void increaseIncorrectLevel() {
        incorrectLevels++;
        decreaseScore(1); // Subtract 1 point for incorrect answer
    }

    /**
     * Increases the player's score by the specified amount.
     *
     * @param amount the number of points to add to the score
     */
    @Override
    public void increaseScore(int amount) {
        score += amount;
    }

    /**
     * Decreases the player's score by the specified amount.
     * Ensures score never goes below zero.
     *
     * @param amount the number of points to subtract from the score
     */
    @Override
    public void decreaseScore(int amount) {
        if (score > 0) {
            score -= amount;
        } else {
            score = 0; // Ensure score never becomes negative
        }
    }
}