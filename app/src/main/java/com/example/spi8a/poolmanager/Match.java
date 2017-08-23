package com.example.spi8a.poolmanager;

import java.util.ArrayList;

/**
 * Created by spi8a on 3/8/2017.
 */


public class Match {
    public ArrayList<Set> getSetType() {
        return setType;
    }
    public void addSet (Set tempSet) {
        setType.add(tempSet);
    }

    public void setSetType(ArrayList<Set> setType) {
        this.setType = setType;
    }

    public String getPlayer1() {

        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String player1,player2,place,date,time;
    ArrayList<Set> setType;



    public void setPlayer1(String name) {
        this.player1 = name;
    }
    public void setPlayer2(String name) {
        this.player2 = name;
    }
    public void setPlace(String name)  {this.place = name;}
    public void setDate(String name) {
        this.date = name;
    }
    public void setTime(String name) {
        this.time = name;
    }
}
