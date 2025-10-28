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
    public QuitButton(boolean confirmQuit) { // users are given the option to confirm if they want to quit as progress will be lost
        super("Quit"); // displayed on button
        addActionListener(e -> { // when the button is pressed
            if (confirmQuit) { // if it is set to true, the follow question is displayed with yes or no options
                int quitChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit, you will lose your progress!", "Confirm Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // if the user picks yes
                if (quitChoice == JOptionPane.YES_OPTION) {
                    System.exit(0); // exit the program
            }
            } else { 
                System.exit(0); // also exit the program but no progress is being lost so the user does not need to be informed of this
            }
        });
            
                
    }
}
