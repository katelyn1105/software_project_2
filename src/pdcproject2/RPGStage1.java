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

// stage 1 of the rpg game, for the tree, path, entrance, kitchen, treasure panels
public class RPGStage1 {
    public RPGStage1(RPGGameWindow window, GameController controller, JPanel cardPanel) {
        
        // stage 1 panel
        window.stage1Panel = new JPanel(new GridLayout(4, 1, 5, 5)); // grid layout
        window.stage1Panel.add(new JLabel("You see a path up ahead and a big tree in the distance.", JLabel.CENTER)); // information
        JPanel buttons = new JPanel();
        
        // two button choices
        JButton treeButton = new JButton("Go to the Tree");
        JButton pathButton = new JButton("Follow the Path");
        // quit and menu buttons
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(window, true);
        // add buttons
        buttons.add(treeButton);
        buttons.add(pathButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        window.stage1Panel.add(new JLabel("Find the hidden treasure and survive!", JLabel.CENTER));
        window.stage1Panel.add(buttons);
        // add panel
        cardPanel.add(window.stage1Panel, "stage1Panel");

        // go to tree panel
        treeButton.addActionListener(e -> controller.goToPanel("treePanel"));
        // go to path panel
        pathButton.addActionListener(e -> controller.goToPanel("pathPanel"));

        // tree panel
        window.treePanel = new JPanel(new GridLayout(4, 1, 5, 5)); // grid layout
        window.hasKeyLabel = new JLabel("", JLabel.CENTER); // label is edited with information if the player has the key
        
        // buttons
        JButton takeKeyButton = new JButton("Take the Key");
        JButton goBackButton = new JButton("Back");
        
        // quit and menu buttons
        QuitButton quitButton2 = new QuitButton(true);
        MenuButton menuButton2 = new MenuButton(window, true);
        
        
        window.treePanel.add(new JLabel("You spot a key under the big tree!", JLabel.CENTER));
        window.treePanel.add(window.hasKeyLabel);
        JPanel treeButtons = new JPanel();
        
        // add buttons
        treeButtons.add(takeKeyButton);
        treeButtons.add(goBackButton);
        treeButtons.add(quitButton2);
        treeButtons.add(menuButton2);
        window.treePanel.add(treeButtons);
        
        // add tree panel
        cardPanel.add(window.treePanel, "treePanel");
        
        // redirect to
        takeKeyButton.addActionListener(e -> controller.takeKey());
        goBackButton.addActionListener(e -> controller.goBackToStage1());

        // path panel
        window.pathPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.noKeyLabel = new JLabel("", JLabel.CENTER); // edited with information if the user does not have the key
        
        // buttons
        JButton unlockButton = new JButton("Unlock the Door");
        JButton goBackButton2 = new JButton("Back");
        
        // quit and menu buttons
        QuitButton quitButton3 = new QuitButton(true);
        MenuButton menuButton3 = new MenuButton(window, true);
        
        
        window.pathPanel.add(new JLabel("You find a mysterious house with a locked door.", JLabel.CENTER));
        window.pathPanel.add(window.noKeyLabel);
        JPanel pathButtons = new JPanel();
        
        // add buttons
        pathButtons.add(unlockButton);
        pathButtons.add(goBackButton2);
        pathButtons.add(quitButton3);
        pathButtons.add(menuButton3);
        window.pathPanel.add(pathButtons);
        // create panel
        cardPanel.add(window.pathPanel, "pathPanel");
        unlockButton.addActionListener(e -> controller.unlockFrontDoor());
        goBackButton2.addActionListener(e -> controller.goBackToStage1());

        // entrance panel
        window.entrancePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.infoLabel4 = new JLabel("The door opens! You are now in a dusty entrance hall.", JLabel.CENTER);
        
        // two options buttons
        JButton kitchenButton = new JButton("Go to Kitchen");
        JButton atticButton = new JButton("Climb to Attic");
        
        // quit and menu buttons
        QuitButton quitButton4 = new QuitButton(true);
        MenuButton menuButton4 = new MenuButton(window, true);
        window.entrancePanel.add(window.infoLabel4);
        window.entrancePanel.add(new JLabel("You spot a door to a kitchen and a ladder up to an attic.", JLabel.CENTER));
        JPanel entranceButtons = new JPanel();
        
        // add buttons
        entranceButtons.add(kitchenButton);
        entranceButtons.add(atticButton);
        entranceButtons.add(quitButton4);
        entranceButtons.add(menuButton4);
        window.entrancePanel.add(entranceButtons);
        // create entrance panel
        cardPanel.add(window.entrancePanel, "entrancePanel");
        
        kitchenButton.addActionListener(e -> controller.goToKitchen());
        atticButton.addActionListener(e -> controller.goToAttic());

        // kitchen panel
        window.kitchenPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        
        // information on the first visit to the kitchen
        window.infoLabel6 = new JLabel("You walk towards the kitchen and meet a friendly chef.", JLabel.CENTER);
        window.chefDialogue = new JLabel("'Gah! Who are you!? Sorry, I thought you might be Sir Doncillme.'", JLabel.CENTER);
        window.chefDialogue2 = new JLabel("'I haven't seen him in weeks...'", JLabel.CENTER);
        window.chefDialogue3 = new JLabel("'Go talk to the Gardener outside if you want to know more.'", JLabel.CENTER);
        
        // two options
        JButton gardenButton = new JButton("Go to Garden");
        JButton goBackButton3 = new JButton("Back");
        // quit and menu buttons
        QuitButton quitButton5 = new QuitButton(true);
        MenuButton menuButton5 = new MenuButton(window, true);
        window.kitchenPanel.add(window.infoLabel6);
        window.kitchenPanel.add(window.chefDialogue);
        window.kitchenPanel.add(window.chefDialogue2);
        window.kitchenPanel.add(window.chefDialogue3);
        JPanel kitchenButtons = new JPanel();
        
        // add buttons
        kitchenButtons.add(gardenButton);
        kitchenButtons.add(goBackButton3);
        kitchenButtons.add(quitButton5);
        kitchenButtons.add(menuButton5);
        window.kitchenPanel.add(kitchenButtons);
        // add ktichen panel
        cardPanel.add(window.kitchenPanel, "kitchenPanel");
        gardenButton.addActionListener(e -> controller.goToGarden());
        // take to the entrance panel
        goBackButton3.addActionListener(e -> controller.goToPanel("entrancePanel"));

        // attic panel
        window.atticPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.infoLabel9 = new JLabel("You see something shiny in the corner...", JLabel.CENTER);
        window.hasSwordLabel = new JLabel("", JLabel.CENTER); // this is changed if the user has the sword
        
        // buttons options
        JButton investigateButton = new JButton("Investigate");
        JButton goBackButton4 = new JButton("Back");
        // quit and menu buttons
        QuitButton quitButton6 = new QuitButton(true);
        MenuButton menuButton6 = new MenuButton(window, true);
        // add labels
        window.atticPanel.add(new JLabel("You enter the attic, filled with dust and cobwebs.", JLabel.CENTER));
        window.atticPanel.add(window.infoLabel9);
        window.atticPanel.add(window.hasSwordLabel);
        JPanel atticButtons = new JPanel();
        // add buttons
        atticButtons.add(investigateButton);
        atticButtons.add(goBackButton4);
        atticButtons.add(quitButton6);
        atticButtons.add(menuButton6);
        window.atticPanel.add(atticButtons);
        // add attic panel
        cardPanel.add(window.atticPanel, "atticPanel");
        investigateButton.addActionListener(e -> controller.investigateTreasure());
        goBackButton4.addActionListener(e -> controller.goToPanel("entrancePanel"));
        // if the user already has the sword then display
        if (controller.hasSword()) {
            window.hasSwordLabel.setText("You already picked up the sword!");
        }

        // treasure panel
        window.investigateTreasurePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        // this panel just has information for the player
        window.treasureInfoLabel1 = new JLabel("Congratulations! You found the treasure!", JLabel.CENTER);
        window.treasureInfoLabel2 = new JLabel("But your journey is not over yet...", JLabel.CENTER);
        // add the labels
        window.investigateTreasurePanel.add(window.treasureInfoLabel1);
        window.investigateTreasurePanel.add(window.treasureInfoLabel2);
        // add the panel
        cardPanel.add(window.investigateTreasurePanel, "investigateTreasurePanel");
    }
}