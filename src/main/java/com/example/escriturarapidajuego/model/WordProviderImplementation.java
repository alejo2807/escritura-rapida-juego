package com.example.escriturarapidajuego.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IWordProvider that provides Spanish words and phrases
 * for different difficulty levels in the typing game.
 * Contains a predefined word bank organized by increasing complexity.
 *
 * @author David
 * @version 1.0
 */
public class WordProviderImplementation implements IWordProvider {

    // Word bank containing all words and phrases for the game
    protected final List<String> wordList = new ArrayList<>();
    private int currentWordIndex = 0;

    /**
     * Constructs a word provider and initializes the word bank
     * with Spanish words and phrases categorized by difficulty level.
     */
    public WordProviderImplementation() {
        // Level 1 - very easy words (short and common)
        wordList.add("sol");
        wordList.add("casa");
        wordList.add("luz");
        wordList.add("flor");
        wordList.add("paz");

        // Level 2 - longer words
        wordList.add("camino");
        wordList.add("estrella");
        wordList.add("montana");
        wordList.add("jardin");
        wordList.add("escuela");

        // Level 3 - short and simple phrases
        wordList.add("buen dia");
        wordList.add("hola mundo");
        wordList.add("hasta luego");
        wordList.add("te quiero");
        wordList.add("muchas gracias");

        // Level 4 - slightly longer phrases
        wordList.add("el perro ladra");
        wordList.add("la luna brilla");
        wordList.add("tengo un sueno");
        wordList.add("quiero aprender");
        wordList.add("vamos a jugar");

        // Level 5 - phrases with connectors
        wordList.add("aunque llueva sonrio");
        wordList.add("me gusta el cafe");
        wordList.add("manana sera mejor");
        wordList.add("vivir es aprender");
        wordList.add("la vida es bella");

        // Level 6 - more complex phrases
        wordList.add("el viento sopla fuerte");
        wordList.add("me alegra verte feliz");
        wordList.add("quiero viajar al mar");
        wordList.add("estudio para mejorar");
        wordList.add("la esperanza nunca muere");

        // Level 7 - longer phrases with punctuation
        wordList.add("siempre hay un nuevo comienzo");
        wordList.add("la amistad es un tesoro");
        wordList.add("aprendo de mis errores");
        wordList.add("cada dia trae su afan");
        wordList.add("confia en ti mismo");

        // Level 8 - long phrases with commas
        wordList.add("la paciencia la fe y la constancia son claves");
        wordList.add("cuando cierro los ojos imagino un mundo mejor");
        wordList.add("el exito llega con esfuerzo y disciplina");
        wordList.add("aunque caigas levantate y sigue adelante");
        wordList.add("los suenos grandes requieren valentia");

        // Level 9 - challenging phrases
        wordList.add("la imaginacion es mas poderosa que el conocimiento");
        wordList.add("el verdadero fracaso es no intentarlo nunca");
        wordList.add("cuando todo parece dificil recuerda tu meta");
        wordList.add("quien tiene un porque puede superar cualquier como");
        wordList.add("la perseverancia transforma lo imposible en realidad");
    }

    /**
     * Retrieves the next word from the word bank in sequential order.
     *
     * @return the next word/phrase to be typed, or null if no more words available
     */
    @Override
    public String getNextWord() {
        if (currentWordIndex < wordList.size()) {
            return wordList.get(currentWordIndex++);
        } else {
            return null;
        }
    }

    /**
     * Checks if there are more words available in the word bank.
     *
     * @return true if more words are available, false otherwise
     */
    @Override
    public boolean hasMoreWords() {
        return currentWordIndex < wordList.size();
    }
}