/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdcproject2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.sql.*;

/**
 *
 * @author 1708k
 */
public class DBHandlerTest {

    private DBConnect dbConnect;
    private DBHandler dbHandler;

    @After
    public void tearDown() throws SQLException {
        if (dbConnect != null) {
            dbConnect.DBClose();
            try {
                DriverManager.getConnection("jdbc:derby:memory:TestDB;shutdown=true");
            } catch (SQLException e) {
                // ignore
            }
        }
    }

    @BeforeClass
    public static void loadDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            fail("Derby driver not found: " + e.getMessage());
        }
    }

    @Before
    public void setUp() throws SQLException {
        dbConnect = new DBConnect();
        dbConnect.establishConnect();

        dbHandler = new DBHandler(dbConnect);

        // Make sure the table exists
        dbHandler.createTables();

        // Clear table before each test
        try (Statement st = dbConnect.getConnect().createStatement()) {
            st.executeUpdate("DELETE FROM PLAYER_DATA");
        } catch (SQLException e) {
            // This should now never happen
            fail("Failed to clear PLAYER_DATA table: " + e.getMessage());
        }
    }

        /*@After
    public void tearDown() throws SQLException {
        dbConnect.DBClose();
        try {
            DriverManager.getConnection("jdbc:derby:memory:TestDB;shutdown=true");
        } catch (SQLException e) {
            // Ignore shutdown exception
        }
    }*/
        @Test
        public void testCreateTables()throws SQLException {
            try (Statement st = dbConnect.getConnect().createStatement()) {
                st.executeUpdate(
                        "CREATE TABLE PLAYER_DATA ("
                        + "playerName VARCHAR(50), "
                        + "score INT, "
                        + "inventory VARCHAR(200))"
                );
            } catch (SQLException e) {
                if (!e.getSQLState().equals("X0Y32")) { // Table already exists
                    throw e;
                }
            }
        }

        @Test
        public void testSavePlayer() throws SQLException {
            GameState gs = new GameState();
            gs.setName("Tester");
            gs.addScore(100);
            gs.setKey();
            gs.setSword();

            dbHandler.savePlayer(gs);

            try (PreparedStatement ps = dbConnect.getConnect().prepareStatement(
                    "SELECT playerName, score, inventory FROM PLAYER_DATA WHERE playerName=?")) {
                ps.setString(1, "Tester");
                ResultSet rs = ps.executeQuery();
                assertTrue("Player should exist", rs.next());
                assertEquals(100, rs.getInt("score"));
                assertTrue("Inventory should contain Key", rs.getString("inventory").contains("Key"));
            }
        }

        @Test
        public void testShowHighScores
        
            () {
        try {
                dbHandler.showHighScores();
            } catch (Exception e) {
                fail("showHighScores threw an exception: " + e.getMessage());
            }
        }
    }
