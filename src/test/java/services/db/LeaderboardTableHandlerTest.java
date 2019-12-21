package services.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.LeaderboardEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;


public class LeaderboardTableHandlerTest {

    private transient LeaderboardTableHandler tableHandler;

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
        tableHandler = new LeaderboardTableHandler(true);
    }

    @Test
    void createAndRetrieveEntryTest() {
        tableHandler.createEntry("nickname", 55);
        LeaderboardEntry entry = tableHandler.getLeaderboardData(1).get(0);
        Assertions.assertEquals("nickname", entry.getNickname());
        Assertions.assertEquals(55, entry.getScore());
        dropCustomTable();
    }

    @Test
    void configureTable() throws SQLException {

        dropCustomTable();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.connect(true);

        Assertions.assertFalse(databaseHandler.getConnection().getMetaData().getTables(null, null, LeaderboardTableHandler.TABLE_NAME, null).next());

        tableHandler = new LeaderboardTableHandler(true);

        Assertions.assertTrue(databaseHandler.getConnection().getMetaData().getTables(null, null, LeaderboardTableHandler.TABLE_NAME, null).next());
        dropCustomTable();

    }

    @Test
    void clearTest() {
        tableHandler.createEntry("nickname", 55);
        Assertions.assertFalse(tableHandler.getLeaderboardData(1).isEmpty());
        tableHandler.clear();
        Assertions.assertTrue(tableHandler.getLeaderboardData(1).isEmpty());
    }

}
