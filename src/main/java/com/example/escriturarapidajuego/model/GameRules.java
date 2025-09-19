package com.example.escriturarapidajuego.model;

/**
 * Manages game rules and validation logic for the typing game.
 * Handles answer validation, level progression, and player scoring.
 * Serves as the core rule engine for the game mechanics.
 *
 * @author David
 * @version 1.0
 */
public class GameRules {

    private final IWordProvider wordProvider;
    private String userAnswer;

    /**
     * Constructs GameRules with a word provider.
     *
     * @param wordProvider the provider of words and phrases for validation
     */
    public GameRules(IWordProvider wordProvider) {
        this.wordProvider = wordProvider;
    }

    /**
     * Validates if the user's answer matches the expected answer exactly.
     * Uses case-sensitive string comparison.
     *
     * @param expectedAnswer the correct word/phrase to match against
     * @param userAnswer the user's input to validate
     * @return true if answers match exactly, false otherwise
     */
    private boolean validateAnswer(String expectedAnswer, String userAnswer) {
        return expectedAnswer.equals(userAnswer);
    }

    /**
     * Validates a level completion and updates player statistics accordingly.
     * Awards points for correct answers and penalizes for incorrect ones.
     *
     * @param expectedAnswer the correct word/phrase for the current level
     * @param userAnswer the user's input to validate
     * @param player the player whose statistics will be updated
     * @return true if level was completed correctly, false otherwise
     */
    public boolean validateLevel(String expectedAnswer, String userAnswer, Player player) {
        if (validateAnswer(expectedAnswer, userAnswer)) {
            player.increaseCorrectLevel(); // Reward correct answer
            return true;
        } else {
            player.increaseIncorrectLevel(); // Penalize incorrect answer
            return false;
        }
    }

    /**
     * Gets the user's last submitted answer.
     *
     * @return the user's most recent answer
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * Retrieves the next word from the word provider.
     *
     * @return the next word/phrase for the following level
     */
    public String nextWord() {
        return wordProvider.getNextWord();
    }

    /**
     * Checks if there are more levels available in the word provider.
     *
     * @return true if more levels are available, false otherwise
     */
    public boolean hasMoreLevels() {
        return wordProvider.hasMoreWords();
    }
}