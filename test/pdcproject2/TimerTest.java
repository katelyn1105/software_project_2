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
        Timer timer = new Timer();
    }
    
    @After
    public void tearDown() {
        if(timer != null){
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
        assertTrue("Timer should be running after start()", timer.isRunning());

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
    try{
        Thread.sleep(50);
    }catch(InterruptedException e){
        System.out.println("Thread failed"+ e.getMessage());
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
     try{
         thread.sleep(50);
     }catch(InterruptedException e){
         e.getMessage();
     }
    }

    /**
     * Test of getTimeMill method, of class Timer.
     */
    @Test
    public void testGetTimeMill() {
        System.out.println("getTimeMill");
        Timer instance = new Timer();
        long expResult = 0L;
        long result = instance.getTimeMill();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSecs method, of class Timer.
     */
    @Test
    public void testGetTimeSecs() {
        System.out.println("getTimeSecs");
        Timer instance = new Timer();
        long expResult = 0L;
        long result = instance.getTimeSecs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeFormatted method, of class Timer.
     */
    @Test
    public void testGetTimeFormatted() {
        System.out.println("getTimeFormatted");
        Timer instance = new Timer();
        String expResult = "";
        String result = instance.getTimeFormatted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRunning method, of class Timer.
     */
    @Test
    public void testIsRunning() {
        System.out.println("isRunning");
        Timer instance = new Timer();
        boolean expResult = false;
        boolean result = instance.isRunning();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
