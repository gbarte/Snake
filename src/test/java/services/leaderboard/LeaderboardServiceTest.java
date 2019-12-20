package services.leaderboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.LeaderboardEntry;


public class LeaderboardServiceTest {

    private transient LeaderboardService service;


    @BeforeEach
    void setUp() {
        service = new LeaderboardService(true);
        service.getTableHandler().clear();
    }

    @Test
    void simpleConstructor() {
        service = new LeaderboardService();
        Assertions.assertNotNull(service.getTableHandler());
    }

    @Test
    void advancedConstructor() {
        service = new LeaderboardService(true);
        Assertions.assertNotNull(service.getTableHandler());
    }

    @Test
    void createAndRetrieveEntryTest() {
        Assertions.assertTrue(service.retrieveLeaderboard().isEmpty());
        service.createEntry("nickname", 55);
        LeaderboardEntry entry = service.retrieveLeaderboard().get(0);
        Assertions.assertEquals("nickname", entry.getNickname());
        Assertions.assertEquals(55, entry.getScore());
    }

}
