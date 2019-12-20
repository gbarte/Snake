package services.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UsersTableHandlerTest {

    private transient UsersTableHandler tableHandler;

    private static String TEST_USERNAME = "username";

    private void dropCustomTable() {
        try {
            File file = new File("data/test_storage.db");
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        dropCustomTable();
        tableHandler = new UsersTableHandler(true);
    }

    @Test
    void createAndRetrieveUserTest() {
        tableHandler.createUser("username", "password1");
        Assertions.assertNotNull(tableHandler.getUser(TEST_USERNAME));
        dropCustomTable();
    }

    @Test
    void configureTable() throws SQLException {

        dropCustomTable();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.connect(true);

        Assertions.assertFalse(databaseHandler.getConnection().getMetaData().getTables(null, null, UsersTableHandler.TABLE_NAME, null).next());

        tableHandler = new UsersTableHandler(true);

        Assertions.assertTrue(databaseHandler.getConnection().getMetaData().getTables(null, null, UsersTableHandler.TABLE_NAME, null).next());
        dropCustomTable();

    }

    @Test
    void clearTest() {
        tableHandler.createUser(TEST_USERNAME, "password1");
        Assertions.assertNotNull(tableHandler.getUser(TEST_USERNAME));
        tableHandler.clear();
        Assertions.assertNull(tableHandler.getUser(TEST_USERNAME));
    }

}
