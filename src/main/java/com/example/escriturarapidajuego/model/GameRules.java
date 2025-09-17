package com.example.escriturarapidajuego.model;


public class GameRules{

    private final IWordProvider wordProvider;
    private String userAnswer;

    public GameRules(IWordProvider wordProvider) {
        this.wordProvider = wordProvider;
    }



    private boolean validateAnswer(String expectedAnswer, String userAnswer)
    {
          return expectedAnswer.equals(userAnswer);
    }

    public boolean validateLevel(String expectedAnswer, String userAnswer, Player player){
        if(validateAnswer(expectedAnswer,userAnswer)){
            player.increaseCorrectLevel();
            return true;
        }
        else{
            player.increaseIncorrectLevel();
            return false;
        }
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public String nextWord() {
        return wordProvider.getNextWord();
    }

    public boolean hasMoreLevels() {
        return wordProvider.hasMoreWords();
    }
}
