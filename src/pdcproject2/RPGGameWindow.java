package pdcproject2;
import javax.swing.*;
import java.awt.*;

/**
 * Escape RPG main game window
 * @author katelyncorreia
 */
public class RPGGameWindow extends JFrame {
    private final GameController controller;
    private final JPanel cardPanel;

    // Core panels
    private JPanel initialPanel, startPanel, stage1Panel, treePanel,
            pathPanel, entrancePanel, kitchenPanel, atticPanel, investigateTreasurePanel,
            gardenPanel, roseBushPanel, gardenerPanel,
            gardenShedPanel, bossFightPanel, winPanel, lostPanel;

    // Common labels and buttons
    private JLabel errorLabel, hasKeyLabel, noKeyLabel,
            infoLabel4, infoLabel6, infoLabel9, treasureInfoLabel1, treasureInfoLabel2,
            chefDialogue, chefDialogue2, chefDialogue3,
            hasSwordLabel, warningLabel, lostLabel;

    private JTextField enterNameBox;
    
    // getter for the controller so that I can use the menu button and it resets the program
    public GameController getController() {
        return controller;
    }
    public JTextField getEnterNameBox() {
        return enterNameBox;
    }
    
    public void showPanel(String panelName) {
    CardLayout layout = (CardLayout) cardPanel.getLayout();
    layout.show(cardPanel, panelName);
}

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
        buildInvestigateTreasure();
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
        QuitButton quitButton = new QuitButton(false);

        buttons.add(startButton);
        buttons.add(highscoresButton);
        buttons.add(quitButton);

        initialPanel.add(title, BorderLayout.CENTER);
        initialPanel.add(buttons, BorderLayout.SOUTH);
        cardPanel.add(initialPanel, "initialPanel");

        startButton.addActionListener(e -> controller.goToPanel("startPanel"));
        highscoresButton.addActionListener(e -> controller.showHighScores());
    }

    private void buildStartPanel() {
        startPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JLabel title = new JLabel("Enter your name to begin", JLabel.CENTER);
        
        MenuButton menuButton = new MenuButton(this, false);

        enterNameBox = new JTextField(15);
        errorLabel = new JLabel("", JLabel.CENTER);

        JPanel input = new JPanel();
        input.add(new JLabel("Name:"));
        input.add(enterNameBox);

        JPanel buttons = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        QuitButton quitButton = new QuitButton(false);
        buttons.add(confirmButton);
        buttons.add(quitButton);
        buttons.add(menuButton);

        startPanel.add(title);
        startPanel.add(input);
        startPanel.add(errorLabel);
        startPanel.add(buttons);
        cardPanel.add(startPanel, "startPanel");

        confirmButton.addActionListener(e -> controller.startGame(enterNameBox.getText()));
    }

    private void buildStage1Panel() {
        stage1Panel = new JPanel(new GridLayout(4, 1, 5, 5));
        stage1Panel.add(new JLabel("You see a path up ahead and a big tree in the distance.", JLabel.CENTER));

        JPanel buttons = new JPanel();
        JButton treeButton = new JButton("Go to the Tree");
        JButton pathButton = new JButton("Follow the Path");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        buttons.add(treeButton);
        buttons.add(pathButton);
        buttons.add(quitButton);
        buttons.add(menuButton);

        stage1Panel.add(new JLabel("Find the hidden treasure and survive!", JLabel.CENTER));
        stage1Panel.add(buttons);

        cardPanel.add(stage1Panel, "stage1Panel");

        treeButton.addActionListener(e -> controller.goToPanel("treePanel"));
        pathButton.addActionListener(e -> controller.goToPanel("pathPanel"));
    }

    private void buildTreePanel() {
        treePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        hasKeyLabel = new JLabel("", JLabel.CENTER);

        JButton takeKeyButton = new JButton("Take the Key");
        JButton goBackButton = new JButton("Back");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        treePanel.add(new JLabel("You spot a key under the big tree!", JLabel.CENTER));
        treePanel.add(hasKeyLabel);

        JPanel buttons = new JPanel();
        buttons.add(takeKeyButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        buttons.add(menuButton);

        treePanel.add(buttons);
        cardPanel.add(treePanel, "treePanel");

        takeKeyButton.addActionListener(e -> controller.takeKey());
        goBackButton.addActionListener(e -> controller.goBackToStage1());
    }

    private void buildPathPanel() {
        pathPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        noKeyLabel = new JLabel("", JLabel.CENTER);

        JButton unlockButton = new JButton("Unlock the Door");
        JButton goBackButton = new JButton("Back");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        pathPanel.add(new JLabel("You find a mysterious house with a locked door.", JLabel.CENTER));
        pathPanel.add(noKeyLabel);

        JPanel buttons = new JPanel();
        buttons.add(unlockButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        buttons.add(menuButton);

        pathPanel.add(buttons);
        cardPanel.add(pathPanel, "pathPanel");

        unlockButton.addActionListener(e -> controller.unlockFrontDoor());
        goBackButton.addActionListener(e -> controller.goBackToStage1());
    }

    private void buildEntrancePanel() {
        entrancePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoLabel4 = new JLabel("The door opens! You are now in a dusty entrance hall.", JLabel.CENTER);

        JButton kitchenButton = new JButton("Go to Kitchen");
        JButton atticButton = new JButton("Climb to Attic");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        entrancePanel.add(infoLabel4);
        entrancePanel.add(new JLabel("You spot a door to a kitchen and a ladder up to an attic.", JLabel.CENTER));

        JPanel buttons = new JPanel();
        buttons.add(kitchenButton);
        buttons.add(atticButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        entrancePanel.add(buttons);

        cardPanel.add(entrancePanel, "entrancePanel");

        kitchenButton.addActionListener(e -> controller.goToKitchen());
        atticButton.addActionListener(e -> controller.goToAttic());
    }

    private void buildKitchenPanel() {
        kitchenPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        infoLabel6 = new JLabel("You walk towards the kitchen and meet a friendly chef.", JLabel.CENTER);
        chefDialogue = new JLabel("'Gah! Who are you!? Sorry, I thought you might be Sir Doncillme.'", JLabel.CENTER);
        chefDialogue2 = new JLabel("'I haven't seen him in weeks...'", JLabel.CENTER);
        chefDialogue3 = new JLabel("'Go talk to the Gardener outside if you want to know more.'", JLabel.CENTER);

        JButton gardenButton = new JButton("Go to Garden");
        JButton goBackButton = new JButton("Back");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        kitchenPanel.add(infoLabel6);
        kitchenPanel.add(chefDialogue);
        kitchenPanel.add(chefDialogue2);
        kitchenPanel.add(chefDialogue3);

        JPanel buttons = new JPanel();
        buttons.add(gardenButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        
        buttons.add(menuButton);
        kitchenPanel.add(buttons);

        cardPanel.add(kitchenPanel, "kitchenPanel");

        gardenButton.addActionListener(e -> controller.goToGarden());
        goBackButton.addActionListener(e -> controller.goToPanel("entrancePanel"));
    }

    private void buildAtticPanel() {
        atticPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoLabel9 = new JLabel("You see something shiny in the corner...", JLabel.CENTER);
        hasSwordLabel = new JLabel("", JLabel.CENTER);

        JButton investigateButton = new JButton("Investigate");
        JButton goBackButton = new JButton("Back");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        atticPanel.add(new JLabel("You enter the attic, filled with dust and cobwebs.", JLabel.CENTER));
        atticPanel.add(infoLabel9);
        atticPanel.add(hasSwordLabel);

        JPanel buttons = new JPanel();
        buttons.add(investigateButton);
        buttons.add(goBackButton);
        buttons.add(quitButton);
        
        buttons.add(menuButton);
        atticPanel.add(buttons);

        cardPanel.add(atticPanel, "atticPanel");
        
        investigateButton.addActionListener(e -> controller.investigateTreasure());
        
        goBackButton.addActionListener(e -> controller.goToPanel("entrancePanel"));
        
        if (controller.hasSword()) {
        hasSwordLabel.setText("You already picked up the sword!");
    }
    }
    
    private void buildInvestigateTreasure() {
        investigateTreasurePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        treasureInfoLabel1 = new JLabel("Congratulations! You found the treasure!", JLabel.CENTER);
        treasureInfoLabel2 = new JLabel("But your journey is not over yet...", JLabel.CENTER);
        
        investigateTreasurePanel.add(treasureInfoLabel1);
        investigateTreasurePanel.add(treasureInfoLabel2);
        
        cardPanel.add(investigateTreasurePanel, "investigateTreasurePanel");
        
    }

    private void buildGardenPanel() {
        gardenPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        warningLabel = new JLabel("", JLabel.CENTER);

        JButton roseBushButton = new JButton("Inspect Rose Bush");
        JButton gardenShedButton = new JButton("Go to Garden Shed");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        gardenPanel.add(new JLabel("You step into the garden; the air feels heavy...", JLabel.CENTER));
        gardenPanel.add(warningLabel);

        JPanel buttons = new JPanel();
        buttons.add(roseBushButton);
        buttons.add(gardenShedButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        gardenPanel.add(buttons);

        cardPanel.add(gardenPanel, "gardenPanel");

        roseBushButton.addActionListener(e -> controller.goToRoseBush());
        gardenShedButton.addActionListener(e -> controller.goToPanel("gardenerPanel"));
    }

    private void buildRoseBushPanel() {
        roseBushPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JButton goBackButton = new JButton("Back");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        roseBushPanel.add(new JLabel("You trip into the rose bush! Ouch!", JLabel.CENTER));

        JPanel buttons = new JPanel();
        buttons.add(goBackButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        roseBushPanel.add(buttons);

        cardPanel.add(roseBushPanel, "roseBushPanel");

        goBackButton.addActionListener(e -> controller.goBackToGarden());
    }

    private void buildGardenerPanel() {
        gardenerPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        
        gardenerPanel.add(new JLabel("You try the door, when suddenly the Gardener appears from behind a hedge!", JLabel.CENTER));
        gardenerPanel.add(new JLabel("'Oi! Whatcha doin there! Ye trynna get inna ma' shed?'", JLabel.CENTER));
        gardenerPanel.add(new JLabel("'Sure, jus' hadta' ask'", JLabel.CENTER));
        gardenerPanel.add(new JLabel("He hands you the shed key.", JLabel.CENTER));

        
        JButton takeKeyButton = new JButton("Take Key");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        JPanel buttons = new JPanel();
        buttons.add(takeKeyButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        gardenerPanel.add(buttons);

        cardPanel.add(gardenerPanel, "gardenerPanel");

        takeKeyButton.addActionListener(e -> controller.unlockShed());
    }

    private void buildGardenShedPanel() {
        gardenShedPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        gardenShedPanel.add(new JLabel("The door creaks open, you swallow your fear and enter", JLabel.CENTER));
        gardenShedPanel.add(new JLabel("You hear the door slam shut (this is getting old you think)", JLabel.CENTER));
        gardenShedPanel.add(new JLabel("You notice Sir Doncillme tied up and unconcious and", JLabel.CENTER));
        gardenShedPanel.add(new JLabel("hear a raspy laugh before the gardener steps in the dim light", JLabel.CENTER));
        
        

        JButton fightButton = new JButton("Confront Gardener");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        JPanel buttons = new JPanel();
        buttons.add(fightButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        gardenShedPanel.add(buttons);

        cardPanel.add(gardenShedPanel, "gardenShedPanel");

        fightButton.addActionListener(e -> controller.goToPanel("bossFightPanel"));
    }

    private void buildBossFightPanel() {
        bossFightPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        bossFightPanel.add(new JLabel("The gardener steps forward, eyes full of hate!", JLabel.CENTER));
        
        bossFightPanel.add(new JLabel("'How easily you both fell for my trap, asked me to let you in even...'", JLabel.CENTER));
        
        bossFightPanel.add(new JLabel("'This beautiful shed is the last thing you will ever see!'", JLabel.CENTER));

        JButton attackButton = new JButton("Attack");
        JButton begButton = new JButton("Beg for Mercy");
        QuitButton quitButton = new QuitButton(true);
        MenuButton menuButton = new MenuButton(this, true);

        JPanel buttons = new JPanel();
        buttons.add(attackButton);
        buttons.add(begButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        bossFightPanel.add(buttons);

        cardPanel.add(bossFightPanel, "bossFightPanel");

        attackButton.addActionListener(e -> controller.attackBoss());
        begButton.addActionListener(e -> controller.beg());
    }

    private void buildWinPanel() {
        winPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        winPanel.add(new JLabel("You defeat the gardener and rescue the prisoner!", JLabel.CENTER));
        
        winPanel.add(new JLabel("Congratulations you have completed the game!", JLabel.CENTER));
        
        winPanel.add(new JLabel("Your Final Score: ", JLabel.CENTER));

        JButton restartButton = new JButton("Play Again");
        QuitButton quitButton = new QuitButton(false);
        MenuButton menuButton = new MenuButton(this, false);

        JPanel buttons = new JPanel();
        buttons.add(restartButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        winPanel.add(buttons);

        cardPanel.add(winPanel, "winPanel");

        restartButton.addActionListener(e -> controller.resetGame());
    }

    private void buildLostPanel() {
        lostPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        lostLabel = new JLabel("", JLabel.CENTER); 
        
        
        lostPanel.add(lostLabel);
// note for kelton: you need to implement the database so that if they click beg for mercy and 
        //dont have the sword it says the without a weapon one, 
        //but if they do have the sword it says the no mercy one
        //I don't understand what you're asking, please elaborate.

        JButton retryButton = new JButton("Retry");
        QuitButton quitButton = new QuitButton(false);
        MenuButton menuButton = new MenuButton(this, false);

        JPanel buttons = new JPanel();
        buttons.add(retryButton);
        buttons.add(quitButton);
        buttons.add(menuButton);
        lostPanel.add(buttons);

        cardPanel.add(lostPanel, "lostPanel");

        retryButton.addActionListener(e -> controller.resetGame());
    }

    // ---------- CONTROLLER INTERFACE ---------- //

   

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
    public JLabel getLostMessageLabel() { return lostLabel; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RPGGameWindow::new);
    }

    
}
