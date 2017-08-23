package com.example.spi8a.poolmanager;

import java.util.ArrayList;

/**
 * Created by spi8a on 23/8/2017.
 */

public class Set{
    ArrayList<Move> player1,player2;

    public void setPlayer1(ArrayList<Move> player1) {
        this.player1 = player1;
    }

    public void setPlayer2(ArrayList<Move> player2) {
        this.player2 = player2;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    String winner;
}