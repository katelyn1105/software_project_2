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
public class quitButton extends JButton{
    public quitButton() {
        super("Quit");
        addActionListener(e -> System.exit(0));
    }
}
