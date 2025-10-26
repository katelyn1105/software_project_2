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
    private String name;
    private int score;
    private boolean hasKey;
    private boolean hasSword;
    private boolean beenToKitchen;
    private final Set<String> inventory = new HashSet<>();
    private int currentStage;
    
    public GameState() {
        name = "";
        hasKey = false;
        hasSword = false;
        score = 0;
        currentStage = 0; // 0 is the start stage
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
        if(inventory.contains("Key")&&inventory.contains("Sword")){
            score = 100;
        }
        return score;
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
    
    
}
