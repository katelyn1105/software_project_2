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


public class RPGStage0 {
    public RPGStage0(RPGGameWindow window, GameController controller, JPanel cardPanel) {
        window.initialPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("ESCAPE THE MIZTHERY LAIR!!!", JLabel.CENTER);
        title.setFont(new Font("Kavivanar", Font.BOLD, 22));

        JPanel buttons = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton startButton = new JButton("Start");
        JButton highscoresButton = new JButton("Highscores");
        QuitButton quitButton = new QuitButton(false);

        buttons.add(startButton);
        buttons.add(highscoresButton);
        buttons.add(quitButton);

        window.initialPanel.add(title, BorderLayout.CENTER);
        window.initialPanel.add(buttons, BorderLayout.SOUTH);
        cardPanel.add(window.initialPanel, "initialPanel");

        startButton.addActionListener(e -> controller.goToPanel("startPanel"));
        highscoresButton.addActionListener(e -> controller.showHighScores());

        // Start Panel
        window.startPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JLabel nameTitle = new JLabel("Enter your name to begin", JLabel.CENTER);
        MenuButton menuButton = new MenuButton(window, false);
        window.enterNameBox = new JTextField(15);
        window.errorLabel = new JLabel("", JLabel.CENTER);

        JPanel input = new JPanel();
        input.add(new JLabel("Name:"));
        input.add(window.enterNameBox);

        JPanel nameButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        QuitButton quitButton2 = new QuitButton(false);
        nameButtons.add(confirmButton);
        nameButtons.add(quitButton2);
        nameButtons.add(menuButton);

        window.startPanel.add(nameTitle);
        window.startPanel.add(input);
        window.startPanel.add(window.errorLabel);
        window.startPanel.add(nameButtons);
        cardPanel.add(window.startPanel, "startPanel");

        confirmButton.addActionListener(e -> controller.startGame(window.enterNameBox.getText()));
    }
}
