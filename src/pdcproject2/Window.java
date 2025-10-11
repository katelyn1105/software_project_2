/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author 1708k
 */
public class Window extends JPanel{
    
    
    
    
    
    //Placeholder until we palaver about how we want to do this. - Assuming your resolution isn't < 480
    final int screenWidth = 480;
    final int screenHeight = 480;
    
    // for the content on the tab that will switch out as we aren't doing multiple tables
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    
    public Window(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        
        
    }
    
    
    
    
    
    
    
    
}
