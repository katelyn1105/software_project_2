/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;

import javax.swing.*;

/**
 *
 * @author katelyncorreia
 */

// created this class so that we can have a reusable menu button as it is needed for every panel in the game
public class MenuButton extends JButton{
    // is used as a button in the GUI, and allows users to confirm their choice of going back to menu, this is displayed if set to true
    public MenuButton(RPGGameWindow window, boolean confirmChoice) {
        super("Return to Menu"); // what is displayed on the button
        addActionListener(e -> { // when the button is pressed
            if (confirmChoice) { // if true then the user is asked the follwoing question
                int menuChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to return to the menu, you will lose your progress!", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // if the user presses yes then thy are taken back to the menu and their progress is lost
                if (menuChoice == JOptionPane.YES_OPTION) {
                    window.getController().resetGame();  // the game is reset as progress is lost so the inventory is empty etc
                    window.showPanel("initialPanel"); // back to initial panel
            }
            } else { // if it is false then confirmation is not needed as there is no progress to be lost and so users are just taken back to the initial panel
                window.showPanel("initialPanel");
            }
        });
            
                
    }
}
