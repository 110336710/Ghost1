package com.example.ilse.ghost;

import android.util.Log;

import java.util.Random;

/**
 * Created by Ilse on 12-10-2015.
 */
public class Game {
    Lexicon lexicon;
    boolean isTurn, isEnded;
    String player1,player2,playersTurn,isWinner;
    private final Random random = new Random();

    public Game(Lexicon lexicon, String player1, String player2){
        this.lexicon = lexicon;
        this.player1 = player1;
        this.player2 = player2;

    }
    public void guess(String wordguess){
        lexicon.filter(wordguess);

        if (lexicon.count() == 0){
            Log.d("GUESS","No word like that");
            ended();
        }
        else if (lexicon.count() == 1){
            if (lexicon.result().length() > 3){
                Log.d("GAME","you win!!"+playersTurn);
                winner();
            }
            else {
                Log.d("GAME","No win!");
            }
        }

    }
    public String turnStart(){
        String[] players = {player1,player2};
        playersTurn = players[random.nextInt(2)];
        return playersTurn;
    }
    public String turn(){
        if (playersTurn == player1){
            playersTurn = player2;
        }
        else {
            playersTurn = player1;
        }
        return playersTurn;
    }

    public boolean ended(){
        Log.d("GAME","Game ended!");
        return isEnded;
    }
    public String winner(){
        if (ended()){
            isWinner =  playersTurn;
        }
        Log.d("GAME","winnaar is: " + playersTurn);
        return isWinner;

    }
}

