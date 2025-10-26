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

    private final static String TABLE_NAME = "Player_Data";

    private final DBConnect dbc = new DBConnect();
    private Connection conn;
    //GameState gs = new GameState();

    public DBHandler(DBConnect dbc) {

        this.conn = dbc.getConnect();

        if (conn == null) {
            dbc.establishConnect();
            this.conn = dbc.getConnect();
        }

        createTable();

    }

    public void createTable() {//Creates Player_DATA table if none found (I'll clean later).
        //Test
        try {
            Statement statement = conn.createStatement();
            //Should I put INV in a new table?
            statement.executeUpdate("""
                CREATE TABLE PLAYER_DATA (
                    playerName VARCHAR(50) PRIMARY KEY,
                    score INT,
                    inventory VARCHAR(255)
                )
            """);
            System.out.println("Table created: " + TABLE_NAME);
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) { //This is to check if it already exists <- I used chat for this so I still need to find out how it works.
                System.out.println("Error creating table: " + e.getMessage());
            }
        }
    }

    public void savePlayer(GameState gs) {

        if (conn == null) {
            System.out.println("No connection found.\nUnable to save.");
            return;
        }

        //Save into variables -> into table (E.g String name = bla.getName();)
        String playerName = gs.getName();
        int score = gs.getScore();
        Set<String> inventory = gs.getInventory();

        String inven = inventory.isEmpty() ? "No items" : String.join(", ", inventory);

        String sqlNew = "UPDATE PLAYER_DATA SET score = ?, inventory = ? WHERE playerName = ?";

        String sqlInsert = "INSERT INTO PLAYER_DATA (playerName, score, inventory) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sqlNew)) {
            ps.setInt(1, score);

            ps.setString(2, inven);
            ps.setString(3, playerName);
            int rows = ps.executeUpdate();//Need to code this in, lookat DBManager

            if (rows == 0) { //First player
                try (PreparedStatement insertPs = conn.prepareStatement(sqlInsert)) {
                    insertPs.setString(1, playerName);
                    insertPs.setInt(2, score);
                    insertPs.setString(3, inven);
                    insertPs.executeUpdate();
                    System.out.println("Succesfully added player.");
                }
            }

            System.out.println("Save succesful");
        } catch (SQLException e) {
            System.out.println("Error whilst saving: " + e.getMessage());
        }

    }

    public void highScores() {
        if (conn == null) {
            System.out.println("No connection Found.");
            return;
        }
        String[] columns = {"Player Name", "Score", "Inventory"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT * FROM PLAYER_DATA ORDER BY score DESC";

        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                String name = rs.getString("playerName");
                int score = rs.getInt("score");
                String inv = rs.getString("inventory");

                model.addRow(new Object[]{name, score, inv});
            }

            JTable table = new JTable(model);
            JScrollPane scroll = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scroll, "High Scores", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading highscores: " + e.getMessage());
        }
    }

}
