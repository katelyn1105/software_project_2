/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author katelyncorreia
 */
public class GameController {
    private final gameWindow window;
    private final GameState state;
    //private final DBConnect db;
    private final ButtonTracker tracker;

    // constructor
    public GameController(gameWindow window) {
        this.window = window;
        this.state = new GameState();
        //this.db = new DBConnect();
        this.tracker = new ButtonTracker();
        //setupListeners(); // for the buttons to work
    }
    
//    private void setupListeners() {
//        // Start button
//        window.getStartButton().addActionListener(e -> startGame());
//
//        // Confirm button (enter name)
//        window.getConfirmButton().addActionListener(e -> confirmName());
//
//        // Stage 1 buttons
//        window.getTreeButton().addActionListener(e -> showPanel("treePanel"));
//        window.getPathButton().addActionListener(e -> showPanel("pathPanel"));
//        window.getGoBackTreeButton().addActionListener(e -> showPanel("stage1Panel"));
//        window.getGoBackPathButton().addActionListener(e -> showPanel("stage1Panel"));
//
//        // Key and door
//        window.getTakeKeyButton().addActionListener(e -> takeKey());
//        window.getUnlockFrontDoorButton().addActionListener(e -> unlockFrontDoor());
//
//        // Kitchen
//        window.getKitchenButton().addActionListener(e -> goToKitchen());
//
//        // Attic
//        window.getAtticButton().addActionListener(e -> showPanel("atticPanel"));
//
//        // Garden
//        window.getGardenButton().addActionListener(e -> showPanel("gardenPanel"));
//        window.getReturnEntranceButton().addActionListener(e -> showPanel("entrancePanel"));
//
//        // Treasure / Sword
//        window.getInvestigateButton().addActionListener(e -> investigateTreasure());
//
//        // Garden interactions
//        window.getRoseBushButton().addActionListener(e -> showPanel("roseBushPanel"));
//        window.getGardenShedButton().addActionListener(e -> showPanel("inspectShedPanel"));
//        window.getGoBackGardenButton1().addActionListener(e -> {
//            showPanel("gardenPanel");
//            window.getWarningLabel().setText("Probably not a good idea...");
//        });
//        window.getDoorButton().addActionListener(e -> showPanel("gardenerPanel"));
//        window.getGoBackGardenButton2().addActionListener(e -> {
//            showPanel("gardenPanel");
//            window.getWarningLabel().setText("Probably not a good idea...");
//        });
//
//        // Shed / boss
//        window.getUnlockShedButton().addActionListener(e -> unlockShed());
//
//        // Boss fight buttons
//        window.getAttackButton().addActionListener(e -> attack());
//        window.getBegButton().addActionListener(e -> showPanel("lostPanel"));
//
//        // Highscores
//        window.getHighscoresButton().addActionListener(e -> showHighScore());
//    
//    }
    
    // start of the game
    public void startGame(String playerName) {
        state.setName(playerName);
        tracker.startCount();
        window.showPanel("stage1Panel");
    }
    
//    public void attack() {
//        if(state.hasSword()) {
//            endGame(true);
//        } else {
//            endGame(false);
//        }
//    }
    
    // end of the game
    public void endGame(boolean win) {
        tracker.stopCount();
        //db.savePlayer(state.getName(), tracker.getScore(), state.getInventory());
        window.showPanel(win ? "winPanel" : "lostPanel");
        
    }
    
    public void showHighScore(){
//        if (db == null) {
//            db = new DBConnect();
//            System.out.println("Connected hscore.");
//        }
        //db.highScores();
    }
    
    // state access
    public GameState getState() {
        return state;
    }
    
    public ButtonTracker getTracker() {
        return tracker;
    }
}
    
//    private void startGame() {
//        tracker.startCount();
//        showPanel("startPanel");
//    }

//    private void confirmName() {
//        String name = window.getEnterNameBox().getText();
//        if (!name.isEmpty()) {
//            showPanel("stage1Panel");
//        } else {
//            window.getErrorLabel().setText("Please enter your name to start");
//        }
//    }
//
//    private void takeKey() {
//        if (!key) {
//            key = true;
//            showPanel("stage1Panel");
//        } else {
//            window.getHasKeyLabel().setText("You already have the key!");
//        }
//    }
//
//    private void unlockFrontDoor() {
//        if (key) {
//            showPanel("entrancePanel");
//        } else {
//            window.getNoKeyLabel().setText("You need a key!");
//        }
//    }
//
//    private void goToKitchen() {
//        showPanel("kitchenPanel");
//        if (!beenToKitchen) {
//            beenToKitchen = true;
//        } else {
//            window.getInfoLabel6().setText("You approach the chef again");
//            window.getChefDialogue().setText("'Didn't you hear me?");
//            window.getChefDialogue2().setText("Stop making a mess and go talk to the Gardener!");
//            window.getChefDialogue3().setText("He's in the garden...obviously.'");
//        }
//    }
//
//    private void investigateTreasure() {
//        if (!sword) {
//            sword = true;
//            showPanel("treasurePanel");
//
//            // Return to entrance after 5 seconds
//            new Thread(() -> {
//                try { Thread.sleep(5000); } catch (InterruptedException ignored) {}
//                SwingUtilities.invokeLater(() -> {
//                    showPanel("entrancePanel");
//                    window.getInfoLabel4().setText("");
//                });
//            }).start();
//
//        } else {
//            window.getHasSwordLabel().setText("You already picked up the sword");
//        }
//    }
//
//    private void unlockShed() {
//        showPanel("gardenShedPanel");
//
//        // Delay to show panel info, then go to boss
//        new Thread(() -> {
//            try { Thread.sleep(5000); } catch (InterruptedException ignored) {}
//            SwingUtilities.invokeLater(() -> showPanel("bossFightPanel"));
//        }).start();
//    }
//
//    private void attack() {
//        if (sword) {
//            tracker.stopCount();
//            showPanel("winPanel");
//        } else {
//            showPanel("lostPanel");
//        }
//    }
//
//    private void showHighScore() {
//        // Call DB or other logic here
//        System.out.println("Highscore logic here");
//    }
//
//    private void showPanel(String panelName) {
//        CardLayout layout = (CardLayout) window.getCardPanel().getLayout();
//        layout.show(window.getCardPanel(), panelName);
//    }
//
//}
