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
public class RPGGameWindow extends JFrame {
    private final GameController controller;
    private final JPanel cardPanel;

    // Core panels
    private JPanel initialPanel, startPanel, stage1Panel, treePanel,
            pathPanel, entrancePanel, kitchenPanel, atticPanel,
            gardenPanel, roseBushPanel, gardenerPanel,
            gardenShedPanel, bossFightPanel, winPanel, lostPanel;

    // Common labels and buttons
    private JLabel errorLabel, hasKeyLabel, noKeyLabel,
            infoLabel4, infoLabel6, infoLabel9,
            chefDialogue, chefDialogue2, chefDialogue3,
            hasSwordLabel, warningLabel;

    private JTextField enterNameBox;

    public RPGGameWindow() {
        super("Escape RPG");

        controller = new GameController(this);
        cardPanel = new JPanel(new CardLayout());

        // Build panels
        buildInitialPanel();
        buildStartPanel();
        buildStage1Panel();
        buildTreePanel();
        buildPathPanel();
        buildEntrancePanel();
        buildKitchenPanel();
        buildAtticPanel();
        buildGardenPanel();
        buildRoseBushPanel();
        buildGardenerPanel();
        buildGardenShedPanel();
        buildBossFightPanel();
        buildWinPanel();
        buildLostPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        add(cardPanel);
        setVisible(true);
    }

    // ---------- PANEL CREATION ---------- //

    private void buildInitialPanel() {
        initialPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("ESCAPE THE MIZTHERY LAIR!!!", JLabel.CENTER);
        title.setFont(new Font("Kavivanar", Font.BOLD, 22));

        JPanel buttons = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton startButton = new JButton("Start");
        JButton highscoresButton = new JButton("Highscores");
        JButton quitButton = new JButton("Quit");

        buttons.add(startButton);
        buttons.add(highscoresButton);
        buttons.add(quitButton);

        initialPanel.add(title, BorderLayout.CENTER);
        initialPanel.add(buttons, BorderLayout.SOUTH);
        cardPanel.add(initialPanel, "initialPanel");

        startButton.addActionListener(e -> controller.goToPanel("startPanel"));
        highscoresButton.addActionListener(e -> controller.showHighScores());
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void buildStartPanel() {
        startPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JLabel title = new JLabel("Enter your name to begin", JLabel.CENTER);

        enterNameBox = new JTextField(15);
        errorLabel = new JLabel("", JLabel.CENTER);

        JPanel input = new JPanel();
        input.add(new JLabel("Name:"));
        input.add(enterNameBox);

        JPanel buttons = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        JButton quitButton = new JButton("Quit");
        buttons.add(confirmButton);
        buttons.add(quitButton);

        startPanel.add(title);
        startPanel.add(input);
        startPanel.add(errorLabel);
        startPanel.add(buttons);
        cardPanel.add(startPanel, "startPanel");

        confirmButton.addActionListener(e -> controller.startGame(enterNameBox.getText()));
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildStage1Panel() {
        stage1Panel = new JPanel(new GridLayout(4, 1, 5, 5));
        stage1Panel.add(new JLabel("You see a path up ahead and a big tree in the distance.", JLabel.CENTER));

        JPanel buttons = new JPanel();
        JButton treeButton = new JButton("Go to the Tree");
        JButton pathButton = new JButton("Follow the Path");
        JButton quitButton = new JButton("Quit");

        buttons.add(treeButton);
        buttons.add(pathButton);
        buttons.add(quitButton);

        stage1Panel.add(new JLabel("Find the hidden treasure and survive!", JLabel.CENTER));
        stage1Panel.add(buttons);

        cardPanel.add(stage1Panel, "stage1Panel");

        treeButton.addActionListener(e -> controller.goToPanel("treePanel"));
        pathButton.addActionListener(e -> controller.goToPanel("pathPanel"));
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildTreePanel() {
        treePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        hasKeyLabel = new JLabel("", JLabel.CENTER);

        JButton takeKeyButton = new JButton("Take the Key");
        JButton goBackButton = new JButton("Back");
        JButton quitButton = new JButton("Quit");

        treePanel.add(new JLabel("You spot a key under the big tree!", JLabel.CENTER));
        treePanel.add(hasKeyLabel);

        JPanel buttons = new JPanel();
        buttons.add(takeKeyButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);

        treePanel.add(buttons);
        cardPanel.add(treePanel, "treePanel");

        takeKeyButton.addActionListener(e -> controller.takeKey());
        goBackButton.addActionListener(e -> controller.goBackToStage1());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildPathPanel() {
        pathPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        noKeyLabel = new JLabel("", JLabel.CENTER);

        JButton unlockButton = new JButton("Unlock the Door");
        JButton goBackButton = new JButton("Back");
        JButton quitButton = new JButton("Quit");

        pathPanel.add(new JLabel("You find a mysterious house with a locked door.", JLabel.CENTER));
        pathPanel.add(noKeyLabel);

        JPanel buttons = new JPanel();
        buttons.add(unlockButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);

        pathPanel.add(buttons);
        cardPanel.add(pathPanel, "pathPanel");

        unlockButton.addActionListener(e -> controller.unlockFrontDoor());
        goBackButton.addActionListener(e -> controller.goBackToStage1());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildEntrancePanel() {
        entrancePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoLabel4 = new JLabel("The door opens! You are now in a dusty entrance hall.", JLabel.CENTER);

        JButton kitchenButton = new JButton("Go to Kitchen");
        JButton atticButton = new JButton("Climb to Attic");
        JButton quitButton = new JButton("Quit");

        entrancePanel.add(infoLabel4);
        entrancePanel.add(new JLabel("You spot a door to a kitchen and a ladder up to an attic.", JLabel.CENTER));

        JPanel buttons = new JPanel();
        buttons.add(kitchenButton);
        buttons.add(atticButton);
        buttons.add(quitButton);
        entrancePanel.add(buttons);

        cardPanel.add(entrancePanel, "entrancePanel");

        kitchenButton.addActionListener(e -> controller.goToKitchen());
        atticButton.addActionListener(e -> controller.goToAttic());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildKitchenPanel() {
        kitchenPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        infoLabel6 = new JLabel("You walk towards the kitchen and meet a friendly chef.", JLabel.CENTER);
        chefDialogue = new JLabel("'Gah! Who are you!? Sorry, I thought you might be Sir Doncillme.'", JLabel.CENTER);
        chefDialogue2 = new JLabel("'I haven't seen him in weeks...'", JLabel.CENTER);
        chefDialogue3 = new JLabel("'Go talk to the Gardener outside if you want to know more.'", JLabel.CENTER);

        JButton gardenButton = new JButton("Go to Garden");
        JButton goBackButton = new JButton("Back");
        JButton quitButton = new JButton("Quit");

        kitchenPanel.add(infoLabel6);
        kitchenPanel.add(chefDialogue);
        kitchenPanel.add(chefDialogue2);
        kitchenPanel.add(chefDialogue3);

        JPanel buttons = new JPanel();
        buttons.add(gardenButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        kitchenPanel.add(buttons);

        cardPanel.add(kitchenPanel, "kitchenPanel");

        gardenButton.addActionListener(e -> controller.goToGarden());
        goBackButton.addActionListener(e -> controller.goToPanel("entrancePanel"));
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildAtticPanel() {
        atticPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoLabel9 = new JLabel("You see something shiny in the corner...", JLabel.CENTER);
        hasSwordLabel = new JLabel("", JLabel.CENTER);

        JButton investigateButton = new JButton("Investigate");
        JButton goBackButton = new JButton("Back");
        JButton quitButton = new JButton("Quit");

        atticPanel.add(new JLabel("You enter the attic, filled with dust and cobwebs.", JLabel.CENTER));
        atticPanel.add(infoLabel9);
        atticPanel.add(hasSwordLabel);

        JPanel buttons = new JPanel();
        buttons.add(investigateButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        atticPanel.add(buttons);

        cardPanel.add(atticPanel, "atticPanel");

        investigateButton.addActionListener(e -> controller.investigateTreasure());
        goBackButton.addActionListener(e -> controller.goToPanel("entrancePanel"));
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildGardenPanel() {
        gardenPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        warningLabel = new JLabel("", JLabel.CENTER);

        JButton roseBushButton = new JButton("Inspect Rose Bush");
        JButton gardenShedButton = new JButton("Go to Garden Shed");
        JButton quitButton = new JButton("Quit");

        gardenPanel.add(new JLabel("You step into the garden; the air feels heavy...", JLabel.CENTER));
        gardenPanel.add(warningLabel);

        JPanel buttons = new JPanel();
        buttons.add(roseBushButton);
        buttons.add(gardenShedButton);
        buttons.add(quitButton);
        gardenPanel.add(buttons);

        cardPanel.add(gardenPanel, "gardenPanel");

        roseBushButton.addActionListener(e -> controller.goToRoseBush());
        gardenShedButton.addActionListener(e -> controller.goToGardenShed());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildRoseBushPanel() {
        roseBushPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JButton gardenerButton = new JButton("Talk to Gardener");
        JButton goBackButton = new JButton("Back");
        JButton quitButton = new JButton("Quit");

        roseBushPanel.add(new JLabel("You trip into the rose bush! Ouch!", JLabel.CENTER));

        JPanel buttons = new JPanel();
        buttons.add(gardenerButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        roseBushPanel.add(buttons);

        cardPanel.add(roseBushPanel, "roseBushPanel");

        gardenerButton.addActionListener(e -> controller.goToGardener());
        goBackButton.addActionListener(e -> controller.goBackToGarden());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildGardenerPanel() {
        gardenerPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        gardenerPanel.add(new JLabel("The gardener appears from behind a hedge!", JLabel.CENTER));
        gardenerPanel.add(new JLabel("He hands you the shed key.", JLabel.CENTER));

        JButton unlockButton = new JButton("Unlock Shed");
        JButton goBackButton = new JButton("Back");
        JButton quitButton = new JButton("Quit");

        JPanel buttons = new JPanel();
        buttons.add(unlockButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        gardenerPanel.add(buttons);

        cardPanel.add(gardenerPanel, "gardenerPanel");

        unlockButton.addActionListener(e -> controller.unlockShed());
        goBackButton.addActionListener(e -> controller.goToGarden());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildGardenShedPanel() {
        gardenShedPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        gardenShedPanel.add(new JLabel("You enter the shed... a dark presence looms.", JLabel.CENTER));

        JButton fightButton = new JButton("Confront Gardener");
        JButton goBackButton = new JButton("Back");
        JButton quitButton = new JButton("Quit");

        JPanel buttons = new JPanel();
        buttons.add(fightButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        gardenShedPanel.add(buttons);

        cardPanel.add(gardenShedPanel, "gardenShedPanel");

        fightButton.addActionListener(e -> controller.goToPanel("bossFightPanel"));
        goBackButton.addActionListener(e -> controller.goBackToGarden());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildBossFightPanel() {
        bossFightPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        bossFightPanel.add(new JLabel("The gardener steps forward, eyes glowing red!", JLabel.CENTER));

        JButton attackButton = new JButton("Attack");
        JButton begButton = new JButton("Beg for Mercy");
        JButton quitButton = new JButton("Quit");

        JPanel buttons = new JPanel();
        buttons.add(attackButton);
        buttons.add(begButton);
        buttons.add(quitButton);
        bossFightPanel.add(buttons);

        cardPanel.add(bossFightPanel, "bossFightPanel");

        attackButton.addActionListener(e -> controller.attackBoss());
        begButton.addActionListener(e -> controller.beg());
        quitButton.addActionListener(e -> controller.resetGame());
    }

    private void buildWinPanel() {
        winPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        winPanel.add(new JLabel("You defeat the gardener and rescue the prisoner! 🎉", JLabel.CENTER));

        JButton restartButton = new JButton("Play Again");
        JButton quitButton = new JButton("Quit");

        JPanel buttons = new JPanel();
        buttons.add(restartButton);
        buttons.add(quitButton);
        winPanel.add(buttons);

        cardPanel.add(winPanel, "winPanel");

        restartButton.addActionListener(e -> controller.resetGame());
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void buildLostPanel() {
        lostPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        lostPanel.add(new JLabel("Without a weapon, you are defeated...", JLabel.CENTER));

        JButton retryButton = new JButton("Retry");
        JButton quitButton = new JButton("Quit");

        JPanel buttons = new JPanel();
        buttons.add(retryButton);
        buttons.add(quitButton);
        lostPanel.add(buttons);

        cardPanel.add(lostPanel, "lostPanel");

        retryButton.addActionListener(e -> controller.resetGame());
        quitButton.addActionListener(e -> System.exit(0));
    }

    // ---------- CONTROLLER INTERFACE ---------- //

    public void showPanel(String name) {
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, name);
    }

    // Getters for GameController
    public JLabel getErrorLabel() { return errorLabel; }
    public JLabel getHasKeyLabel() { return hasKeyLabel; }
    public JLabel getNoKeyLabel() { return noKeyLabel; }
    public JLabel getInfoLabel4() { return infoLabel4; }
    public JLabel getInfoLabel6() { return infoLabel6; }
    public JLabel getInfoLabel9() { return infoLabel9; }
    public JLabel getChefDialogue() { return chefDialogue; }
    public JLabel getChefDialogue2() { return chefDialogue2; }
    public JLabel getChefDialogue3() { return chefDialogue3; }
    public JLabel getHasSwordLabel() { return hasSwordLabel; }
    public JLabel getWarningLabel() { return warningLabel; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RPGGameWindow::new);
    }
}
