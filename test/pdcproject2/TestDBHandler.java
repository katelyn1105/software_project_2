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
import java.util.Set;
import static org.junit.Assert.*;

public class TestDBHandler {

    private DBConnect dbConnect;
    private DBHandler dbHandler;

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
        // Use in-memory database for testing
        dbConnect = new DBConnect() {
            @Override
            public void establishConnect() {
                try {
                    conn = DriverManager.getConnection("jdbc:derby:memory:TestDB;create=true");
                } catch (SQLException e) {
                    fail("Failed to connect to in-memory DB: " + e.getMessage());
                }
            }

            @Override
            public void DBClose() {
                try {
                    if (conn != null && !conn.isClosed()) conn.close();
                    DriverManager.getConnection("jdbc:derby:memory:TestDB;shutdown=true");
                } catch (SQLException e) {
                    // Derby throws exception on shutdown, ignore
                }
            }
        };

        dbConnect.establishConnect();
        dbHandler = new DBHandler(dbConnect);
    }

    @After
    public void tearDown() {
        dbConnect.DBClose();
    }

    @Test
    public void testTablesCreated() throws SQLException {
        DatabaseMetaData meta = dbConnect.getConnect().getMetaData();

        ResultSet rs = meta.getTables(null, null, "PLAYER", null);
        assertTrue("PLAYER table should exist", rs.next());

        rs = meta.getTables(null, null, "INVENTORY", null);
        assertTrue("INVENTORY table should exist", rs.next());

        rs = meta.getTables(null, null, "PROGRESS", null);
        assertTrue("PROGRESS table should exist", rs.next());
    }

    @Test
    public void testSavePlayerCreatesEntries() throws SQLException {
        GameState gs = new GameState();
        gs.setName("TestPlayer");
        gs.addScore(100);
        gs.setKey();
        gs.setSword();
        gs.setKitchen();
        dbHandler.savePlayer(gs);

        // Check PLAYER table
        try (PreparedStatement ps = dbConnect.getConnect().prepareStatement("SELECT * FROM PLAYER WHERE player_name = ?")) {
            ps.setString(1, "TestPlayer");
            ResultSet rs = ps.executeQuery();
            assertTrue("Player should exist in PLAYER table", rs.next());
            assertEquals(100, rs.getInt("score"));
        }

        // Check INVENTORY table
        try (PreparedStatement ps = dbConnect.getConnect().prepareStatement(
                "SELECT item_name FROM INVENTORY i JOIN PLAYER p ON i.player_id = p.player_id WHERE p.player_name = ?")) {
            ps.setString(1, "TestPlayer");
            ResultSet rs = ps.executeQuery();
            Set<String> items = new java.util.HashSet<>();
            while (rs.next()) items.add(rs.getString("item_name"));
            assertTrue(items.contains("Key"));
            assertTrue(items.contains("Sword"));
        }

        // Check PROGRESS table
        try (PreparedStatement ps = dbConnect.getConnect().prepareStatement(
                "SELECT * FROM PROGRESS p JOIN PLAYER pl ON p.player_id = pl.player_id WHERE pl.player_name = ?")) {
            ps.setString(1, "TestPlayer");
            ResultSet rs = ps.executeQuery();
            assertTrue("Progress entry should exist", rs.next());
            assertTrue(rs.getBoolean("has_key"));
            assertTrue(rs.getBoolean("has_sword"));
            assertTrue(rs.getBoolean("been_to_kitchen"));
        }
    }
}
