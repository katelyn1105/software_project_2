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
public class RPGStage3 {
    public RPGStage3 (RPGGameWindow window, GameController controller, JPanel cardPanel) {

        // GARDEN SHED PANEL
        window.gardenShedPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        window.gardenShedPanel.add(new JLabel("The door creaks open, you swallow your fear and enter", JLabel.CENTER));
        window.gardenShedPanel.add(new JLabel("You hear the door slam shut (this is getting old you think)", JLabel.CENTER));
        window.gardenShedPanel.add(new JLabel("You notice Sir Doncillme tied up and unconscious and", JLabel.CENTER));
        window.gardenShedPanel.add(new JLabel("hear a raspy laugh before the gardener steps in the dim light", JLabel.CENTER));

        JButton fightButton = new JButton("Confront Gardener");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(window, true);

        JPanel shedButtons = new JPanel();
        shedButtons.add(fightButton);
        shedButtons.add(quitButton);
        shedButtons.add(menuButton);
        window.gardenShedPanel.add(shedButtons);

        cardPanel.add(window.gardenShedPanel, "gardenShedPanel");

        fightButton.addActionListener(e -> controller.goToPanel("bossFightPanel"));

        // BOSS FIGHT PANEL
        window.bossFightPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        window.bossFightPanel.add(new JLabel("The gardener steps forward, eyes full of hate!", JLabel.CENTER));
        window.bossFightPanel.add(new JLabel("'How easily you both fell for my trap, asked me to let you in even...'", JLabel.CENTER));
        window.bossFightPanel.add(new JLabel("'This beautiful shed is the last thing you will ever see!'", JLabel.CENTER));

        JButton attackButton = new JButton("Attack");
        JButton begButton = new JButton("Beg for Mercy");
        QuitButton quitButton2 = new QuitButton(true);
        MenuButton menuButton2 = new MenuButton(window, true);

        JPanel fightButtons = new JPanel();
        fightButtons.add(attackButton);
        fightButtons.add(begButton);
        fightButtons.add(quitButton2);
        fightButtons.add(menuButton2);
        window.bossFightPanel.add(fightButtons);

        cardPanel.add(window.bossFightPanel, "bossFightPanel");

        attackButton.addActionListener(e -> controller.attackBoss());
        begButton.addActionListener(e -> controller.beg());

        // WIN PANEL
        window.winPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        window.winPanel.add(new JLabel("You defeat the gardener and rescue the prisoner!", JLabel.CENTER));
        window.winPanel.add(new JLabel("Congratulations you have completed the game!", JLabel.CENTER));
        window.winPanel.add(new JLabel("Your Final Score: ", JLabel.CENTER));

        JButton restartButton = new JButton("Play Again");
        QuitButton quitButton3 = new QuitButton(false);
        MenuButton menuButton3 = new MenuButton(window, false);

        JPanel winButtons = new JPanel();
        winButtons.add(restartButton);
        winButtons.add(quitButton3);
        winButtons.add(menuButton3);
        window.winPanel.add(winButtons);

        cardPanel.add(window.winPanel, "winPanel");
        restartButton.addActionListener(e -> controller.resetGame());

        // LOST PANEL
        window.lostPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        window.lostLabel = new JLabel("", JLabel.CENTER);

        window.lostPanel.add(window.lostLabel);

        JButton retryButton = new JButton("Retry");
        QuitButton quitButton4 = new QuitButton(false);
        MenuButton menuButton4 = new MenuButton(window, false);

        JPanel lostButtons = new JPanel();
        lostButtons.add(retryButton);
        lostButtons.add(quitButton4);
        lostButtons.add(menuButton4);
        window.lostPanel.add(lostButtons);

        cardPanel.add(window.lostPanel, "lostPanel");
        retryButton.addActionListener(e -> controller.resetGame());
    }
}
