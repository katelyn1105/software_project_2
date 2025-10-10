/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pdcproject2;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author 1708k
 */
public class PDCProject2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Window window = new Window();
       JFrame frame = new JFrame();
        
        
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("K&K's Ergonomic Adventure!");
       frame.setResizable(true);
       frame.setLocationRelativeTo(null); // TESTTTT
       frame.setVisible(true);
        
       
       
       frame.add(window);
       frame.pack();
        
    }
    
}
