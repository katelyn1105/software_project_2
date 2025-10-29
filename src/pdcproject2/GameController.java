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
    private final DBConnect db = new DBConnect();
    private final ButtonTracker tracker;
    private final DBHandler dbh;
    //private final Timer timer = new Timer();
    
    
    // Game flags for stages/items
    private boolean key = false;
    private boolean sword = false;
    private boolean beenToKitchen = false;

    // constructor
    public GameController(RPGGameWindow window) {
        this.window = window;
        this.state = new GameState();
        this.tracker = new ButtonTracker();
        this.dbh = new DBHandler(db);
    }
    
    // game
    public void startGame(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            window.getErrorLabel().setText("Please enter your name to start");
            return;
        }
        state.setName(playerName);
        tracker.startCount();
        //Timer start
        state.startTimer();
        System.out.println("Timer started");
        window.showPanel("stage1Panel");
    }

    public void endGame(boolean win) {
        //Stop Timer and save stats to databases.
        state.stopTimer();
        tracker.stopCount();
        state.setScore(tracker.getScore());
        window.showPanel(win ? "winPanel" : "lostPanel");
        
        dbh.savePlayer(state);
        
        resetGame();
        
        
    }
       
    //Read databases and combine to a scoreboard.
    public void showHighScores() {
        
        dbh.showHighScores();
        System.out.println("Showing high scores...");
    } 
    
    public void showLostPanel() {
        JLabel lostLabel = window.getLostMessageLabel();
        if (sword) {
            lostLabel.setText("The Gardener shows you no mercy, you are defeated...");
        } else {
            lostLabel.setText("Without a weapon, you are defeated...");
        }
        window.showPanel("lostPanel");
    }
    
public void takeKey() {
        if (!key) {
            state.setKey();
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
            state.setSword();
            sword = true;
            window.showPanel("investigateTreasurePanel");
            // Auto-return to entrance after 5 seconds
            new Thread(() -> {
                try { 
                    Thread.sleep(1000); 
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
            showLostPanel(); // Player loses
        }
    }

    public void beg() {
        showLostPanel();
    }


    public void goBackToStage1() {
        window.showPanel("stage1Panel");
    }

    public void goBackToGarden() {
        window.showPanel("gardenPanel");
        window.getWarningLabel().setText("Probably not a good idea to smell the roses again...");
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
      // Reset controller-level flags
        key = false;
        sword = false;
        beenToKitchen = false;

        // Reset game state completely
        state.setName("");
        state.setCurrentStage(0);

        // Reset items and inventory
        state.getInventory().clear();

        // Reset UI labels
        window.getHasKeyLabel().setText("");
        window.getHasSwordLabel().setText("");
        window.getNoKeyLabel().setText("");
        window.getWarningLabel().setText("");
        window.getErrorLabel().setText("");

        // Reset player input
        window.getEnterNameBox().setText("");

        // Restart the score/timer tracker
        tracker.startCount();

        // Return to the start of the game
        window.showPanel("initialPanel");
    }

    public void showPanel(String panelName) {
        window.showPanel(panelName);
    }
}
