/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author katelyncorreia
 */
public class GameController {
    private final RPGGameWindow window;
    private final GameState state;
    //private final DBConnect db;
    private final ButtonTracker tracker;
    
    // Game flags for stages/items
    private boolean key = false;
    private boolean sword = false;
    private boolean beenToKitchen = false;

    // constructor
    public GameController(RPGGameWindow window) {
        this.window = window;
        this.state = new GameState();
        //this.db = new DBConnect();
        this.tracker = new ButtonTracker();
    }
    
    // game
    public void startGame(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            window.getErrorLabel().setText("Please enter your name to start");
            return;
        }
        state.setName(playerName);
        tracker.startCount();
        window.showPanel("stage1Panel");
    }

    public void endGame(boolean win) {
        tracker.stopCount();
        window.showPanel(win ? "winPanel" : "lostPanel");
        // save to DB here i assume idk
    }

    public void showHighScores() {
        // implement database/highscore logic here
        System.out.println("Showing high scores...");
    } 
    
public void takeKey() {
        if (!key) {
            key = true;
            window.showPanel("stage1Panel");
        } else {
            window.getHasKeyLabel().setText("You already have the key!");
        }
    }

    public void unlockFrontDoor() {
        if (key) {
            window.showPanel("entrancePanel");
        } else {
            window.getNoKeyLabel().setText("You need a key!");
        }
    }
    
    

    public void goToKitchen() {
        window.showPanel("kitchenPanel");
        if (!beenToKitchen) {
            beenToKitchen = true;
        } else {
            window.getInfoLabel6().setText("You approach the chef again");
            window.getChefDialogue().setText("'Didn't you hear me?");
            window.getChefDialogue2().setText("Stop making a mess and go talk to the Gardener!");
            window.getChefDialogue3().setText("He's in the garden...obviously.'");
        }
    }

    public void goToAttic() {
        window.showPanel("atticPanel");
    }

    public void goToGarden() {
        window.showPanel("gardenPanel");
    }

    public void investigateTreasure() {
        if (!sword) {
            sword = true;
            window.showPanel("investigateTreasurePanel");
            // Auto-return to entrance after 5 seconds
            new Thread(() -> {
                try { 
                    Thread.sleep(5000); 
                } catch (InterruptedException ex) {
                ex.printStackTrace();
                }
                
                SwingUtilities.invokeLater(() -> {
                    window.showPanel("entrancePanel");
                });

            }).start();
        } else {
            window.getHasSwordLabel().setText("You already picked up the sword");
        }
    }
    
    public boolean hasSword() {
        return sword;
    }

    public void goToRoseBush() {
        window.showPanel("roseBushPanel");
    }

    public void goToGardenShed() {
        window.showPanel("inspectShedPanel");
    }

    public void goToGardener() {
        window.showPanel("gardenerPanel");
    }

    public void unlockShed() {
        window.showPanel("gardenShedPanel");
    }

    public void attackBoss() {
        if (sword) {
            endGame(true);  // Player wins
        } else {
            endGame(false); // Player loses
        }
    }

    public void beg() {
        endGame(false); // Player loses
    }

    public void goBackToStage1() {
        window.showPanel("stage1Panel");
    }

    public void goBackToGarden() {
        window.showPanel("gardenPanel");
        window.getWarningLabel().setText("Probably not a good idea...");
    }

    // ===== GENERIC PANEL NAVIGATION =====
    public void goToPanel(String panelName) {
        window.showPanel(panelName);
    }

    // ===== ACCESSORS =====
    public GameState getState() {
        return state;
    }

    public ButtonTracker getTracker() {
        return tracker;
    }

    // ===== RESET GAME =====
    public void resetGame() {
        key = false;
        sword = false;
        beenToKitchen = false;
        tracker.startCount();
        window.showPanel("startPanel");
        // Reset all UI labels
        window.getHasKeyLabel().setText("");
        window.getHasSwordLabel().setText("");
        window.getNoKeyLabel().setText("");
        window.getWarningLabel().setText("");

        // Optionally reset any text fields
        window.getEnterNameBox().setText("");
    }
    
    public void showPanel(String panelName) {
        window.showPanel(panelName);
    }
}
