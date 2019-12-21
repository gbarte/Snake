package services.leaderboard;

import java.util.List;

import services.LeaderboardEntry;
import services.db.LeaderboardTableHandler;

/**
 * The class that handles the logics for the leadboard.
 */
public class LeaderboardService {

    private static int DEFAULT_LIMIT = 10;

    private LeaderboardTableHandler tableHandler;

    public LeaderboardTableHandler getTableHandler() {
        return this.tableHandler;
    }

    private void setTableHandler(LeaderboardTableHandler tableHandler) {
        this.tableHandler = tableHandler;
    }

    /**
     * Constructor with test flag.
     * @param isTest indicates if the test instance is running.
     */
    public LeaderboardService(boolean isTest) {
        tableHandler = new LeaderboardTableHandler(isTest);
    }

    /**
     * Default public constructor for the class.
     */
    public LeaderboardService() {
        tableHandler = new LeaderboardTableHandler(false);
    }


    /**
     * Creates a new leaderboard record.
     * @param nickname of the player to be recorded.
     * @param score achieved in the game session.
     */
    public void createEntry(String nickname, int score) {
        tableHandler.createEntry(nickname, score);
    }

    public List<LeaderboardEntry> retrieveLeaderboard() {
        return tableHandler.getLeaderboardData(DEFAULT_LIMIT);
    }

}
