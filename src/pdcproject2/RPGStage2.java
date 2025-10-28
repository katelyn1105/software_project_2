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


/**
 * Stage 2 of the game — the middle section:
 * Kitchen, Attic, Treasure discovery, Garden, Rose Bush, Gardener, and Shed.
 * 
 * Panels handled:
 *  - kitchenPanel
 *  - atticPanel
 *  - investigateTreasurePanel
 *  - gardenPanel
 *  - roseBushPanel
 *  - gardenerPanel
 *  - gardenShedPanel
 * 
 * @author katelyncorreia
 */
public class RPGStage2 {
    public RPGStage2(RPGGameWindow window, GameController controller, JPanel cardPanel) {

        // GARDEN PANEL
        window.gardenPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.warningLabel = new JLabel("", JLabel.CENTER);

        JButton roseBushButton = new JButton("Inspect Rose Bush");
        JButton gardenShedButton = new JButton("Go to Garden Shed");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(window, true);

        window.gardenPanel.add(new JLabel("You step into the garden; the air feels heavy...", JLabel.CENTER));
        window.gardenPanel.add(window.warningLabel);

        JPanel buttons = new JPanel();
        buttons.add(roseBushButton);
        buttons.add(gardenShedButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        window.gardenPanel.add(buttons);

        cardPanel.add(window.gardenPanel, "gardenPanel");

        roseBushButton.addActionListener(e -> controller.goToRoseBush());
        gardenShedButton.addActionListener(e -> controller.goToPanel("gardenerPanel"));

        // ROSE BUSH PANEL
        window.roseBushPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton goBackButton = new JButton("Back");
        QuitButton quitButton2 = new QuitButton(true);
        MenuButton menuButton2 = new MenuButton(window, true);

        window.roseBushPanel.add(new JLabel("You trip into the rose bush! Ouch!", JLabel.CENTER));
        JPanel roseButtons = new JPanel();
        roseButtons.add(goBackButton);
        roseButtons.add(quitButton2);
        roseButtons.add(menuButton2);
        window.roseBushPanel.add(roseButtons);
        cardPanel.add(window.roseBushPanel, "roseBushPanel");
        goBackButton.addActionListener(e -> controller.goBackToGarden());

        // GARDENER PANEL
        window.gardenerPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        window.gardenerPanel.add(new JLabel("You try the door, when suddenly the Gardener appears from behind a hedge!", JLabel.CENTER));
        window.gardenerPanel.add(new JLabel("'Oi! Whatcha doin there! Ye trynna get inna ma' shed?'", JLabel.CENTER));
        window.gardenerPanel.add(new JLabel("'Sure, jus' hadta' ask'", JLabel.CENTER));
        window.gardenerPanel.add(new JLabel("He hands you the shed key.", JLabel.CENTER));

        JButton takeKeyButton = new JButton("Take Key");
        QuitButton quitButton3 = new QuitButton(true);
        MenuButton menuButton3 = new MenuButton(window, true);

        JPanel gardenerButtons = new JPanel();
        gardenerButtons.add(takeKeyButton);
        gardenerButtons.add(quitButton3);
        gardenerButtons.add(menuButton3);
        window.gardenerPanel.add(gardenerButtons);

        cardPanel.add(window.gardenerPanel, "gardenerPanel");

        takeKeyButton.addActionListener(e -> controller.unlockShed());
    }
}

