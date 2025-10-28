/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject2;

/**
 *
 * @author katelyncorreia
 */

import org.junit.*;
import java.sql.*;
import static org.junit.Assert.*;

public class TestDBConnect {

    private DBConnect dbConnect;

    @BeforeClass
    public static void loadDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Derby driver loaded.");
        } catch (ClassNotFoundException e) {
            fail("Derby driver not found: " + e.getMessage());
        }
    }

    @Before
    public void setUp() {
        // Override DBConnect to use in-memory DB for testing
        dbConnect = new DBConnect() {
            @Override
            public void establishConnect() {
                try {
                    conn = DriverManager.getConnection("jdbc:derby:memory:TestDB;create=true");
                    System.out.println("Connected to in-memory TestDB.");
                } catch (SQLException e) {
                    fail("Connection failed: " + e.getMessage());
                }
            }

            @Override
            public void DBClose() {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                    // Shutdown in-memory DB
                    DriverManager.getConnection("jdbc:derby:memory:TestDB;shutdown=true");
                } catch (SQLException e) {
                    // Expected on shutdown, ignore
                }
            }
        };

        dbConnect.establishConnect();
    }

    @After
    public void tearDown() {
        dbConnect.DBClose();
    }

    @Test
    public void testConnectionNotNull() {
        assertNotNull("Connection should not be null", dbConnect.getConnect());
    }

    @Test
    public void testConnectionOpen() throws SQLException {
        assertFalse("Connection should be open", dbConnect.getConnect().isClosed());
    }

    @Test
    public void testDBClose() throws SQLException {
        dbConnect.DBClose();
        Connection conn = dbConnect.getConnect();
        assertTrue("Connection should be closed or null", conn == null || conn.isClosed());
    }
}
