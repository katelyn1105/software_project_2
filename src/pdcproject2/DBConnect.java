/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;
import java.sql.*;
import java.util.*;
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
        
        
}
    
    public void establishConnect(){
        try{
        conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to: " + DB_URL);
                }catch(SQLException e){
                    e.printStackTrace();
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
    public void savePlayer(Statement statement) {
        //This is where I would use the getters
        
        //Save into variables -> into table (E.g String name = bla.getName();)

        String sql = """
            MERGE INTO PLAYERS p
            USING (VALUES (?, ?, ?)) AS vals(playerName, score, inventory)
            ON p.playerName = vals.playerName
            WHEN MATCHED THEN UPDATE SET score = vals.score, inventory = vals.inventory
            WHEN NOT MATCHED THEN INSERT (playerName, score, inventory)
            VALUES (vals.playerName, vals.score, vals.inventory)
            """;
        
        //I ripped this from te tut and changed it so it needs testing...AGAIN.

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            //ps.setString(1, name);
            //ps.setInt(2, score);
            //ps.setString(3, inv);
            ps.executeUpdate();//Need to code this in, lookat DBManager
            
            System.out.println("Save succesful");
        } catch (SQLException e) {
            System.out.println("Error whilst saving: " + e.getMessage());
        }
    }
    
    
}
