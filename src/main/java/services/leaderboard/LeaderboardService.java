package services.leaderboard;

import java.util.List;

import services.LeaderboardEntry;
import services.db.LeaderboardTableHandler;


public class LeaderboardService {

    private static int DEFAULT_LIMIT = 10;

    private LeaderboardTableHandler tableHandler;

    public LeaderboardTableHandler getTableHandler() {
        return this.tableHandler;
    }

    private void setTableHandler(LeaderboardTableHandler tableHandler) {
        this.tableHandler = tableHandler;
    }

    public LeaderboardService(boolean isTest) {
        tableHandler = new LeaderboardTableHandler(isTest);
    }

    public LeaderboardService() {
        tableHandler = new LeaderboardTableHandler(false);
    }


    public void createEntry(String nickname, int score) {
        tableHandler.createEntry(nickname, score);
    }

    public List<LeaderboardEntry> retrieveLeaderboard() {
        return tableHandler.getLeaderboardData(DEFAULT_LIMIT);
    }

}
