package services.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UsersTableHandler {

    public static final String TABLE_NAME = "users";

    private transient DatabaseHandler db;
    private transient boolean isTest = false;

    /**
     * Constructor for the class.
     * @param isTest indicates if the testing table should be used.
     */
    public UsersTableHandler(boolean isTest) {

        db = new DatabaseHandler();
        db.connect(isTest);
        this.isTest = isTest;

        try {
            if (!db.getConnection().getMetaData().getTables(null, null, TABLE_NAME, null).next()) {
                configureTableSchema();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }


    /**
     * The method that inserts a new user into the database.
     * @param username - name of the user.
     * @param passHash - hashed password value.
     */
    public void createUser(String username, String passHash) {

        db.connect(isTest);

        String query = "INSERT INTO users (username, password) "
                + "VALUES (?, ?)";

        try {

            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passHash);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("User was added");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    /**
     * Retrieve user by username from the table.
     * @param username - name of the user.
     * @return Map object that contains password and username.
     */
    @SuppressWarnings("PMD")
    public Map<String, String> getUser(String username) {

        db.connect(isTest);

        String query = "SELECT * FROM users WHERE username=? LIMIT 1";

        ResultSet queryResult = null;
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = db.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);

            queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                String hash = queryResult.getString("password");
                System.out.println(hash);

                Map<String, String> result = new HashMap<>();
                result.put("username", username);
                result.put("hash", hash);

                queryResult.close();
                preparedStatement.close();
                db.close();
                return result;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return null;
    }


    /**
     * Clears out all users in the table.
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
                + "username VARCHAR(50) UNIQUE,"
                + "password VARCHAR(50))";

        db.configureSchema(query);
        db.close();
    }


}
