package com.example.escriturarapidajuego.model;

public class GameRules {

    private String userAnswer;





    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isAnswerCorrect(){
        if(getUserAnswer().equals("aqui tendria que poner la forma de leer de la gui")){
            return true;
        }

        return false;

    }


}
