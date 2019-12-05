package db;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// TODO: decouple logics; make abstract class to handle abstract table interaction.

public class UsersTable {

    private static final String DATA_PATH = "data";
    private static final String TABLE_NAME = "users";

    private Connection connection;

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Open connection with database with custom name where users are stored.
     * @return True if the connection was established successfully.
     */
    public boolean connect(String tableName) {

        // Create data directory if does not exist
        //noinspection ResultOfMethodCallIgnored
        new File(DATA_PATH).mkdirs();


        // Define url to table
        String url = "jdbc:sqlite:" + DATA_PATH + "/" + tableName + ".db";


        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            if (!connection.getMetaData().getTables(null, null, TABLE_NAME, null).next()) {
                configureSchema();
            }

            System.out.println("Connected to DB: " + tableName);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     * Open connection with database where users are stored.
     * @return True if the connection was established successfully.
     */
    public boolean connect() {
        return connect(TABLE_NAME);
    }

    /**
     * The method that inserts a new user into the database.
     * @param username - name of the user.
     * @param passHash - hashed password value.
     */
    public void createUser(String username, String passHash) {

        // TODO: make a constraint for username uniqueness.

        assert connection != null;

        String query = "INSERT INTO users (username, password) "
                        + "VALUES (?, ?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passHash);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("User was added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieve user by username from the table.
     * @param username - name of the user.
     * @return Map object that contains password and username.
     */
    public Map<String, String> getUser(String username) {

        assert connection != null;

        String query = "SELECT * FROM users WHERE username=? LIMIT 1";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet queryResult = preparedStatement.executeQuery();

            if(queryResult.next()) {
                String hash = queryResult.getString("password");
                System.out.println(hash);

                Map<String, String> result = new HashMap<>();
                result.put("username", username);
                result.put("hash", hash);

                preparedStatement.close();
                return result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Closes the connection with database.
     */
    public void close() {

        assert connection != null;

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Clears out all users in the table.
     */
    public void clearUsers() {
        assert connection != null;
        try {
            connection.createStatement().executeQuery("TRUNCATE TABLE " + TABLE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configures the schema of the users table.
     */
    private void configureSchema() {

        System.out.println("Configuring schema...");

        String query = "CREATE TABLE users ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "username VARCHAR(50),"
                        + "password VARCHAR(50)"
                + ")";

        try {
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
