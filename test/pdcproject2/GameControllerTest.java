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

import javax.swing.*;
import java.util.*;

/**
 *
 * @author 1708k
 */
public class GameControllerTest {

    //This is a Mock window - used for testing without using the actual code and variables used. Very useful.
    private static class MockWindow extends RPGGameWindow {

        private String lastPanelShown = "";
        private final JLabel errorLabel = new JLabel();
        private final JLabel hasKeyLabel = new JLabel();
        private final JLabel noKeyLabel = new JLabel();
        private final JLabel hasSwordLabel = new JLabel();
        private final JLabel warningLabel = new JLabel();
        private final JTextField nameBox = new JTextField();

        //All of these will have to be overriden.
        @Override
        public void showPanel(String name) {
            lastPanelShown = name;
        }

        @Override
        public JLabel getErrorLabel() {
            return errorLabel;
        }

        @Override
        public JLabel getHasKeyLabel() {
            return hasKeyLabel;
        }

        @Override
        public JLabel getNoKeyLabel() {
            return noKeyLabel;
        }

        @Override
        public JLabel getHasSwordLabel() {
            return hasSwordLabel;
        }

        @Override
        public JLabel getWarningLabel() {
            return warningLabel;
        }

        @Override
        public JTextField getEnterNameBox() {
            return nameBox;
        }
    }

    //We use these to pretend there is a database to connect to.
    //It doesn't actually save but simulates it.
    private static class MockDBConnect extends DBConnect {
    }

    private static class MockDBHandler extends DBHandler {

        boolean playerSaved = false;

        public MockDBHandler(DBConnect db) {
            super(db);//Pass it to super.
        }

        @Override
        public void savePlayer(GameState s) {
            playerSaved = true;//Pretend it was successful.
        }
    }

    //Objects.
    private MockWindow window;
    private GameController controller;

    @Before
    public void setUp() {
        window = new MockWindow();
        controller = new GameController(window);
    }

    @Test
    public void testStartGameValidName() {
        controller.startGame("Player1");
        assertEquals("stage1Panel", window.lastPanelShown);
    }

    @Test
    public void testStartGameEmptyNameError() {
        controller.startGame("");
        assertEquals("Please enter your name to start", window.getErrorLabel().getText());
    }

    @Test
    public void testTakenKey() {
        controller.takeKey();//Take key.
        assertEquals("stage1Panel", window.lastPanelShown);
        controller.takeKey(); //Take again.
        
        assertEquals("You already have the key!", window.getHasKeyLabel().getText());
    }

    @Test
    public void testUnlockFrontDoorNoKey() {
        controller.unlockFrontDoor();
        assertEquals("You need a key!", window.getNoKeyLabel().getText());
    }

    @Test
    public void testAttackBossNoSword() {
        controller.attackBoss();
        assertEquals("lostPanel", window.lastPanelShown);
    }

    @Test
    public void testAttackBosswithSword() {
        controller.takeKey(); //Pretend you have key (Inv check doesn't like it otherwise).
        controller.investigateTreasure(); //Sword from attic.
        controller.attackBoss();
        //This took a long time for some reason.
        //I think it's because of the Thread.sleep()...?
        assertTrue(window.lastPanelShown.equals("winPanel") || window.lastPanelShown.equals("entrancePanel"));
    }

    @Test
    public void testReset() {
        controller.takeKey();
        controller.resetGame();
        

        assertEquals("", window.getHasKeyLabel().getText());
        assertEquals("", window.getHasSwordLabel().getText());
        assertEquals("initialPanel", window.lastPanelShown);
    }

}
