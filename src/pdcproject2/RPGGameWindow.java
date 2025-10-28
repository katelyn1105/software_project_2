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
    JPanel initialPanel, startPanel, stage1Panel, treePanel,
            pathPanel, entrancePanel, kitchenPanel, atticPanel, investigateTreasurePanel,
            gardenPanel, roseBushPanel, gardenerPanel,
            gardenShedPanel, bossFightPanel, winPanel, lostPanel;

    // Common labels and buttons
    JLabel errorLabel, hasKeyLabel, noKeyLabel,
            infoLabel4, infoLabel6, infoLabel9, treasureInfoLabel1, treasureInfoLabel2,
            chefDialogue, chefDialogue2, chefDialogue3,
            hasSwordLabel, warningLabel, lostLabel;

    JTextField enterNameBox;

    public GameController getController() { return controller; }
    public JTextField getEnterNameBox() { return enterNameBox; }

    public void showPanel(String panelName) {
        CardLayout layout = (CardLayout) cardPanel.getLayout();
        layout.show(cardPanel, panelName);
    }

    public RPGGameWindow() {
        super("Escape RPG");

        controller = new GameController(this);
        cardPanel = new JPanel(new CardLayout());

        // Build panels
        new RPGStage0(this, controller, cardPanel);
        new RPGStage1(this, controller, cardPanel);
        new RPGStage2(this, controller, cardPanel);
        new RPGStage3(this, controller, cardPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        add(cardPanel);
        setVisible(true);
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
    public JLabel getLostMessageLabel() { return lostLabel; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RPGGameWindow::new);
    }
}
