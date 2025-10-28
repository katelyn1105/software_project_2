/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;

/**
 *
 * @author 1708k
 */
//Enables a timer to track time taken.
public class Timer {
    
    
       private long startTime;
    private long endTime;
    private boolean running;

    //Constructor
    public Timer() {
        running = false;
        startTime = 0;
        endTime = 0;
    }

    //Timer start/reset.
    public void start() {
        if(running = true){
            reset();
        }
        startTime = System.currentTimeMillis();
        running = true;
    }

    //Stop Timer
    public void stop() {
        if (running) {
            endTime = System.currentTimeMillis();
            System.out.println("Timer Stopped: " + endTime);
            running = false;
        }
    }

    //Resets Timer.
    public void reset() {
        startTime = 0;
        endTime = 0;
        running = false;
    }

    //This is still in milliseconds.
    public long getTimeMill() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

   //Return time in secs.
    public long getTimeSecs() {
        return getTimeMill() / 1000;
    }

    //Formats time into min:sec
    public String getTimeFormatted() {
        //This is to convert to secs
        long totalSeconds = getTimeSecs();
        long minutes = totalSeconds / 60;
        //Don't use double.
        long seconds = totalSeconds % 60;

        if (minutes > 0) {
            return String.format("%dm %02ds", minutes, seconds);
        } else {
            return String.format("%ds", seconds);
        }
    }

    //Check if running.
    public boolean isRunning() {
        return running;
    }
    
}
