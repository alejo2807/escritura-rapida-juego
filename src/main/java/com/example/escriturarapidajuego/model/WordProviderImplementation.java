package com.example.escriturarapidajuego.model;

import java.util.ArrayList;
import java.util.List;

public class WordProviderImplementation implements  IWordProvider{

    //Words Bank
    protected final List<String> wordList = new ArrayList<>();
    private int currentWordIndex = 0;

    //Constructor
    public WordProviderImplementation(){
        // Nivel 1 - palabras muy faciles (cortas y comunes)
        wordList.add("sol");
        wordList.add("casa");
        wordList.add("luz");
        wordList.add("flor");
        wordList.add("paz");

        // Nivel 2 - palabras mas largas
        wordList.add("camino");
        wordList.add("estrella");
        wordList.add("montana");
        wordList.add("jardin");
        wordList.add("escuela");

        // Nivel 3 - frases cortas y simples
        wordList.add("buen dia");
        wordList.add("hola mundo");
        wordList.add("hasta luego");
        wordList.add("te quiero");
        wordList.add("muchas gracias");

        // Nivel 4 - frases un poco mas largas
        wordList.add("el perro ladra");
        wordList.add("la luna brilla");
        wordList.add("tengo un sueno");
        wordList.add("quiero aprender");
        wordList.add("vamos a jugar");

        // Nivel 5 - frases con conectores
        wordList.add("aunque llueva sonrio");
        wordList.add("me gusta el cafe");
        wordList.add("manana sera mejor");
        wordList.add("vivir es aprender");
        wordList.add("la vida es bella");

        // Nivel 6 - frases mas complejas
        wordList.add("el viento sopla fuerte");
        wordList.add("me alegra verte feliz");
        wordList.add("quiero viajar al mar");
        wordList.add("estudio para mejorar");
        wordList.add("la esperanza nunca muere");

        // Nivel 7 - frases largas con puntuacion
        wordList.add("siempre hay un nuevo comienzo");
        wordList.add("la amistad es un tesoro");
        wordList.add("aprendo de mis errores");
        wordList.add("cada dia trae su afan");
        wordList.add("confia en ti mismo");

        // Nivel 8 - frases largas con comas
        wordList.add("la paciencia la fe y la constancia son claves");
        wordList.add("cuando cierro los ojos imagino un mundo mejor");
        wordList.add("el exito llega con esfuerzo y disciplina");
        wordList.add("aunque caigas levantate y sigue adelante");
        wordList.add("los suenos grandes requieren valentia");

        // Nivel 9 - frases mas desafiantes
        wordList.add("la imaginacion es mas poderosa que el conocimiento");
        wordList.add("el verdadero fracaso es no intentarlo nunca");
        wordList.add("cuando todo parece dificil recuerda tu meta");
        wordList.add("quien tiene un porque puede superar cualquier como");
        wordList.add("la perseverancia transforma lo imposible en realidad");


    }

    @Override
    public String getNextWord()
    {
        if (currentWordIndex < wordList.size()) {
            return wordList.get(currentWordIndex++);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasMoreWords() {
        return currentWordIndex < wordList.size();
    }

}
