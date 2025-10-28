/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1708k
 */
public class DBHandler {

    //private final static String TABLE_NAME = "Player_Data";

    
    private final DBConnect dbc = new DBConnect();
    private Connection conn;
    Timer timer = new Timer();
    //GameState gs = new GameState();

    public DBHandler(DBConnect dbc) {

        this.conn = dbc.getConnect();

        if (conn == null) {
            dbc.establishConnect();
            this.conn = dbc.getConnect();
        }

        createTables();

    }
    
    public void createTables() {
    try (Statement statement = conn.createStatement()) {

        statement.executeUpdate("""
            CREATE TABLE PLAYER (
                player_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                player_name VARCHAR(50) NOT NULL UNIQUE,
                score INT,
                play_time BIGINT
            )
        """);

        statement.executeUpdate("""
            CREATE TABLE INVENTORY (
                item_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                player_id INT REFERENCES PLAYER(player_id),
                item_name VARCHAR(50)
            )
        """);

        statement.executeUpdate("""
            CREATE TABLE PROGRESS (
                progress_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                player_id INT REFERENCES PLAYER(player_id),
                current_stage INT,
                has_key BOOLEAN,
                has_sword BOOLEAN,
                been_to_kitchen BOOLEAN
            )
        """);

        System.out.println("All tables created successfully!");

    } catch (SQLException e) {
        if (!"X0Y32".equals(e.getSQLState())) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}
   public void savePlayer(GameState gs) {
    if (conn == null) {
        System.out.println("No connection found.\nUnable to save.");
        return;
    }

    String playerName = gs.getName();
    int score = gs.getScore();
    long playTime = gs.getPlayTimeSecs();

    try {
        int playerId = getOrCreatePlayer(playerName, score, playTime);

        // Clear old inventory entries
        try (PreparedStatement clear = conn.prepareStatement("DELETE FROM INVENTORY WHERE player_id = ?")) {
            clear.setInt(1, playerId);
            clear.executeUpdate();
        }

        // Reinsert current inventory items
        try (PreparedStatement insertInv = conn.prepareStatement("INSERT INTO INVENTORY (player_id, item_name) VALUES (?, ?)")) {
            for (String item : gs.getInventory()) {
                insertInv.setInt(1, playerId);
                insertInv.setString(2, item);
                insertInv.addBatch();
            }
            insertInv.executeBatch();
        }

        // Save progress manually (no MERGE)
        try (PreparedStatement ps = conn.prepareStatement(
            "SELECT player_id FROM PROGRESS WHERE player_id = ?")) {
            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                try (PreparedStatement update = conn.prepareStatement(
                    "UPDATE PROGRESS SET current_stage=?, has_key=?, has_sword=?, been_to_kitchen=? WHERE player_id=?")) {
                    update.setInt(1, gs.getCurrentStage());
                    update.setBoolean(2, gs.getKey());
                    update.setBoolean(3, gs.getSword());
                    update.setBoolean(4, gs.getKitchen());
                    update.setInt(5, playerId);
                    update.executeUpdate();
                }
            } else {
                try (PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO PROGRESS (player_id, current_stage, has_key, has_sword, been_to_kitchen) VALUES (?, ?, ?, ?, ?)")) {
                    insert.setInt(1, playerId);
                    insert.setInt(2, gs.getCurrentStage());
                    insert.setBoolean(3, gs.getKey());
                    insert.setBoolean(4, gs.getSword());
                    insert.setBoolean(5, gs.getKitchen());
                    insert.executeUpdate();
                }
            }
        }

        System.out.println("Player data saved successfully.");

    } catch (SQLException e) {
        System.out.println("Error saving player: " + e.getMessage());
    }
}
    
    private int getOrCreatePlayer(String name, int score, long playTime) throws SQLException {
    int playerId = -1;

    // Try to find existing player
    try (PreparedStatement ps = conn.prepareStatement("SELECT player_id FROM PLAYER WHERE player_name = ?")) {
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            playerId = rs.getInt(1);
            try (PreparedStatement update = conn.prepareStatement("UPDATE PLAYER SET score = ?, play_time = ? WHERE player_id = ?")) {
                update.setInt(1, score);
                update.setLong(2, playTime);
                update.setInt(3, playerId);
                update.executeUpdate();
            }
        } else {
            try (PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO PLAYER (player_name, score, play_time) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                insert.setString(1, name);
                insert.setInt(2, score);
                insert.setLong(3, playTime);
                insert.executeUpdate();
                ResultSet keys = insert.getGeneratedKeys();
                if (keys.next()) {
                    playerId = keys.getInt(1);
                }
            }
        }
    }
    return playerId;
}
    //Method to display Highscores into one table
  public void showHighScores() {
    if (conn == null) {
        System.out.println("No connection Found.");
        return;
    }

    String[] columns = {"Player Name", "Score", "Play Time (s)", "Inventory"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);

    //SQL string.
    String sqlPlayers = "SELECT player_id, player_name, score, play_time FROM PLAYER ORDER BY score DESC";

    try (Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sqlPlayers)) {

        while (rs.next()) {
            //variables to be placed in table.
            int id = rs.getInt("player_id");
            String name = rs.getString("player_name");
            int score = rs.getInt("score");
            long time = rs.getLong("play_time");

            // Build inventory manually
            StringBuilder inventoryBuilder = new StringBuilder();
            try (PreparedStatement invPs = conn.prepareStatement("SELECT item_name FROM INVENTORY WHERE player_id = ?")) {
                invPs.setInt(1, id);
                ResultSet invRs = invPs.executeQuery();
                //Inventory, whille has next.
                while (invRs.next()) {
                    if (inventoryBuilder.length() > 0) inventoryBuilder.append(", ");
                    inventoryBuilder.append(invRs.getString("item_name"));
                }
            }
            String inventory = inventoryBuilder.length() == 0 ? "No Items" : inventoryBuilder.toString();

            model.addRow(new Object[]{name, score, time, inventory});
        }

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scroll, "High Scores", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        System.out.println("Error showing high scores: " + e.getMessage());
    }
}
}
