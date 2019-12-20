package services.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import services.LeaderboardEntry;

public class LeaderboardTableHandler {

    public static final String TABLE_NAME = "leaderboard";

    private transient DatabaseHandler db;
    private transient boolean isTest = false;

    /**
     * Constructor for the class.
     * @param isTest indicates if the testing table should be used.
     */
    public LeaderboardTableHandler(boolean isTest) {
        db = new DatabaseHandler();
        db.connect(isTest);
        this.isTest = isTest;

        try {
            if (!db.getConnection().getMetaData().getTables(null, null, TABLE_NAME, null).next()) {
                configureTableSchema();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }


    /**
     * The method that inserts a new entry into the database.
     * @param nickname of the player to be recorded.
     * @param score of the game that the player has achieved.
     */
    public void createEntry(String nickname, int score) {

        db.connect(isTest);

        String query = "INSERT INTO " + TABLE_NAME
                        + " (nickname, score) "
                        + "VALUES (?, ?)";

        try {

            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);

            preparedStatement.setString(1, nickname);
            preparedStatement.setInt(2, score);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("Score entry was added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

    }

    /**
     * Retrieves 5 top scores .
     * @param limit the amount of returned top entities.
     * @return List of LeaderboardEntry objects.
     */
    @SuppressWarnings("PMD")
    public List<LeaderboardEntry> getLeaderboardData(int limit) {

        db.connect(isTest);

        String query = "SELECT * FROM leaderboard ORDER BY score DESC LIMIT " + limit;
        ResultSet queryResult = null;
        List<LeaderboardEntry> result = new ArrayList<>();


        try {
            queryResult = db.getConnection().createStatement().executeQuery(query);

            while (queryResult.next()) {
                LeaderboardEntry entry = new LeaderboardEntry(
                        queryResult.getString("nickname"),
                        queryResult.getInt("score")
                );
                result.add(entry);
            }

            queryResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return result;
    }

    /**
     * Clear all entries in the table.
     */
    public void clear() {
        db.clearTable(TABLE_NAME);
    }

    /**
     * Configure schema for the table.
     */
    private void configureTableSchema() {
        db.connect(isTest);
        String query = "CREATE TABLE " + TABLE_NAME
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nickname VARCHAR(50),"
                + "score Integer)";
        db.configureSchema(query);
        db.close();
    }


}
