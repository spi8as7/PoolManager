package com.example.spi8a.poolmanager;

/**
 * Created by spi8a on 4/8/2017.
 */

public class Card {
    private String line1;
    private String line2;

    public Card(String line1, String line2) {
        this.line1 = line1;
        this.line2 = line2;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

}