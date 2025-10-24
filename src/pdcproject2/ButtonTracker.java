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
public class ButtonTracker {

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * @return the countOn
     */
    public boolean isCountOn() {
        return countOn;
    }

    /**
     * @param countOn the countOn to set
     */
    public void setCountOn(boolean countOn) {
        this.countOn = countOn;
    }

    /**
     * @return the score
     */
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
    
    
    public void startCount(){
        setCountOn(true);
        setCounter(0);
        setScore(0);
    }
    
    public int stopCount(){
        setCountOn(false);
        setScore(100 - getCounter());
        return getScore();
    }
    
    public void addCount(){
        if(isCountOn()){
            setCounter(getCounter()+1);
        }
    }
    
    
    
}
