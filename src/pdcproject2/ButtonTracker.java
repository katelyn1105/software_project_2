/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;

import java.util.*;

/**
 *
 * @author 1708k
 */
//Tracks total button presses - use in score.
public class ButtonTracker {

    public int getCounter() {
        return counter;
    }

    //Override counter
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isCountOn() {
        return countOn;
    }

    //Enable counter.
    public void setCountOn(boolean countOn) {
        this.countOn = countOn;
    }

    //Give score.
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    private int counter = 0;
    private boolean countOn = false;
    private int score = 0;

    //Start Count.
    public void startCount() {
        setCountOn(true);
        setCounter(0);
        setScore(0);
    }

    //Stop counting and return score
    public void stopCount() {
        System.out.println("Current count: " + getCounter());
        setCountOn(false);
        setScore(112 - getCounter());
        System.out.println("Final score: " + getScore());
    }

    //Increment score.
    public void addCount() {
        if (isCountOn()) {
            setCounter(getCounter() + 1);
            System.out.println("Count incremented.");
        }
    }

}
