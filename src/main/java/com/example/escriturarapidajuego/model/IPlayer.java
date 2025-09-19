package com.example.escriturarapidajuego.model;

/**
 * Interface representing a player in the typing game.
 * Defines the contract for player score management and
 * progress tracking throughout the game sessions.
 *
 * @author David
 * @version 1.0
 */
public interface IPlayer {

    /**
     * Increases the player's score by the specified amount.
     *
     * @param amount the number of points to add to the current score
     */
    void increaseScore(int amount);

    /**
     * Decreases the player's score by the specified amount.
     * The score will never go below zero.
     *
     * @param amount the number of points to subtract from the current score
     */
    void decreaseScore(int amount);

}