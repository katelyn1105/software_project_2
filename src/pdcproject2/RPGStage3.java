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

// stage 3 garden shed panel to the end of the game where you either win or lose
public class RPGStage3 {
    public RPGStage3 (RPGGameWindow window, GameController controller, JPanel cardPanel) {

        // garden shed panel
        window.gardenShedPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        // text displayed to the player
        window.gardenShedPanel.add(new JLabel("The door creaks open, you swallow your fear and enter", JLabel.CENTER));
        window.gardenShedPanel.add(new JLabel("You hear the door slam shut (this is getting old you think)", JLabel.CENTER));
        window.gardenShedPanel.add(new JLabel("You notice Sir Doncillme tied up and unconscious and", JLabel.CENTER));
        window.gardenShedPanel.add(new JLabel("hear a raspy laugh before the gardener steps in the dim light", JLabel.CENTER));

        // button option
        JButton fightButton = new JButton("Confront Gardener");
        // quit and menu button
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(window, true);

        // add buttons
        JPanel shedButtons = new JPanel();
        shedButtons.add(fightButton);
        shedButtons.add(quitButton);
        shedButtons.add(menuButton);
        window.gardenShedPanel.add(shedButtons);
        
        // add garden shed panel
        cardPanel.add(window.gardenShedPanel, "gardenShedPanel");

        fightButton.addActionListener(e -> {
            controller.getTracker().addCount();
            controller.goToPanel("bossFightPanel");
                });

        // boss fight panel
        window.bossFightPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        // text displayed to the player
        window.bossFightPanel.add(new JLabel("The gardener steps forward, eyes full of hate!", JLabel.CENTER));
        window.bossFightPanel.add(new JLabel("'How easily you both fell for my trap, asked me to let you in even...'", JLabel.CENTER));
        window.bossFightPanel.add(new JLabel("'This beautiful shed is the last thing you will ever see!'", JLabel.CENTER));

        // 2 options buttons
        JButton attackButton = new JButton("Attack");
        JButton begButton = new JButton("Beg for Mercy");
        
        // quit and menu buttons
        QuitButton quitButton2 = new QuitButton(true);
        MenuButton menuButton2 = new MenuButton(window, true);

        JPanel fightButtons = new JPanel();
        fightButtons.add(attackButton);
        fightButtons.add(begButton);
        fightButtons.add(quitButton2);
        fightButtons.add(menuButton2);
        window.bossFightPanel.add(fightButtons);

        cardPanel.add(window.bossFightPanel, "bossFightPanel");

        attackButton.addActionListener(e -> {
            controller.getTracker().addCount();
            controller.attackBoss();
                });
        begButton.addActionListener(e -> {
            controller.getTracker().addCount();
            controller.beg();
                });

        // win panel
        window.winPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        // text displayed if you win, if u have the sword and choose to attack
        window.winPanel.add(new JLabel("You defeat the gardener and rescue the prisoner!", JLabel.CENTER));
        window.winPanel.add(new JLabel("Congratulations you have completed the game!", JLabel.CENTER));
        window.finalScoreLabel = new JLabel("Your Final Score: 0", JLabel.CENTER);
        window.winPanel.add(window.finalScoreLabel);

        // option buttons
        JButton restartButton = new JButton("Play Again");
        QuitButton quitButton3 = new QuitButton(false);
        MenuButton menuButton3 = new MenuButton(window, false);

        JPanel winButtons = new JPanel();
        // add buttons
        winButtons.add(restartButton);
        winButtons.add(quitButton3);
        winButtons.add(menuButton3);
        window.winPanel.add(winButtons);

        cardPanel.add(window.winPanel, "winPanel");
        restartButton.addActionListener(e -> {
            controller.getTracker().addCount();
            controller.resetGame();
                });

        // lost panel
        window.lostPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        window.lostLabel = new JLabel("", JLabel.CENTER);

        window.lostPanel.add(window.lostLabel);

        // button options
        JButton retryButton = new JButton("Retry");
        QuitButton quitButton4 = new QuitButton(false);
        MenuButton menuButton4 = new MenuButton(window, false);

        // add buttons
        JPanel lostButtons = new JPanel();
        lostButtons.add(retryButton);
        lostButtons.add(quitButton4);
        lostButtons.add(menuButton4);
        window.lostPanel.add(lostButtons);

        // add lost panel
        cardPanel.add(window.lostPanel, "lostPanel");
        retryButton.addActionListener(e -> {
            controller.getTracker().addCount();
            controller.resetGame();
                });
    }
}
