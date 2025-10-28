/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;

import javax.swing.*;
import java.awt.*;

/**
 * Stage 1 of the game — starting the adventure: tree, path, entrance.
 * Handles panels:
 *  - stage1Panel
 *  - treePanel
 *  - pathPanel
 *  - entrancePanel
 *
 * @author katelyncorreia
 */
public class RPGStage1 {
    public RPGStage1(RPGGameWindow window, GameController controller, JPanel cardPanel) {
        // STAGE 1 PANEL
        window.stage1Panel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.stage1Panel.add(new JLabel("You see a path up ahead and a big tree in the distance.", JLabel.CENTER));
        JPanel buttons = new JPanel();
        JButton treeButton = new JButton("Go to the Tree");
        JButton pathButton = new JButton("Follow the Path");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(window, true);
        buttons.add(treeButton);
        buttons.add(pathButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        window.stage1Panel.add(new JLabel("Find the hidden treasure and survive!", JLabel.CENTER));
        window.stage1Panel.add(buttons);
        cardPanel.add(window.stage1Panel, "stage1Panel");

        treeButton.addActionListener(e -> controller.goToPanel("treePanel"));
        pathButton.addActionListener(e -> controller.goToPanel("pathPanel"));

        // TREE PANEL
        window.treePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.hasKeyLabel = new JLabel("", JLabel.CENTER);
        JButton takeKeyButton = new JButton("Take the Key");
        JButton goBackButton = new JButton("Back");
        QuitButton quitButton2 = new QuitButton(true);
        MenuButton menuButton2 = new MenuButton(window, true);
        window.treePanel.add(new JLabel("You spot a key under the big tree!", JLabel.CENTER));
        window.treePanel.add(window.hasKeyLabel);
        JPanel treeButtons = new JPanel();
        treeButtons.add(takeKeyButton);
        treeButtons.add(goBackButton);
        treeButtons.add(quitButton2);
        treeButtons.add(menuButton2);
        window.treePanel.add(treeButtons);
        cardPanel.add(window.treePanel, "treePanel");
        takeKeyButton.addActionListener(e -> controller.takeKey());
        goBackButton.addActionListener(e -> controller.goBackToStage1());

        // PATH PANEL
        window.pathPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.noKeyLabel = new JLabel("", JLabel.CENTER);
        JButton unlockButton = new JButton("Unlock the Door");
        JButton goBackButton2 = new JButton("Back");
        QuitButton quitButton3 = new QuitButton(true);
        MenuButton menuButton3 = new MenuButton(window, true);
        window.pathPanel.add(new JLabel("You find a mysterious house with a locked door.", JLabel.CENTER));
        window.pathPanel.add(window.noKeyLabel);
        JPanel pathButtons = new JPanel();
        pathButtons.add(unlockButton);
        pathButtons.add(goBackButton2);
        pathButtons.add(quitButton3);
        pathButtons.add(menuButton3);
        window.pathPanel.add(pathButtons);
        cardPanel.add(window.pathPanel, "pathPanel");
        unlockButton.addActionListener(e -> controller.unlockFrontDoor());
        goBackButton2.addActionListener(e -> controller.goBackToStage1());

        // ENTRANCE PANEL
        window.entrancePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.infoLabel4 = new JLabel("The door opens! You are now in a dusty entrance hall.", JLabel.CENTER);
        JButton kitchenButton = new JButton("Go to Kitchen");
        JButton atticButton = new JButton("Climb to Attic");
        QuitButton quitButton4 = new QuitButton(true);
        MenuButton menuButton4 = new MenuButton(window, true);
        window.entrancePanel.add(window.infoLabel4);
        window.entrancePanel.add(new JLabel("You spot a door to a kitchen and a ladder up to an attic.", JLabel.CENTER));
        JPanel entranceButtons = new JPanel();
        entranceButtons.add(kitchenButton);
        entranceButtons.add(atticButton);
        entranceButtons.add(quitButton4);
        entranceButtons.add(menuButton4);
        window.entrancePanel.add(entranceButtons);
        cardPanel.add(window.entrancePanel, "entrancePanel");
        kitchenButton.addActionListener(e -> controller.goToKitchen());
        atticButton.addActionListener(e -> controller.goToAttic());

        // KITCHEN PANEL
        window.kitchenPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        window.infoLabel6 = new JLabel("You walk towards the kitchen and meet a friendly chef.", JLabel.CENTER);
        window.chefDialogue = new JLabel("'Gah! Who are you!? Sorry, I thought you might be Sir Doncillme.'", JLabel.CENTER);
        window.chefDialogue2 = new JLabel("'I haven't seen him in weeks...'", JLabel.CENTER);
        window.chefDialogue3 = new JLabel("'Go talk to the Gardener outside if you want to know more.'", JLabel.CENTER);
        JButton gardenButton = new JButton("Go to Garden");
        JButton goBackButton3 = new JButton("Back");
        QuitButton quitButton5 = new QuitButton(true);
        MenuButton menuButton5 = new MenuButton(window, true);
        window.kitchenPanel.add(window.infoLabel6);
        window.kitchenPanel.add(window.chefDialogue);
        window.kitchenPanel.add(window.chefDialogue2);
        window.kitchenPanel.add(window.chefDialogue3);
        JPanel kitchenButtons = new JPanel();
        kitchenButtons.add(gardenButton);
        kitchenButtons.add(goBackButton3);
        kitchenButtons.add(quitButton5);
        kitchenButtons.add(menuButton5);
        window.kitchenPanel.add(kitchenButtons);
        cardPanel.add(window.kitchenPanel, "kitchenPanel");
        gardenButton.addActionListener(e -> controller.goToGarden());
        goBackButton3.addActionListener(e -> controller.goToPanel("entrancePanel"));

        // ATTIC PANEL
        window.atticPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.infoLabel9 = new JLabel("You see something shiny in the corner...", JLabel.CENTER);
        window.hasSwordLabel = new JLabel("", JLabel.CENTER);
        JButton investigateButton = new JButton("Investigate");
        JButton goBackButton4 = new JButton("Back");
        QuitButton quitButton6 = new QuitButton(true);
        MenuButton menuButton6 = new MenuButton(window, true);
        window.atticPanel.add(new JLabel("You enter the attic, filled with dust and cobwebs.", JLabel.CENTER));
        window.atticPanel.add(window.infoLabel9);
        window.atticPanel.add(window.hasSwordLabel);
        JPanel atticButtons = new JPanel();
        atticButtons.add(investigateButton);
        atticButtons.add(goBackButton4);
        atticButtons.add(quitButton6);
        atticButtons.add(menuButton6);
        window.atticPanel.add(atticButtons);
        cardPanel.add(window.atticPanel, "atticPanel");
        investigateButton.addActionListener(e -> controller.investigateTreasure());
        goBackButton4.addActionListener(e -> controller.goToPanel("entrancePanel"));
        if (controller.hasSword()) {
            window.hasSwordLabel.setText("You already picked up the sword!");
        }

        // TREASURE PANEL
        window.investigateTreasurePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.treasureInfoLabel1 = new JLabel("Congratulations! You found the treasure!", JLabel.CENTER);
        window.treasureInfoLabel2 = new JLabel("But your journey is not over yet...", JLabel.CENTER);
        window.investigateTreasurePanel.add(window.treasureInfoLabel1);
        window.investigateTreasurePanel.add(window.treasureInfoLabel2);
        cardPanel.add(window.investigateTreasurePanel, "investigateTreasurePanel");
    }
}