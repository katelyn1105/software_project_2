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
    public class Window extends JFrame {

    private final GameController controller;
    private final gameWindow gui;

    public Window() {
        gui = new gameWindow();
        controller = new GameController(gui);

        setTitle("Escape RPG");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(480, 480));
        setLayout(new BorderLayout());

        add(gui, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Start button wiring
        controller.getState(); // initialize state if needed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}

