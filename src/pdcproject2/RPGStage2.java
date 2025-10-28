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
 * 
 * @author katelyncorreia
 */

// stage 2 of the game, from the rose bush to the gardener panel
public class RPGStage2 {
    public RPGStage2(RPGGameWindow window, GameController controller, JPanel cardPanel) {

        // garden panel
        window.gardenPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        window.warningLabel = new JLabel("", JLabel.CENTER); // label edited if the user went to the rose bush

        // buttons options
        JButton roseBushButton = new JButton("Inspect Rose Bush");
        JButton gardenShedButton = new JButton("Go to Garden Shed");
        // quit and menu buttons
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(window, true);

        window.gardenPanel.add(new JLabel("You step into the garden; the air feels heavy...", JLabel.CENTER));
        window.gardenPanel.add(window.warningLabel);

        // add buttons
        JPanel buttons = new JPanel();
        buttons.add(roseBushButton);
        buttons.add(gardenShedButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        window.gardenPanel.add(buttons);
        // add panel
        cardPanel.add(window.gardenPanel, "gardenPanel");
        // redirect when buttons are pressed
        roseBushButton.addActionListener(e -> controller.goToRoseBush());
        gardenShedButton.addActionListener(e -> controller.goToPanel("gardenerPanel"));

        // rose bush panel
        window.roseBushPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        // back button is the only option here
        JButton goBackButton = new JButton("Back");
        // quit and menu buttons
        QuitButton quitButton2 = new QuitButton(true);
        MenuButton menuButton2 = new MenuButton(window, true);

        window.roseBushPanel.add(new JLabel("You trip into the rose bush! Ouch!", JLabel.CENTER));
        JPanel roseButtons = new JPanel();
        // add buttons
        roseButtons.add(goBackButton);
        roseButtons.add(quitButton2);
        roseButtons.add(menuButton2);
        window.roseBushPanel.add(roseButtons);
        cardPanel.add(window.roseBushPanel, "roseBushPanel");
        // redirect back to the garden panel
        goBackButton.addActionListener(e -> controller.goBackToGarden());

        // gardener panel
        window.gardenerPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        // for the gardeners dialogue
        window.gardenerPanel.add(new JLabel("You try the door, when suddenly the Gardener appears from behind a hedge!", JLabel.CENTER));
        window.gardenerPanel.add(new JLabel("'Oi! Whatcha doin there! Ye trynna get inna ma' shed?'", JLabel.CENTER));
        window.gardenerPanel.add(new JLabel("'Sure, jus' hadta' ask'", JLabel.CENTER));
        window.gardenerPanel.add(new JLabel("He hands you the shed key.", JLabel.CENTER));

        // button options
        JButton takeKeyButton = new JButton("Take Key");
        // quit and menu buttons
        QuitButton quitButton3 = new QuitButton(true);
        MenuButton menuButton3 = new MenuButton(window, true);
        
        // add buttons
        JPanel gardenerButtons = new JPanel();
        gardenerButtons.add(takeKeyButton);
        gardenerButtons.add(quitButton3);
        gardenerButtons.add(menuButton3);
        window.gardenerPanel.add(gardenerButtons);
        // add panel
        cardPanel.add(window.gardenerPanel, "gardenerPanel");
        // unlock shed button redirects to garden shed panel
        takeKeyButton.addActionListener(e -> controller.unlockShed());
    }
}

