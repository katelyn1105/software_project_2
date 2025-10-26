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
    public MenuButton(RPGGameWindow window, boolean confirmChoice) {
        super("Return to Menu");
        addActionListener(e -> {
            if (confirmChoice) {
                int menuChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to return to the menu, you will lose your progress!", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
                if (menuChoice == JOptionPane.YES_OPTION) {
                    window.getController().resetGame(); 
                    window.showPanel("initialPanel");
            }
            } else {
                window.showPanel("initialPanel");
            }
        });
            
                
    }
}
