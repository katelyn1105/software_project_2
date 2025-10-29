/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author katelyncorreia
 */
public class GameState {
    // declaring varibles
    private String name;
    private int score;
    private boolean hasKey;
    private boolean hasSword;
    private boolean beenToKitchen;
    private final Set<String> inventory = new HashSet<>(); // hash set to store inventory
    private int currentStage;
    private final Timer timer;
    
    public GameState() {
        name = "";
        hasKey = false;
        hasSword = false;
        score = 0;
        currentStage = 0; // 0 is the start stage
        timer = new Timer();
    }
    // name setter
    public void setName(String name) {
        this.name = name;
    }
    
    // name getter
    public String getName() {
        return this.name;
    }
    
    // items setter
    public void setKey(){
        this.hasKey = true;
        inventory.add("Key");
    }
    
    public void setSword(){
    this.hasSword = true;
    inventory.add("Sword");
    }
    
    // items getter
    public boolean getKey(){
        return hasKey;
    }
    
    public boolean getSword(){
        return hasSword;
    }
    
    // score
    public int getScore() {
        return score;
    }
    
    public void setScore(int s){
        score = s;
    }

    public void addScore(int points) {
        this.score += points;
    }
    
    // locations setter and getter
    public void setKitchen(){
        this.beenToKitchen = true;
    }
    public boolean getKitchen(){
        return beenToKitchen;
    }
    
    // inventory
    public Set<String> getInventory() {
        return inventory;
    }
    
    // current stage
    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int stage) {
        this.currentStage = stage;
    }
    
    
    
    
    //Timer Controls
    
    public void startTimer(){
        timer.start();
    }
    
    public void stopTimer(){
        timer.stop();
    }
    public long getPlayTimeSecs(){
        return timer.getTimeSecs();
    }
}
