/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 1708k
 */
public final class DBConnect {
    
    private final static String DB_URL = "jdbc:derby://localhost:1527/Project2DB";
    private final static String TABLE_NAME = "Player_Data";
    Connection conn;
 
    
    public DBConnect(){
    establishConnect();
    createTable();
        
}
    
    public void establishConnect(){
        try{
        conn = DriverManager.getConnection(DB_URL, "app", "app");
            System.out.println("Connected to: " + DB_URL);
                }catch(SQLException e){
                    
                   System.out.println("Connection failed. " + e.getMessage());
                   
                }
    }
    
    
    public Connection getConnect(){
        return this.conn;
    }
    
    
    public void createTable(){//Creates Player_DATA table if none found (I'll clean later).
        //Test
        try  {
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
    
    //This is only to save (Which works), not to load (Which blows up netbeans).
    public void savePlayer(String playerName, int score, Set<String> inventory) {
        
        if(conn==null){
            System.out.println("No connection found.");
            return;
        }
        
        //Save into variables -> into table (E.g String name = bla.getName();)
        
        String inven = String.join(", ", inventory);

        String sqlNew = "UPDATE PLAYER_DATA SET score = ?, inventory = ? WHERE playerName = ?";
        
        String sqlInsert = "INSERT INTO PLAYER_DATA (playerName, score, inventory) VALUES (?, ?, ?)";
        
        //I ripped this from te tut and changed it so it needs testing...AGAIN.

        try (PreparedStatement ps = conn.prepareStatement(sqlNew)){
            ps.setInt(1, score);
            
            
            ps.setString(2, inven);
            ps.setString(3, playerName);
            int rows = ps.executeUpdate();//Need to code this in, lookat DBManager
            
            
            
            if (rows == 0) { //First 
                try (PreparedStatement insertPs = conn.prepareStatement(sqlInsert)) {
                    insertPs.setString(1, playerName);
                    insertPs.setInt(2, score);
                    insertPs.setString(3, inven);
                    insertPs.executeUpdate();
                    System.out.println("Succefully added player.");
                }
            }
            
            System.out.println("Save succesful");
        } catch (SQLException e) {
            System.out.println("Error whilst saving: " + e.getMessage());
        }
        
        
    }
    
    
    
    
    public void highScores() {
        String[] columns = {"Player Name", "Score", "Inventory"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT * FROM PLAYER_DATA ORDER BY score DESC";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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
