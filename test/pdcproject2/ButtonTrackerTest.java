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

/**
 *
 * @author 1708k
 */
public class ButtonTrackerTest {

    private ButtonTracker tracker;

    public ButtonTrackerTest() {

    }

    @Before
    public void setUp() {
        tracker = new ButtonTracker();
    }

    @After
    public void tearDown() {
        tracker = null;
    }


    /**
     * Test of isCountOn method, of class ButtonTracker.
     */
    @Test
    public void testIsCountzero() {
        assertEquals("Counter should start at 0", 0, tracker.getCounter());
        assertFalse("Counting should be off initially", tracker.isCountOn());
        assertEquals("Score should start at 0", 0, tracker.getScore());
    }


    /**
     * Test of getScore method, of class ButtonTracker.
     */
    @Test
    public void testGetandSetScore() {
        tracker.setScore(100);
        
        
        assertEquals("Score should be 100 ", 100, tracker.getScore());
    }

    

    /**
     * Test of startCount method, of class ButtonTracker.
     */
    @Test
    public void testStartCount() {
        tracker.startCount();
        assertTrue("Counting should be on after startCount()", tracker.isCountOn());
        assertEquals("Counter should be 0 after startCount()", 0, tracker.getCounter());
        assertEquals("Score should be 0 after startCount()", 0, tracker.getScore());
    }

    /**
     * Test of stopCount method, of class ButtonTracker.
     */
    @Test
    public void testStopCount() {
        tracker.startCount();
        for (int i = 0; i < 5; i++) {
            tracker.addCount();
        }
        tracker.stopCount();
        assertFalse("Counter should be off after stopCount()", tracker.isCountOn());
        assertEquals("Score should be 100 - counter after stopCount()", 95, tracker.getScore());
    }

    /**
     * Test of addCount method, of class ButtonTracker.
     */
    @Test
    public void testAddCount() {
        tracker.startCount();
        tracker.addCount();
        tracker.addCount();
        assertEquals("Count should increment only when counting is on", 2, tracker.getCounter());
    }

    @Test
    public void testAddCountWhenOff() {
        tracker.addCount();
        assertEquals("Count should not increment when counting is off", 0, tracker.getCounter());
    }

}
