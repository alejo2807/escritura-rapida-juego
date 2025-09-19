package com.example.escriturarapidajuego.model;

/**
 * Interface for providing words and phrases to the typing game.
 * Defines the contract for word providers that supply text content
 * for different game levels and difficulties.
 *
 * @author David
 * @version 1.0
 */
public interface IWordProvider {

    /**
     * Retrieves the next word or phrase from the provider.
     *
     * @return the next word/phrase to be typed, or null if no more words available
     */
    String getNextWord();

    /**
     * Checks if there are more words available in the provider.
     *
     * @return true if more words are available, false otherwise
     */
    boolean hasMoreWords();

}