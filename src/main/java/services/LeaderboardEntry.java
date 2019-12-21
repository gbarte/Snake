package services;

/**
 * Class for the entry object in the leaderboard;
 * contains nickname and score.
 */
public class LeaderboardEntry {

    private transient String nickname;
    private transient int score;

    public String getNickname() {
        return this.nickname;
    }

    public int getScore() {
        return this.score;
    }

    public LeaderboardEntry(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }


}
