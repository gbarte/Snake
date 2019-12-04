package db;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;

public class UsersTableTest {

    UsersTable table;

    @BeforeEach
    void setup() {
        table = new UsersTable();
    }

    void dropCustomTable() {
        try {
            File file = new File("data/test_table.db");
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void nullConnectionGetterTest() {
        Assertions.assertNull(table.getConnection());
    }

    @Test
    void connectionTest() throws SQLException {
        table.connect();
        Assertions.assertFalse(table.getConnection().isClosed());
        Assertions.assertNotNull(table.getConnection());
    }

    @Test
    void closeConnectionTest() throws SQLException {
        table.connect();
        table.close();
        Assertions.assertTrue(table.getConnection().isClosed());
        Assertions.assertNotNull(table.getConnection());
    }

    @Test
    void connectionCustomTest() throws SQLException {

        table.connect("test_table");
        Assertions.assertFalse(table.getConnection().isClosed());
        Assertions.assertNotNull(table.getConnection());

        // Verify that schema is initialized.
        Assertions.assertTrue(table.getConnection().getMetaData().getTables(null, null, "users", null).next());

        dropCustomTable();

    }

}
