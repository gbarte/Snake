package services.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class that handles the logics of database connection.
 */
public class DatabaseHandler {

    private static final String DATA_PATH = "data";
    private static final String FILE_NAME = "storage.db";


    private Connection connection;
    private transient boolean isTest = false;

    /**
     * Setter for the database connection.
     * @param connection instance for the database.
     */
    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Getter of the database connection.
     * @return
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Open connection with database with custom name where users are stored.
     * @param isTest - indicates whether or not it is a test connection.
     * @return True if the connection was established successfully.
     */
    public boolean connect(boolean isTest) {

        this.isTest = isTest;

        // Create data directory if does not exist
        //noinspection ResultOfMethodCallIgnored
        new File(DATA_PATH).mkdirs();

        String fileName = (isTest ? "test_" : "") + FILE_NAME;



        // Define url to table
        String url = "jdbc:sqlite:" + DATA_PATH + "/" + fileName;


        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);

            System.out.println("Connected to DB: " + fileName);
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
        return connect(false);
    }

    /**
     * Closes the connection with database.
     */
    public void close() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Configures the schema of the users table.
     */
    public void configureSchema(String query) {

        System.out.println("Configuring schema...");

        try {
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Clear the table of entities for the given table.
     * @param tableName of the table that needs to be cleared.
     */
    public void clearTable(String tableName) {
        this.connect(isTest);

        try {
            connection.createStatement()
                    .executeQuery(String.format("DELETE FROM %s", tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.close();

    }

}
