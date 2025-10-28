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

// initial start of the game, this includes the title/start page, and the buttons to start, view the highscores, taken from the database, and quit
public class RPGStage0 {
    
    public RPGStage0(RPGGameWindow window, GameController controller, JPanel cardPanel) {
        window.initialPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("ESCAPE THE MIZTHERY LAIR!!!", JLabel.CENTER); // title
        title.setFont(new Font("Kavivanar", Font.BOLD, 22)); // font of the title

        JPanel buttons = new JPanel(new GridLayout(3, 1, 5, 5)); // grid layout
        JButton startButton = new JButton("Start"); // start button
        JButton highscoresButton = new JButton("Highscores"); // highscore button
        QuitButton quitButton = new QuitButton(false); // quit button, without warning of losing progress

        // add buttons to panel
        buttons.add(startButton);
        buttons.add(highscoresButton);
        buttons.add(quitButton);
        
        // add the labels and buttons to panel
        window.initialPanel.add(title, BorderLayout.CENTER);
        window.initialPanel.add(buttons, BorderLayout.SOUTH);
        
        // add card panel
        cardPanel.add(window.initialPanel, "initialPanel");
        
        // when the start button is pressed, takes the user to the start panel
        startButton.addActionListener(e -> controller.goToPanel("startPanel"));
        // take the user to the highscores and more information displayed from the databases
        highscoresButton.addActionListener(e -> controller.showHighScores());

        // start Panel
        window.startPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JLabel nameTitle = new JLabel("Enter your name to begin", JLabel.CENTER);
        MenuButton menuButton = new MenuButton(window, false);
        window.enterNameBox = new JTextField(15);
        window.errorLabel = new JLabel("", JLabel.CENTER);

        JPanel input = new JPanel();
        input.add(new JLabel("Name:"));
        input.add(window.enterNameBox); // add the text box for name input

        JPanel nameButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        QuitButton quitButton2 = new QuitButton(false); // quit button without warning
        
        // add buttons
        nameButtons.add(confirmButton);
        nameButtons.add(quitButton2);
        nameButtons.add(menuButton);

        window.startPanel.add(nameTitle);
        window.startPanel.add(input);
        window.startPanel.add(window.errorLabel);
        window.startPanel.add(nameButtons);
        // add panel
        cardPanel.add(window.startPanel, "startPanel");

        confirmButton.addActionListener(e -> controller.startGame(window.enterNameBox.getText()));
    }
}
