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
    
    private final static String DB_URL = "jdbc:derby://localhost:1527/Project2DB;create=true";
    //private final static String TABLE_NAME = "Player_Data";
    Connection conn;
 
    
    public DBConnect(){
    establishConnect();
    //createTable();
        
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
    
}
