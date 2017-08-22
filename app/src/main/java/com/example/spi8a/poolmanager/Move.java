package com.example.spi8a.poolmanager;

/**
 * Created by spi8a on 4/8/2017.
 */

public class Move {
    public int number;
    public MoveCompletion move;
    public ShotType shotype;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMove(MoveCompletion move) {
        this.move = move;
    }

    public void setShotype(ShotType shotype) {
        this.shotype = shotype;
    }
}
