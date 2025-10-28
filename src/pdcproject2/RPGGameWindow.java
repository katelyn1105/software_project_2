package pdcproject2;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author katelyncorreia
 */

public class RPGGameWindow extends JFrame {
    private final GameController controller; // using game controller class
    private final JPanel cardPanel; // using card panel

    // rpg game panels
    JPanel initialPanel, startPanel, stage1Panel, treePanel,
            pathPanel, entrancePanel, kitchenPanel, atticPanel, investigateTreasurePanel,
            gardenPanel, roseBushPanel, gardenerPanel,
            gardenShedPanel, bossFightPanel, winPanel, lostPanel;

    // rpg game labels and buttons
    JLabel errorLabel, hasKeyLabel, noKeyLabel,
            infoLabel4, infoLabel6, infoLabel9, treasureInfoLabel1, treasureInfoLabel2,
            chefDialogue, chefDialogue2, chefDialogue3,
            hasSwordLabel, warningLabel, lostLabel;

    JTextField enterNameBox; // for the user to enter their name, the only part of the game where the user has to enter something
    
    // getter for controller
    public GameController getController() { 
        return controller; 
    }
    
    // getter for the input in the text box 
    public JTextField getEnterNameBox() { 
        return enterNameBox; 
    }
    
    // method for displaying the panel to the user based on the stage they are on
    public void showPanel(String panelName) {
        CardLayout layout = (CardLayout) cardPanel.getLayout();
        layout.show(cardPanel, panelName); // based on the panel name, display the panel
    }

    public RPGGameWindow() {
        super("Escape RPG"); // name on the top of panel

        controller = new GameController(this);
        cardPanel = new JPanel(new CardLayout());

        // build panels for each of the different stages of the game
        RPGStage0 rpgStage0 = new RPGStage0(this, controller, cardPanel);
        RPGStage1 rpgStage1 = new RPGStage1(this, controller, cardPanel);
        RPGStage2 rpgStage2 = new RPGStage2(this, controller, cardPanel);
        RPGStage3 rpgStage3 = new RPGStage3(this, controller, cardPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 480); // size of the panel
        setLocationRelativeTo(null);
        setResizable(false);
        add(cardPanel); // add card panel
        setVisible(true);
    }

    // getters for the labels for the GameController so it can be used, these are the labels that have conditions and are changed based on these conditions
    public JLabel getErrorLabel() { 
        return errorLabel; 
    }
    public JLabel getHasKeyLabel() { 
        return hasKeyLabel; 
    }
    public JLabel getNoKeyLabel() { 
        return noKeyLabel; 
    }
    public JLabel getInfoLabel4() { 
        return infoLabel4; 
    }
    public JLabel getInfoLabel6() { 
        return infoLabel6; 
    }
    public JLabel getInfoLabel9() { 
        return infoLabel9;
    }
    public JLabel getChefDialogue() { 
        return chefDialogue; 
    }
    public JLabel getChefDialogue2() { 
        return chefDialogue2;
    }
    public JLabel getChefDialogue3() { 
        return chefDialogue3;
    }
    public JLabel getHasSwordLabel() { 
        return hasSwordLabel;
    }
    public JLabel getWarningLabel() { 
        return warningLabel;
    }
    public JLabel getLostMessageLabel() { 
        return lostLabel;
    }

    // main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RPGGameWindow::new);
    }
}
