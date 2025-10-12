/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;

import javax.swing.JButton;

/**
 *
 * @author katelyncorreia
 */

// created this class so that we can have a reusable quit button as it is needed for every panel in the game
public class quitButton extends JButton{
    public quitButton() {
        super("Quit");
        addActionListener(e -> System.exit(0));
    }
}
