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

// created this class so that we can have a reusable quit button as it is needed for every panel in the game
public class QuitButton extends JButton{
    public QuitButton(boolean confirmQuit) {
        super("Quit");
        addActionListener(e -> {
            if (confirmQuit) {
                int quitChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit, you will lose your progress!", "Confirm Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
                if (quitChoice == JOptionPane.YES_OPTION) {
                    System.exit(0);
            }
            } else {
                System.exit(0);
            }
        });
            
                
    }
}
