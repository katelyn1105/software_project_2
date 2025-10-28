/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdcproject2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

/**
 *
 * @author 1708k
 */
public class TimerTest {

    private Timer timer;

    public TimerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Timer Test initiating.");
       
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Terminating Timer Test.");
    }

    @Before
    public void setUp() {
        timer = new Timer();
    }

    @After
    public void tearDown() {
        if (timer != null) {
            timer = null;
        }
    }

    /**
     * Test of start method, of class Timer.
     */
    @Test
    public void testStartAndIsRunning() throws InterruptedException {
        assertFalse("Timer should not be running initially", timer.isRunning());
        timer.start();
        assertTrue("Timer should be running after start", timer.isRunning());

        //Pause - this allows incrementation.
        Thread.sleep(50);

        timer.stop();
        assertFalse("Timer still running error", timer.isRunning());
    }

    /**
     * Test of stop method, of class Timer.
     */
    @Test
    public void testStop() {
        timer.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("Thread failed" + e.getMessage());
        }

        timer.stop();
        long elapsed = timer.getTimeMill();
        assertTrue("Time > 0 ", elapsed > 0);

        //Stop - no reset
        timer.stop();
        assertEquals("Time is same as before ", elapsed, timer.getTimeMill());
    }

    /**
     * Test of reset method, of class Timer.
     */
    @Test
    public void testReset() {
        timer.reset();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.getMessage();
        }
        assertTrue(timer.getTimeMill() < 10);

        timer.reset();

        assertEquals("Timer should be zero (0)", 0L, timer.getTimeMill());
        assertFalse("Timer is not running ", timer.isRunning());

    }

    /**
     * Test of getTimeMill method, of class Timer.
     */
    @Test
    public void testGetTimeMill() {
        timer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error testMill" + e.getMessage());
        }
        timer.stop();

        long ms = timer.getTimeMill();
        long secs = timer.getTimeSecs();

        assertTrue("Milliseconds should be >= 100", ms >= 100);
        assertEquals("Seconds should be 1", 1, secs);
    }

    /**
     * Test of getTimeSecs method, of class Timer.
     */
    @Test
    public void testGetTimeSecs() {
        timer.start();
        try {
            Thread.sleep(1000);//Need greater than 1000
        } catch (InterruptedException e) {
            System.out.println("Error testSecs" + e.getMessage());
        }
        timer.stop();

        long ms = timer.getTimeMill();
        long secs = timer.getTimeSecs();

        assertTrue("Seconds should be >= 100", secs >= 1);
        assertEquals("Seconds should be 1", 1, secs);
    }

    /**
     * Test of getTimeFormatted method, of class Timer.
     */
    @Test
    public void testGetTimeFormatted() {
            timer.start();
        try{
        Thread.sleep(1000); 
        }catch(InterruptedException e){
            System.out.println("Error Time formatted" + e.getMessage());
        }
        timer.stop();

        String tform = timer.getTimeFormatted();
        
        assertNotNull("Time should not be null",tform);
        //assertTrue("Should be colon present", tform.contains(":"));
    }

    /**
     * Test of isRunning method, of class Timer.
     */


}

