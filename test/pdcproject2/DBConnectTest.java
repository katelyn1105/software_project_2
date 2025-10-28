/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdcproject2;

import java.sql.Connection;
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
public class DBConnectTest {

    private DBConnect dbConnect;

    @After
    public void tearDown() throws SQLException {
        if (dbConnect != null) {
            dbConnect.DBClose();
            try {
                DriverManager.getConnection("jdbc:derby:memory:TestDB;shutdown=true");
            } catch (SQLException e) {
                // Derby throws an exception on shutdown — ignore
            }
        }
    }

    @BeforeClass
    public static void loadDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Derby driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            fail("Derby driver not found: " + e.getMessage());
        }
    }

    @Before
    public void setUp() throws SQLException {
        dbConnect = new DBConnect() {
            @Override
            public void establishConnect() {
                try {
                    // Use in-memory database for testing
                    conn = DriverManager.getConnection("jdbc:derby:memory:TestDB;create=true");
                    System.out.println("Connected to in-memory test DB.");
                } catch (SQLException e) {
                    fail("Connection failed: " + e.getMessage());
                }
            }
        };
        dbConnect.establishConnect();

        // Create table if it doesn't exist
        try (Statement st = dbConnect.getConnect().createStatement()) {
            st.executeUpdate("CREATE TABLE PLAYER_DATA ("
                    + "playerName VARCHAR(50), "
                    + "score INT, "
                    + "inventory VARCHAR(200))");
        } catch (SQLException e) {
            // Table already exists is fine
        }

        // Clear table before each test
        try (Statement st = dbConnect.getConnect().createStatement()) {
            st.executeUpdate("DELETE FROM PLAYER_DATA");
        }
    }

    /*@After
    public void tearDown() throws SQLException {
        dbConnect.DBClose();
        try {
            DriverManager.getConnection("jdbc:derby:memory:TestDB;shutdown=true");
        } catch (SQLException e) {
            // Derby throws exception on shutdown — ignore
        }
    }*/

    @Test
    public void testEstablishConnect() throws SQLException {
        assertNotNull("Connection should not be null", dbConnect.getConnect());
        assertFalse("Connection should not be closed", dbConnect.getConnect().isClosed());
    }

    @Test
    public void testDBClose() throws SQLException {
        dbConnect.DBClose();
        Connection conn = dbConnect.getConnect();
        assertTrue("Connection should be null or closed", conn == null || conn.isClosed());
    }

}
